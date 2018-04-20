package com.nowcoder.service;

import com.nowcoder.dao.CommentDAO;
import com.nowcoder.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentDAO commentDAO;

    public List<Comment> getCommentsByEntity(int entity_type,int entity_id){
        return commentDAO.getCommentsByEntity(entity_type,entity_id);
    }

    public void deleteComment(int id){
        commentDAO.updateCommentStatus(id,1);
    }

    public void addComment(Comment comment){
         commentDAO.addComment(comment);
    }
}
