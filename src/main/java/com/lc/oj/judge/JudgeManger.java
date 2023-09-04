package com.lc.oj.judge;

import com.lc.oj.judge.strategy.DefaultJudgeStrategy;
import com.lc.oj.judge.strategy.JudgeContext;
import com.lc.oj.judge.strategy.JudgeStrategy;
import com.lc.oj.judge.codesandbox.model.JudgeInfo;
import com.lc.oj.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * @Author Lc
 * @Date 2023/8/22
 * @PackageName: com.lc.oj.judge
 * @ClassName: JudgeManger
 * @Description:
 */
@Service
public class JudgeManger {

    public JudgeInfo doJudge(JudgeContext context) {
        QuestionSubmit questionSubmit = context.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if ("java".equals(language)) {
            judgeStrategy = new DefaultJudgeStrategy();
        }
        return judgeStrategy.doJudge(context);
    }
}
