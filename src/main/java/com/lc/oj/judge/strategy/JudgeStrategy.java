package com.lc.oj.judge.strategy;

import com.lc.oj.judge.codesandbox.model.JudgeInfo;

/**
 * @Author Lc
 * @Date 2023/8/22
 * @PackageName: com.lc.oj.judge.strategy
 * @ClassName: JudgeStrategy
 * @Description:
 */

public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext context);
}
