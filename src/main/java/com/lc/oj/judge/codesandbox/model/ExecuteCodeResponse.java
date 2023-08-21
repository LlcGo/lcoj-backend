package com.lc.oj.judge.codesandbox.model;

import com.lc.oj.model.dto.questionsubmit.JudgeInfo;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Lc
 * @Date 2023/8/21
 * @PackageName: com.lc.oj.judge.codesandbox.model
 * @ClassName: ExecuteCodeRequest
 * @Description:
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteCodeResponse {

    private List<String> output;

    /**
     * 接口返回的信息
     */
    private String message;

    /**
     * 执行状态
     */
    private String statue;

    /**
     * 执行配置
     */
    private JudgeInfo judgeInfo;
}
