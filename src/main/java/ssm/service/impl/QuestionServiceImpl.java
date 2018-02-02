package ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssm.dao.QuestionDAO;
import ssm.pojo.Question;
import ssm.service.QuestionService;

import java.util.List;
@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionDAO questionDAO;
    @Override
    public void addQuestion(Question question) {
        questionDAO.addQuestion(question);
    }

    @Override
    public PageInfo<Question> getQuestionList(Question question, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize, "UPDATE_TIME DESC");
        List<Question> questions = questionDAO.getQuestionList(question);
        PageInfo<Question> pageInfo = new PageInfo<>(questions, 3);
        return pageInfo;
    }


}
