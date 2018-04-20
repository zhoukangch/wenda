package com.nowcoder.service;

import com.nowcoder.dao.LoginTicketDAO;
import com.nowcoder.dao.UserDAO;
import com.nowcoder.model.LoginTicket;
import com.nowcoder.model.User;
import com.nowcoder.utils.WendaUtils;
import freemarker.template.utility.StringUtil;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    LoginTicketDAO loginTicketDAO;

    public void logout(String ticket){
        loginTicketDAO.updateStatusByTicket(1,ticket);
        return;
    }

    public User getUser(String username){
        return userDAO.selectUserByName(username);
    }

    public Map<String,Object> login(String username,String password){
        Map<String,Object> map=new HashMap<>();
        if(StringUtils.isEmpty(username)||StringUtils.containsWhitespace(username)){
            map.put("msg","用户名不合法");
            return map;
        }
        if(StringUtils.isEmpty(password)||StringUtils.containsWhitespace(password)){
            map.put("msg","密码不合法");
            return map;
        }
        User user=userDAO.selectUserByName(username);
        if(user==null) {
            map.put("msg","用户名不存在");
            return map;
        }
        if(user.getPassword().equals(WendaUtils.md5(password+user.getSalt()))){
            LoginTicket loginTicket=new LoginTicket();
            loginTicket.setUser_id(user.getId());
            loginTicket.setStatus(0);
            loginTicket.setExpired(new Timestamp(System.currentTimeMillis()+100*24*3600*1000));
            loginTicket.setTicket(UUID.randomUUID().toString().replace("-",""));
            loginTicketDAO.addLoginTicket(loginTicket);
            map.put("ticket",loginTicket.getTicket());
            return map;
        }else{
            map.put("msg","密码错误");
            return map;
        }
    }
    public User getUser(int id){
        return userDAO.selectUserById(id);
    }

    /**
     * function:provide registration service
     * @param username
     * @param password
     * @return
     */
    public Map<String,String> register(String username,String password){
        Map<String,String> map=new HashMap<>();
        //org.springframework.utils.StringUtils
        if(StringUtils.isEmpty(username)){
            map.put("msg","username is empty ! please input again!");
            return map;
        }
        if(StringUtils.containsWhitespace(username)){
            map.put("msg","username contains whitespace ! please input again!");
            return map;
        }
        if(StringUtils.isEmpty(password)){
            map.put("msg","password is empty! please input again!");
            return map;
        }
        if(StringUtils.containsWhitespace(password)){
            map.put("msg","password contains whitespace! please input again!");
            return map;
        }
        User user=userDAO.selectUserByName(username);
        if(user!=null){
            map.put("msg","this username has been registered! please input again!");
            return map;
        }else{
            user=new User();
            user.setName(username);
            //UUID
            user.setSalt(UUID.randomUUID().toString().substring(0,5));
            user.setHead_url(String.format("http://images.nowcoder.com/head/%dt.png",new Random().nextInt(1000)));
            user.setPassword(WendaUtils.md5(password+user.getSalt()));
            userDAO.addUser(user);
            return map;
        }
    }
}
