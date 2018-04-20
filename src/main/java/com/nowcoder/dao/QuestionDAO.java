package com.nowcoder.dao;

import com.nowcoder.model.Question;
import org.apache.ibatis.annotations.*;
import org.aspectj.weaver.patterns.TypePatternQuestions;

import java.util.List;

@Mapper
public interface QuestionDAO {
    String TABLE_NAME = "question";
    String INSERT_FIELDS = " title,content,user_id,created_date,comment_count ";
    String SELECT_FIELDS = "id," + INSERT_FIELDS;

    @Insert({"insert into ", TABLE_NAME, "(", INSERT_FIELDS, ") values(#{title},#{content},#{user_id},#{created_date},#{comment_count})"})
    int addQuestion(Question question);

     @Select({"select  *  from ", TABLE_NAME ,"  ORDER BY id DESC  LIMIT #{offset},#{limit}"})
     @Results({
             @Result(property = "id" ,column="id"),
             @Result(property = "title" ,column="title"),
             @Result(property = "content" ,column="content"),
             @Result(property = "user_id" ,column="user_id"),
             @Result(property = "created_date" ,column="created_date"),
             @Result(property = "comment_count" ,column="comment_count")
     })
     List<Question> selectLatestQuestions(@Param("offset") int offset,@Param("limit") int limit);

     @Select({"select * from ", TABLE_NAME ,"where user_id=#{user_id} order by created_date DESC LIMIT #{offset},#{limit}"})
     @Results({
             @Result(property = "id" , column = "id"),
             @Result(property = "title" , column = "title"),
             @Result(property = "content" , column = "content"),
             @Result(property = "user_id" , column = "user_id"),
             @Result(property = "created_date" , column = "created_date"),
             @Result(property = "comment_count" , column = "comment_count"),
     })
     List<Question> selectQuestionsByUser(@Param("user_id") int user_id,
                                          @Param("offset") int offset,
                                          @Param("limit") int limit
     );
}