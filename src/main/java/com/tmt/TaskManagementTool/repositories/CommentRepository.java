package com.tmt.TaskManagementTool.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.TaskManagementTool.models.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, ObjectId>{
    
}
