package com.lc.oj.judge.codesandbox;

import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * @Author Lc
 * @Date 2023/8/21
 * @PackageName: com.lc.oj.judge.codesandbox
 * @ClassName: CodeSandbox
 * @Description:
 */

public interface CodeSandbox {
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
