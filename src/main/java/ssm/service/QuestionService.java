package ssm.service;

import com.github.pagehelper.PageInfo;
import ssm.pojo.Question;

public interface QuestionService {
    void addQuestion(Question question);

    PageInfo<Question> getQuestionList(Question question, int pageNo, int pageSize);


}
