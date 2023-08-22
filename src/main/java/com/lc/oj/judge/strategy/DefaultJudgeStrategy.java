package com.lc.oj.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.lc.oj.model.dto.question.JudgeCase;
import com.lc.oj.model.dto.question.JudgeConfig;
import com.lc.oj.model.dto.questionsubmit.JudgeInfo;
import com.lc.oj.model.entity.Question;
import com.lc.oj.model.enums.JudgeInfoMessageEnum;

import java.util.List;

/**
 * @Author Lc
 * @Date 2023/8/22
 * @PackageName: com.lc.oj.judge.strategy
 * @ClassName: DefaultJudgeStragegy
 * @Description:
 */

public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext context) {
        JudgeInfo judgeInfo = context.getJudgeInfo();
        List<String> input = context.getInput();
        List<String> output = context.getOutput();
        Question question = context.getQuestion();
        List<JudgeCase> judgeCase = context.getJudgeCase();

        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        judgeInfo.setMemory(memory);
        judgeInfo.setTime(time);

        JudgeInfoMessageEnum judgeInfoMessage = JudgeInfoMessageEnum.ACCEPTED;
        //有输入就一定要有输出
        if (output.size() != input.size()){
            judgeInfoMessage = JudgeInfoMessageEnum.RUNTIME_ERROR;
            judgeInfo.setMessage(judgeInfoMessage.getText());
            return judgeInfo;
        }

        //判断结果是否相同
        for (int i = 0; i < judgeCase.size(); i++) {
            JudgeCase judgeCase1 = judgeCase.get(i);
            if(!judgeCase1.getOutput().equals(output.get(i))){
                judgeInfoMessage = JudgeInfoMessageEnum.RUNTIME_ERROR;
                judgeInfo.setMessage(judgeInfoMessage.getText());
                return judgeInfo;
            }
        }

        String judgeConfig = question.getJudgeConfig();
        JudgeConfig judgeConfig1 = JSONUtil.toBean(judgeConfig, JudgeConfig.class);
        Long needTimeLimit = judgeConfig1.getTimeLimit();
        Long needMemoryLimit = judgeConfig1.getMemoryLimit();


        //运行之后需要的时间
        if(memory > needMemoryLimit){
            judgeInfoMessage = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoMessage.getText());
            return judgeInfo;
        }
        if(time > needTimeLimit){
            judgeInfoMessage = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoMessage.getText());
            return judgeInfo;
        }
        judgeInfo.setMessage(judgeInfoMessage.getText());
        return judgeInfo;
    }
}
