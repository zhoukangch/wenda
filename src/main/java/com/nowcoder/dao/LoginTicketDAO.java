package com.nowcoder.dao;

import com.nowcoder.model.LoginTicket;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginTicketDAO {
    String TABLE_NAME="loginticket";
    String INSERT_FIELDS="user_id , ticket , expired , status ";
    @Select({"select  *  from ", TABLE_NAME , "where ticket = #{ticket}"})
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "user_id" ,column = "user_id"),
            @Result(property = "ticket" ,column = "ticket"),
            @Result(property = "expired" ,column = "expired"),
            @Result(property = "status" ,column = "status"),
    })
    LoginTicket selectByTicket(@Param("ticket") String ticket);

    @Update({"update ", TABLE_NAME , " set status = #{status} where user_id = #{user_id}" })
    void updateStatusByUser_id(@Param("status") int status,@Param("user_id") int user_id);

    @Update({"update " , TABLE_NAME, " set status = #{status} where ticket =#{ticket}"})
    void updateStatusByTicket(@Param("status") int status,@Param("ticket") String ticket);

    @Insert({"insert into", TABLE_NAME, "(",INSERT_FIELDS, ") values(#{user_id}," +
            "#{ticket},#{expired},#{status})" })
    void addLoginTicket(LoginTicket loginTicket);
}

