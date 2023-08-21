package com.lc.oj.judge.codesandbox.impl;

import com.lc.oj.judge.codesandbox.CodeSandbox;
import com.lc.oj.judge.codesandbox.CodeSandboxFactory;
import com.lc.oj.judge.codesandbox.CodeSandboxProxy;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.xmlbeans.impl.jam.mutable.MAnnotatedElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
public class ExampleCodeSandboxTest {

    @Value("${codeSandbox.type}")
    private String type;

    @Test
    public void executeCode() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(ExecuteCodeRequest.builder()
                .coed("555").language("java").input(Arrays.asList("12", "34")).build()
        );
        Assertions.assertNotNull(executeCodeResponse);
    }

    @Test
    public void executeCodeByProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(ExecuteCodeRequest.builder()
                .coed("555").language("java").input(Arrays.asList("12", "34")).build()
        );
        Assertions.assertNotNull(executeCodeResponse);
    }

    public static void main(String[] args) {
//        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
//        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(ExecuteCodeRequest.builder()
//                .coed("555").language("java").input(Arrays.asList("12", "34")).build()
//        );
    }
}