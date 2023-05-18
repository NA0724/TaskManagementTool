package com.tmt.TaskManagementTool.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Attachment;
import com.tmt.TaskManagementTool.repositories.AttachmentRepository;

@Service
public class AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    public Optional<Attachment> getAttachment(ObjectId id){
        return attachmentRepository.findById(id);
    }

    public List<Attachment> getAllAttachmentsByTaskId(String taskId){
        return null;
    }
    
}
