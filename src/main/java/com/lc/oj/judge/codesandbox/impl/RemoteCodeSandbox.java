package com.lc.oj.judge.codesandbox.impl;

import com.lc.oj.judge.codesandbox.CodeSandbox;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Service;

/**
 * @Author Lc
 * @Date 2023/8/21
 * @PackageName: com.lc.oj.judge.codesandbox.impl
 * @ClassName: ExampleCodeSandbox
 * @Description:
 */

/**
 * 远程调用沙箱
 */
@Service
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程调用沙箱");
        return null;
    }
}
