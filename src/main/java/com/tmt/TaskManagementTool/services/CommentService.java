package com.tmt.TaskManagementTool.services;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Comment;
import com.tmt.TaskManagementTool.models.Notification;
import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.repositories.CommentRepository;
import com.tmt.TaskManagementTool.repositories.NotificationRepository;
import com.tmt.TaskManagementTool.repositories.TaskRepository;
import com.tmt.TaskManagementTool.util.GenericUtil;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private GenericUtil genericUtil;

    public Optional<Comment> getAttachment(ObjectId id){
        return commentRepository.findById(id);
    }

    public List<Comment> getAllCommentsByTaskId(String taskId){
        return null;
    }
    
    public void createCommentForTask(String taskid, Comment comment){
        Optional<Task> taskOptional = taskRepository.findTaskByTid(taskid);
        Task task = taskOptional.orElseThrow(()-> new IllegalArgumentException("Task not found: " + taskid));

        comment.setCreatedAt(genericUtil.getCurrentDateTime());
        comment.setTaskId(taskid);
        //TODO add createdby to comment
        commentRepository.insert(comment);

        List<Comment> comments = task.getComments();
        comments.add(comment);

        Notification notification = notificationService.createNotificationForTask(taskid);
        notification.setBody("A new comment was added by " + comment.getCreatedBy() + ".");
        notification.setUserId(task.getAssignedTo());
        notificationService.saveNotification(notification);

        taskRepository.save(task);

    }
}
