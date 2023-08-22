package com.lc.oj.judge.strategy;

import com.lc.oj.model.dto.question.JudgeCase;
import com.lc.oj.model.dto.questionsubmit.JudgeInfo;
import com.lc.oj.model.entity.Question;
import com.lc.oj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * @Author Lc
 * @Date 2023/8/22
 * @PackageName: com.lc.oj.judge.strategy
 * @ClassName: JudgeContext
 * @Description:
 */

@Data
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> input;

    private List<String> output;

    private List<JudgeCase> judgeCase;

    private Question question;

    private QuestionSubmit questionSubmit;
}
