package ssm.dao;

import ssm.pojo.Question;

import java.util.List;

public interface QuestionDAO {

    void addQuestion(Question question);

    List<Question> getQuestionList(Question question);

}
