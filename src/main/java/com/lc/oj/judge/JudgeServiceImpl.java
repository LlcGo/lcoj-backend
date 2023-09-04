package com.lc.oj.judge;

import cn.hutool.json.JSONUtil;
import com.lc.oj.common.ErrorCode;
import com.lc.oj.exception.BusinessException;
import com.lc.oj.judge.codesandbox.CodeSandbox;
import com.lc.oj.judge.codesandbox.CodeSandboxFactory;
import com.lc.oj.judge.codesandbox.CodeSandboxProxy;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.lc.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.lc.oj.judge.strategy.JudgeContext;
import com.lc.oj.model.dto.question.JudgeCase;
import com.lc.oj.judge.codesandbox.model.JudgeInfo;
import com.lc.oj.model.entity.Question;
import com.lc.oj.model.entity.QuestionSubmit;
import com.lc.oj.model.enums.QuestionSubmitStatusEnum;
import com.lc.oj.service.QuestionService;
import com.lc.oj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Lc
 * @Date 2023/8/22
 * @PackageName: com.lc.oj.judge
 * @ClassName: JudgeServiceImpl
 * @Description:
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManger judgeManger;

    @Value("${codeSandbox.type}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        //传入题目的提交id，获取对应的题目，提交信息（包含代码，编程语言等）
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "没有发现该提交的信息");
        }
        Integer status = questionSubmit.getStatus();
        Long questionId = questionSubmit.getQuestionId();

        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "没有该题目");
        }

        //如果传入的状态不是等待中就不需要继续执行
        if (!status.equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "题目不是等待允许状态");
        }
        //开始执行沙箱前改变判题状态
        QuestionSubmit updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionSubmitService.updateById(updateQuestionSubmit);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目状态更新出错");
        }
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();


        //获取输入信息
        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCases = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCases.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        //调用沙箱，获取执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        CodeSandboxProxy codeSandboxProxy = new CodeSandboxProxy(codeSandbox);
        ExecuteCodeResponse executeCodeResponse = codeSandboxProxy.executeCode(ExecuteCodeRequest.builder()
                .coed(code).language(language).input(inputList).build()
        );
        List<String> output = executeCodeResponse.getOutput();
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();


        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(judgeInfo);
        judgeContext.setInput(inputList);
        judgeContext.setOutput(output);
        judgeContext.setJudgeCase(judgeCases);
        judgeContext.setQuestion(question);
        judgeContext.setQuestionSubmit(questionSubmit);

        JudgeInfo returnJudgeInfo = judgeManger.doJudge(judgeContext);

        //根据沙箱的执行结果 设置题目的判题状态和信息
        updateQuestionSubmit = new QuestionSubmit();
        updateQuestionSubmit.setId(questionSubmitId);
        updateQuestionSubmit.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        updateQuestionSubmit.setJudgeInfo(JSONUtil.toJsonStr(returnJudgeInfo));
        boolean b = questionSubmitService.updateById(updateQuestionSubmit);
        if (!b) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return questionSubmitService.getById(questionSubmitId);
    }
}
