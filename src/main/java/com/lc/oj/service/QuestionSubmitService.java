package com.lc.oj.service;

import com.lc.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.lc.oj.model.entity.QuestionSubmit;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lc.oj.model.entity.User;

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
}
