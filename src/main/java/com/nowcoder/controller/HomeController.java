package com.nowcoder.controller;


import com.nowcoder.model.Question;
import com.nowcoder.model.ViewObject;
import com.nowcoder.service.QuestionService;
import com.nowcoder.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.swing.text.html.ObjectView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class HomeController {
    private static final Logger logger=LoggerFactory.getLogger(HomeController.class);
    @Autowired
    UserService userService;
    @Autowired
    QuestionService questionService;
    @RequestMapping(path = {"/","/index"},method = RequestMethod.GET)
    public String index(Map<String,Object> map){
        List<Question> questionList=questionService.getLatestQuestions(0,10);
        List<ViewObject> vbs=new ArrayList<ViewObject>();
        for(Question question : questionList){
            ViewObject vb=new ViewObject();
            vb.set("question",question);
            vb.set("user",userService.getUser(question.getUser_id()));
            vbs.add(vb);
        }
        map.put("vos",vbs);
        return "index";
    }

    @RequestMapping(path = {"/user/{user_id}"})
    public String userDetails(Map<String,Object> map,
                              @PathVariable("user_id") int user_id
                              ){
        List<Question> questions=questionService.getQuestionsByUser(user_id,0,10);
        List<ViewObject> vos=new ArrayList<>();
        for(Question question:questions){
            ;ViewObject vo=new ViewObject();
             vo.set("question",question);
             vo.set("user",userService.getUser(question.getUser_id()));
             vos.add(vo);
        }
        map.put("vos",vos);
        return "index";
    }
}
