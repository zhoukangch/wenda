package com.nowcoder.model;

import org.springframework.stereotype.Component;

//Component
@Component
public class HostHolder {
     private static ThreadLocal<User> users=new ThreadLocal<>();

     public User getUser(){
         return users.get();
     }
     public void setUsers(User user){
         users.set(user);
     }
     public void remove(){
         users.remove();
     }
}