package com.lc.oj.judge.codesandbox.model;

import lombok.Data;

/**
 * @Author Lc
 * @Date 2023/8/16
 * @PackageName: com.lc.oj.model.dto.questionsubmit
 * @ClassName: JudgeInfo
 * @Description: 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 执行信息
     */
    private String message;

    /**
     * 消耗内存
     */
    private Long memory;

    /**
     * 消耗时间(ms)
     */
    private Long time;
}
