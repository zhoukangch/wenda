package com.nowcoder.intercepter;

import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.HostHolder;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

@Component
public class PassportIntercepter implements HandlerInterceptor {
    @Autowired
    LoginTicketDAO loginTicketDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
       String ticket=null;
       if(request.getCookies()!=null){
           for(Cookie cookie : request.getCookies()){
               if(cookie.getName().equals("ticket")){
                   ticket=cookie.getValue();
                   break;
               }
           }
       }
       if(ticket==null){
           return true;
       }else{
           LoginTicket loginTicket =loginTicketDAO.selectByTicket(ticket);
           if(loginTicket==null||loginTicket.getExpired().before(new Timestamp(System.currentTimeMillis()))||loginTicket.getStatus()!=0){
               return true;
           }else{
               User user=userDAO.selectUserById(loginTicket.getUser_id());
               hostHolder.setUsers(user);
               return true;
           }
       }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
          if(modelAndView!=null&&hostHolder.getUser()!=null){
              modelAndView.addObject("user",hostHolder.getUser());
          }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
          hostHolder.remove();
    }
}
