package com.lc.oj.model.dto.question;

import lombok.Data;

/**
 * @Author Lc
 * @Date 2023/8/16
 * @PackageName: com.lc.oj.model.dto.question
 * @ClassName: JudgeCase
 * @Description:
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制 (ms)
     */
    private Long timeLimit;

    /**
     * 内存限制(KB)
     */
    private Long memoryLimit;

    /**
     * 堆栈限制(KB)
     */
    private Long stackLimit;
}
