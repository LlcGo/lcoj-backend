package com.lc.oj.judge.codesandbox.impl;

import com.lc.oj.judge.codesandbox.CodeSandbox;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.lc.oj.judge.codesandbox.model.JudgeInfo;
import com.lc.oj.model.enums.JudgeInfoMessageEnum;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Lc
 * @Date 2023/8/21
 * @PackageName: com.lc.oj.judge.codesandbox.impl
 * @ClassName: ExampleCodeSandbox
 * @Description:
 */

/**
 * 测试沙箱
 */
@Service
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        List<String> input = executeCodeRequest.getInput();

        //组装返回信息
        ExecuteCodeResponse executeCodeResponse = new ExecuteCodeResponse();
        executeCodeResponse.setOutput(input);
        executeCodeResponse.setMessage("执行成功");
        executeCodeResponse.setStatue(JudgeInfoMessageEnum.ACCEPTED.getText());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage("成功");
        judgeInfo.setMemory(1000L);
        judgeInfo.setTime(1000L);
        executeCodeResponse.setJudgeInfo(judgeInfo);
        return executeCodeResponse;
    }
}
