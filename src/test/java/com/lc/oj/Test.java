package com.lc.oj;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;

import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1 2", "3 4");
        String jsonStr = JSONUtil.toJsonStr(list);

        HttpResponse execute = HttpUtil.createPost("localhost:8090/executeCode").body(jsonStr).execute();
        String body = execute.body();
        System.out.println(body);
    }
}
