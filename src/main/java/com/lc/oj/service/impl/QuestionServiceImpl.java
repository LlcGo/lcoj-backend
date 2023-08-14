package com.lc.oj.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lc.oj.model.entity.Question;
import com.lc.oj.service.QuestionService;
import com.lc.oj.mapper.QuestionMapper;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
    implements QuestionService{

}




