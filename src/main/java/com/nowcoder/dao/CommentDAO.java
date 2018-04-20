package com.nowcoder.dao;

import com.nowcoder.model.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;

import java.util.List;

@Mapper
public interface CommentDAO {
    String TABLE_NAME="comment";
    String TABLE_FIELDS="content , user_id , created_date , entity_id , entity_type , status";

    @Insert({" insert into ",TABLE_NAME , "(" ,TABLE_NAME , ")  values(#{content},#{user_id},#{created_date},#{entity_id},#{entity_type},#{status}))"})
    int addComment(Comment comment);

    @Select({"select count(id) from" , TABLE_NAME , "where entity_type=#{entity_type} and entity_id=#{entity_id}"})
    int getCommentCount(@Param("entity_type") int entity_type,@Param("entity_id") int entity_id);

    @Select({"select * from" ,TABLE_NAME , "where entity_type = #{entity_type} and entity_id = #{entity_id} order by created_time DESC"})
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "content" ,column = "content"),
            @Result(property = "user_id" ,column = "user_id"),
            @Result(property = "created_date" ,column = "created_date"),
            @Result(property = "entity_id" ,column = "entity_id"),
            @Result(property = "entity_type" ,column = "entity_type"),
            @Result(property = "status" ,column = "status")
    })
    List<Comment> getCommentsByEntity(@Param("entity_type") int entity_type,@Param("entity_id") int entity_id);

    @Update({"update  ",TABLE_NAME, "set status =#{status} where id= #{id}"})
    void updateCommentStatus(@Param("id") int id,@Param("status") int status);


}
