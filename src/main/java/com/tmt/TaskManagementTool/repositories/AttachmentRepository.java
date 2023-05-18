package com.tmt.TaskManagementTool.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.TaskManagementTool.models.Attachment;

@Repository
public interface AttachmentRepository extends MongoRepository<Attachment, ObjectId>{
    
}
