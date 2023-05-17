package com.tmt.TaskManagementTool.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Comment;
import com.tmt.TaskManagementTool.repositories.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Optional<Comment> getAttachment(ObjectId id){
        return commentRepository.findById(id);
    }

    public List<Comment> getAllCommentsByTaskId(String taskId){
        return null;
    }
    
}
