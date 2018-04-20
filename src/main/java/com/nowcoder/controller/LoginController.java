package com.nowcoder.controller;

import com.nowcoder.model.User;
import com.nowcoder.service.UserService;
import com.nowcoder.utils.WendaUtils;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(path = {"/reg"}, method = RequestMethod.POST)
    public String reg(Map<String, Object> map,
                      @RequestParam("password") String password,
                      @RequestParam("username") String username
    ){
            Map<String,String> back=userService.register(username,password);
            if(back.containsKey("msg")){
                map.put("msg",back.get("msg"));
            }else{
                map.put("msg","注册成功!");
            }
             return "login";
     }

     @RequestMapping(path = {"/reglogin"})
     public String reglogin(Map<String,Object> map,
                            @RequestParam(value = "next" ,required = false) String next
                            ){
        if(next!=null){
            map.put("next",next);
        }
        return "login";
     }

     @RequestMapping(path = {"/login"},method = RequestMethod.POST)
     public String login(Map<String,Object> map,
                          @RequestParam("username") String username,
                          @RequestParam("password")  String password,
                           @RequestParam("rememberme") boolean rememberme,
                         @RequestParam(value ="next", required = false) String next,
                           HttpServletResponse httpServletResponse
     ){
        Map<String,Object> back=userService.login(username,password);
        if(back.containsKey("ticket")){
            Cookie cookie = new Cookie("ticket",(String) back.get("ticket"));
            cookie.setPath("/");
            if(rememberme==true){
                cookie.setMaxAge(5*24*3600);
            }
            httpServletResponse.addCookie(cookie);
            if(next!=null){
                return "redirect:"+next;
            }
            return "redirect:/";
        }else{
            map.put("msg",back.get("msg"));
            return "login";
        }
     }

     @RequestMapping(path = {"/logout"})
     public String logout(@CookieValue("ticket") String ticket){
         userService.logout(ticket);
         return "redirect:/";
     }
}
