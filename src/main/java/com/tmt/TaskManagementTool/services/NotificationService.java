package com.tmt.TaskManagementTool.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Notification;
import com.tmt.TaskManagementTool.repositories.NotificationRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Optional<Notification> getNotification(ObjectId id){
        return notificationRepository.findById(id);
    }

    public List<Notification> getAllNotificationsByTaskId(String taskId){
        return null;
    }

    public List<Notification> getAllNotificationsByUserId(String userId){
        return null;
    }
    
}
