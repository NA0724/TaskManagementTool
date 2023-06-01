package com.tmt.TaskManagementTool.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tmt.TaskManagementTool.models.Attachment;

@Repository
public interface AttachmentRepository extends MongoRepository<Attachment, String>{
    List<Attachment> findAttachmentsByTaskid(String taskid);
    
}
