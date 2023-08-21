package com.lc.oj.judge.codesandbox;

import com.lc.oj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.lc.oj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.lc.oj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * @Author Lc
 * @Date 2023/8/21
 * @PackageName: com.lc.oj.judge.codesandbox
 * @ClassName: CodeSandboxFactory
 * @Description:
 */

public class CodeSandboxFactory {

    public static CodeSandbox newInstance(String type){
        switch (type){
            case  "example" :
                return new ExampleCodeSandbox();
            case "remote" :
                return new RemoteCodeSandbox();
            case "thirdParty" :
                return new ThirdPartyCodeSandbox();
            default:
                return new ExampleCodeSandbox();
        }
    }

}
