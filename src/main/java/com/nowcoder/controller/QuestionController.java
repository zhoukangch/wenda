package com.nowcoder.controller;

import com.nowcoder.service.QuestionService;
import com.nowcoder.service.SensitiveService;
import com.nowcoder.utils.WendaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;
    @Autowired
    SensitiveService sensitiveService;

    @RequestMapping(path = {"/question/{qid}"})
    public String questionDetails(){
        return "detail";
    }

    @RequestMapping(value = {"/question/add"},method = RequestMethod.POST)
    @ResponseBody
    public String addQuestion(
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ){
         if(title==null||title.length()==0||content==null||content.length()==0){
             return WendaUtils.getJSONString(1);
         }
         questionService.addQuestion(title,content);
         return WendaUtils.getJSONString(0);
    }
}
