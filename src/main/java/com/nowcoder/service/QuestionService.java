package com.nowcoder.service;

import com.nowcoder.dao.QuestionDAO;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import org.w3c.dom.html.HTMLUListElement;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;
    @Autowired
    SensitiveService sensitiveService;
    @Autowired
    HostHolder hostHolder;
    public List<Question> getLatestQuestions(int offset,int limit){
        return questionDAO.selectLatestQuestions(offset,limit);
    }

    public void addQuestion(String title,String content){
        title=HtmlUtils.htmlEscape(title);
        content=HtmlUtils.htmlEscape(content);
        title=sensitiveService.filter(title);
        content=sensitiveService.filter(content);
        Question question=new Question();
        question.setContent(content);
        question.setTitle(title);
        question.setComment_count(0);
        question.setCreated_date(new Timestamp(System.currentTimeMillis()));
        question.setUser_id(hostHolder.getUser().getId());
        questionDAO.addQuestion(question);
    }

    public List<Question> getQuestionsByUser(int user_id,int offset,int limit){
        return questionDAO.selectQuestionsByUser(user_id,offset,limit);
    }
}
