package com.lc.oj.judge;

import com.lc.oj.model.entity.QuestionSubmit;
import com.lc.oj.model.vo.QuestionSubmitVO;

/**
 * @Author Lc
 * @Date 2023/8/22
 * @PackageName: com.lc.oj.judge
 * @ClassName: JudgeService
 * @Description:
 */

public interface JudgeService {
    QuestionSubmit doJudge(long questionSubmitId);
}
