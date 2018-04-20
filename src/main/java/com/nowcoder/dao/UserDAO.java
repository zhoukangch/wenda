package com.nowcoder.dao;

import com.nowcoder.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDAO {

    String TABLE_NAME="user";
    String INSERT_USER_FIELDS=" name,password,salt,head_url ";
    String SELECT_FIELDS="id ,"+INSERT_USER_FIELDS;

    @Insert({"insert into", TABLE_NAME , " (",INSERT_USER_FIELDS,
            ") values(#{name},#{password},#{salt},#{head_url})"})
    int addUser(User user);

    @Select({"select ",SELECT_FIELDS,"from ", TABLE_NAME ," where id = #{id}"})
    @Results({
            @Result(property = "id" ,column ="id"),
            @Result(property = "name" , column = "name"),
            @Result(property= "password",column ="password"),
            @Result(property= "salt",column="salt"),
            @Result(property="head_url",column="head_url")
    })
    User selectUserById(int id);

    @Update({"update ",TABLE_NAME,"set password=#{password} where id = #{id}"})
    void updatePassword(User user);

    @Delete({"delete from " ,TABLE_NAME,"where id = #{id}"})
    void deleteUserById(int id);

    //@Select({"select * from user "})
    List<User> selectALLUsers();

    @Select({"select * from ",TABLE_NAME ," where name = #{username}"})
    @Results({
            @Result(property = "id" ,column ="id"),
            @Result(property = "name" , column = "name"),
            @Result(property= "password",column ="password"),
            @Result(property= "salt",column="salt"),
            @Result(property="head_url",column="head_url")
    })
    User selectUserByName(@Param("username") String username);

}
