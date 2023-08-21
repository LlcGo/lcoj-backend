package com.lc.oj.judge.codesandbox;

import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author Lc
 * @Date 2023/8/21
 * @PackageName: com.lc.oj.judge.codesandbox
 * @ClassName: CodeSandboxProxy
 * @Description:
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info(executeCodeRequest.toString() + "开始执行");
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        log.info(executeCodeRequest.toString() + "执行结束");
        return executeCodeResponse;
    }
}
