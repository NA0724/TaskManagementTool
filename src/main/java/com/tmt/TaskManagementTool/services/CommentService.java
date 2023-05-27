package com.tmt.TaskManagementTool.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Comment;
import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.repositories.CommentRepository;
import com.tmt.TaskManagementTool.repositories.TaskRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Comment> getAttachment(ObjectId id){
        return commentRepository.findById(id);
    }

    public List<Comment> getAllCommentsByTaskId(String taskId){
        return null;
    }
    
    public void createCommentForTask(String taskid, Comment comment){
        Optional<Task> taskOptional = taskRepository.findTaskByTid(taskid);
        Task task = taskOptional.orElseThrow(()-> new IllegalArgumentException("Task not found: " + taskid));

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm");
        comment.setCreatedAt(dateTime.format(formatter));
        comment.setTaskId(taskid);
        commentRepository.insert(comment);

        List<Comment> comments = task.getComments();
        comments.add(comment);
        taskRepository.save(task);
    }
}
