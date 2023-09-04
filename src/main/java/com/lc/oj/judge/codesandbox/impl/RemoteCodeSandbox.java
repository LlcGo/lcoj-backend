package com.lc.oj.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.github.xiaoymin.knife4j.core.util.StrUtil;
import com.lc.oj.common.ErrorCode;
import com.lc.oj.exception.BusinessException;
import com.lc.oj.judge.codesandbox.CodeSandbox;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import org.springframework.stereotype.Service;

import java.sql.Struct;

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
        String url = "http://localhost:8090/executeCode";
        String jsonStr = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url).body(jsonStr).execute().body();
        if(StrUtil.isBlank(responseStr)){
            throw new BusinessException(ErrorCode.REMOTE_ERROR,"executeCode remote error");
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
