package com.lc.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lc.oj.common.ErrorCode;
import com.lc.oj.constant.CommonConstant;
import com.lc.oj.exception.BusinessException;
import com.lc.oj.judge.JudgeService;
import com.lc.oj.judge.codesandbox.CodeSandbox;
import com.lc.oj.judge.codesandbox.CodeSandboxFactory;
import com.lc.oj.judge.codesandbox.CodeSandboxProxy;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.lc.oj.mapper.QuestionSubmitMapper;
import com.lc.oj.model.dto.question.JudgeCase;
import com.lc.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lc.oj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.lc.oj.model.entity.*;
import com.lc.oj.model.entity.QuestionSubmit;
import com.lc.oj.model.enums.JudgeInfoMessageEnum;
import com.lc.oj.model.enums.QuestionSubmitLanguaStatusEnum;
import com.lc.oj.model.enums.QuestionSubmitStatusEnum;
import com.lc.oj.model.enums.UserRoleEnum;
import com.lc.oj.model.vo.QuestionSubmitVO;
import com.lc.oj.model.vo.QuestionVO;
import com.lc.oj.model.vo.UserVO;
import com.lc.oj.service.QuestionService;
import com.lc.oj.service.QuestionSubmitService;
import com.lc.oj.service.UserService;
import com.lc.oj.utils.SqlUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * 提交题目服务实现
 *

 */
@Service
public class QuestionSubmitServiceImpl extends ServiceImpl<QuestionSubmitMapper, QuestionSubmit>
        implements QuestionSubmitService {

    @Resource
    @Lazy
    private JudgeService judgeService;

    @Resource
    private QuestionService questionService;

    @Resource
    private UserService userService;



    /**
     * 提交题目
     *
     * @param questionSubmitAddRequest
     * @param loginUser
     * @return
     */
    @Override
    public long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser) {

        String language = questionSubmitAddRequest.getLanguage();
        QuestionSubmitLanguaStatusEnum languaStatusEnum = QuestionSubmitLanguaStatusEnum.getEnumByValue(language);
        if(languaStatusEnum == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"没有对应的语言");
        }
        Long questionId = questionSubmitAddRequest.getQuestionId();
        // 判断实体是否存在，根据类别获取实体
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        Long userId = loginUser.getId();
        String code = questionSubmitAddRequest.getCode();
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setLanguage(language);
        questionSubmit.setCode(code);
        questionSubmit.setStatus(QuestionSubmitStatusEnum.WAITING.getValue());
        questionSubmit.setQuestionId(questionId);
        questionSubmit.setUserId(userId);
        questionSubmit.setJudgeInfo("{}");

        boolean save = this.save(questionSubmit);
        if(!save){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"数据录入失败");
        }
        //加入判题机处理
        Long questionSubmitId = questionSubmit.getId();
        CompletableFuture.runAsync(()-> {
            judgeService.doJudge(questionSubmitId);
        });
        return questionSubmitId;

    }

    @Override
    public QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest) {
        QueryWrapper<QuestionSubmit> queryWrapper = new QueryWrapper<>();
        if (questionSubmitQueryRequest == null) {
            return queryWrapper;
        }
        Long userId = questionSubmitQueryRequest.getUserId();
        String language = questionSubmitQueryRequest.getLanguage();
        Integer status = questionSubmitQueryRequest.getStatus();
        String code = questionSubmitQueryRequest.getCode();
        Long questionId = questionSubmitQueryRequest.getQuestionId();
        String sortField = questionSubmitQueryRequest.getSortField();
        String sortOrder = questionSubmitQueryRequest.getSortOrder();

        queryWrapper.eq(ObjectUtils.isNotEmpty(questionId), "questionId", questionId);
        queryWrapper.eq(QuestionSubmitStatusEnum.getEnumByValue(status) != null, "status", status);
        queryWrapper.eq(ObjectUtils.isNotEmpty(code), "code", code);
        queryWrapper.eq(ObjectUtils.isNotEmpty(language), "language", language);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq("isDelete", false);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser) {
        QuestionSubmitVO questionSubmitVO = QuestionSubmitVO.objToVo(questionSubmit);
        //如果当前查询的人不是自己提交的题目并且不是管理员，不给返回代码
        Long userId = loginUser.getId();
        if(!userId.equals(questionSubmit.getUserId()) && !userService.isAdmin(loginUser)){
            questionSubmitVO.setCode(null);
        }
        return questionSubmitVO;
    }

    @Override
    public Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User LoginUser) {
        List<QuestionSubmit> questionSubmitList = questionSubmitPage.getRecords();
        Page<QuestionSubmitVO> questionSubmitVOPage = new Page<>(questionSubmitPage.getCurrent(), questionSubmitPage.getSize(), questionSubmitPage.getTotal());
        if (CollectionUtils.isEmpty(questionSubmitList)) {
            return questionSubmitVOPage;
        }
        List<QuestionSubmitVO> questionSubmitVOList = questionSubmitList.stream()
                .map(questionSubmit -> getQuestionSubmitVO(questionSubmit, LoginUser))
                .collect(Collectors.toList());
        questionSubmitVOPage.setRecords(questionSubmitVOList);
        return questionSubmitVOPage;
    }

}




