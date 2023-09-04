package com.lc.oj.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.oj.annotation.AuthCheck;
import com.lc.oj.common.BaseResponse;
import com.lc.oj.common.ErrorCode;
import com.lc.oj.common.ResultUtils;
import com.lc.oj.constant.UserConstant;
import com.lc.oj.exception.BusinessException;
import com.lc.oj.model.dto.question.QuestionQueryRequest;
import com.lc.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lc.oj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.lc.oj.model.entity.Question;
import com.lc.oj.model.entity.QuestionSubmit;
import com.lc.oj.model.entity.User;
import com.lc.oj.model.vo.QuestionSubmitVO;
import com.lc.oj.service.QuestionSubmitService;
import com.lc.oj.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子点赞接口
 */
//@RestController
//@RequestMapping("/question_submit")
@Slf4j
public class QuestionSubmitController {

//    @Resource
//    private QuestionSubmitService questionSubmitService;
//
//    @Resource
//    private UserService userService;
//
//    /**
//     * 提交题目
//     *
//     * @param questionSubmitAddRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/")
//    public BaseResponse<Long> doThumb(@RequestBody QuestionSubmitAddRequest questionSubmitAddRequest,
//                                      HttpServletRequest request) {
//        if (questionSubmitAddRequest == null || questionSubmitAddRequest.getQuestionId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        final User loginUser = userService.getLoginUser(request);
//        long result = questionSubmitService.doQuestionSubmit(questionSubmitAddRequest, loginUser);
//        return ResultUtils.success(result);
//    }
//
//    @PostMapping("/list/page")
//    public BaseResponse<Page<QuestionSubmitVO>> listQuestionByPage(@RequestBody QuestionSubmitQueryRequest questionSubmitQueryRequest,
//                                                                   HttpServletRequest request) {
//        long current = questionSubmitQueryRequest.getCurrent();
//        long size = questionSubmitQueryRequest.getPageSize();
//        Page<QuestionSubmit> questionSubmitPage = questionSubmitService.page(new Page<>(current, size),
//                questionSubmitService.getQueryWrapper(questionSubmitQueryRequest));
//        User loginUser = userService.getLoginUser(request);
//        return ResultUtils.success(questionSubmitService.getQuestionSubmitVOPage(questionSubmitPage, loginUser));
//    }

}
