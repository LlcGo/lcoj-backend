package com.lc.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lc.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lc.oj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.lc.oj.model.entity.Question;
import com.lc.oj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.oj.model.entity.User;
import com.lc.oj.model.vo.QuestionSubmitVO;
import com.lc.oj.model.vo.QuestionVO;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交题目
     *
     * @param postId
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest postId, User loginUser);

    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取帖子封装
     *
     * @param questionSubmit
     * @param loginUser
     * @return
     */

    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);

    /**
     * 分页获取帖子封装
     *
     * @param questionSubmitPage
     * @param loginUser
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);
}
