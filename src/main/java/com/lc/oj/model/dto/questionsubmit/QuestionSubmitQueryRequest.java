package com.lc.oj.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.TableField;
import com.lc.oj.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionSubmitQueryRequest extends PageRequest implements Serializable {


    /**
     * 编程语言
     */
    private String language;

    /**
     * 题目状态
     */
    private Integer status;


    /**
     * 用户代码
     */
    private String code;


    /**
     * 题目id
     */
    private Long questionId;


    /**
     * 这个题目是哪个用户提交的
     */
    private Long userId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}