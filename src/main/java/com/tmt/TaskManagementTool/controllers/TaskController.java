package com.tmt.TaskManagementTool.controllers;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmt.TaskManagementTool.models.Attachment;
import com.tmt.TaskManagementTool.models.Comment;
import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.AuthService;
import com.tmt.TaskManagementTool.services.TaskService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    public AuthService authService;

    /**
     * Get all the tasks created
     * 
     * @return
     */
    @GetMapping("/all-tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
    }

    /**
     * Create a new task
     * 
     * @param requestBody
     * @param session
     * @return
     */
    @PostMapping("/create-task")
    public ResponseEntity<Task> createTask(@RequestBody String requestBody, HttpSession session) {
        User loggedInUser = authService.getCurrentUser(session);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            Task newTask = new Task();
            newTask.setCreatedBy(loggedInUser.getUsername());
            newTask.setTid(jsonNode.get("tid").asText());
            newTask.setTitle(jsonNode.get("title").asText());
            newTask.setDescription(jsonNode.get("description").asText());
            newTask.setStatus(jsonNode.get("status").asText());
            newTask.setPriority(jsonNode.get("priority").asText());
            newTask.setAssignedTo(jsonNode.get("assignedTo").asText());
            newTask.setDueDate(LocalDate.parse(jsonNode.get("dueDate").asText()));
            newTask.setTaskType(jsonNode.get("taskType").asText());
            newTask.setTaskCategory(jsonNode.get("taskCategory").asText());
            JsonNode commentsNode = jsonNode.get("comments");
            if (commentsNode != null && commentsNode.isArray()) {
                List<Comment> comments = new ArrayList<>();
                for (JsonNode commentNode : commentsNode) {
                    Comment comment = new Comment();
                    comment.setBody(commentNode.get("body").asText());
                    comment.setCreatedBy(loggedInUser.getUsername());
                    comments.add(comment);
                }
                newTask.setComments(comments);
            }
            JsonNode attachmentsNode = jsonNode.get("attachments");
            if (attachmentsNode != null && attachmentsNode.isArray()) {
                List<Attachment> attachments = new ArrayList<>();
                for (JsonNode attachmentNode : attachmentsNode) {
                    Attachment attachment = new Attachment();
                    attachment.setFileName(attachmentNode.get("body").asText());
                    attachments.add(attachment);
                }
                newTask.setAttachments(attachments);
            }
            return new ResponseEntity<Task>(taskService.createTask(newTask), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating a new task", e);
            return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update Task
     * 
     * @param tid
     * @param requestBody
     * @return
     */
    @PutMapping("/edit-task/{tid}")
    public ResponseEntity<Task> updateTask(@PathVariable String tid, @RequestBody String requestBody) {
        Task task = taskService.getTaskByTid(tid);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            task.setTitle(jsonNode.get("tid").asText());
            task.setDescription(jsonNode.get("description").asText());
            task.setPriority(jsonNode.get("priority").asText());
            task.setStatus(jsonNode.get("status").asText());
            task.setDueDate(LocalDate.parse(jsonNode.get("dueDate").asText()));
            task.setTaskType(jsonNode.get("taskType").asText());
            task.setTaskCategory(jsonNode.get("taskCategory").asText());
            task.setAssignedTo(jsonNode.get("assignedTo").asText());
            taskService.updateTask(task);
            return new ResponseEntity<Task>(HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating task", e);
            return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete Task
     * 
     * @param tid
     */
    @GetMapping("/delete-task/{tid}")
    public void deleteRole(@PathVariable String tid) {
        taskService.deleteTask(tid);
    }

    /**
     * Upload files to task
     * 
     * @param taskId
     * @param file
     * @return
     */
    @PostMapping("/{taskId}/upload")
    public ResponseEntity<String> uploadTaskAttachments(@PathVariable("taskId") String taskId,
            @RequestParam("file") MultipartFile file) {
        try {
            Attachment attachment = new Attachment();
            attachment.setTaskid(taskId);
            attachment.setFileName(file.getOriginalFilename());
            attachment.setFile(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
            taskService.saveAttachment(taskId, attachment);
            return new ResponseEntity<String>("Successfully uploaded", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error uploading attachment", e);
            return new ResponseEntity<String>("Could not upload attachment", HttpStatus.EXPECTATION_FAILED);
        }
    }

    /**
     * Get all attachments for task
     * 
     * @param taskId
     * @return
     */
    @PostMapping("/{taskId}/attachments")
    public ResponseEntity<String> getAllAttachmentsForTask(@PathVariable("taskId") String taskId) {
        try {
            Task task = taskService.getTaskByTid(taskId);
            List<Attachment> attachments = task.getAttachments();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Could not upload attachment", HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<String>("Successfully uploaded", HttpStatus.OK);
    }

    /**
     * Add a new comment to task
     * 
     * @param taskId
     * @param session
     * @param requestBody
     * @return
     */
    @PostMapping("/{taskId}/add-comment")
    public ResponseEntity<Comment> addComment(@PathVariable("taskId") String taskId, HttpSession session,
            @RequestBody String requestBody) {
        User user = authService.getCurrentUser(session);
        Task task = taskService.getTaskByTid(taskId);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(requestBody);
            Comment comment = new Comment();
            List<Comment> comments = task.getComments();
            comment.setBody(jsonNode.get("body").asText());
            comment.setTaskId(taskId);
            comment.setCreatedBy(user.getUsername());
            comments.add(comment);
            taskService.createCommentForTask(task, comment);
            return new ResponseEntity<Comment>(comment, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error creating comment:", e);
            return new ResponseEntity<Comment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Find all comments for a given task
     * 
     * @param taskId
     * @param requestBody
     * @return
     */
    @PostMapping("/{taskid}/all-comments")
    public ResponseEntity<List<Comment>> addComment(@PathVariable("taskId") String taskId,
            @RequestBody String requestBody) {
        return new ResponseEntity<List<Comment>>(taskService.getCommentsForTask(taskId), HttpStatus.OK);
    }

    /**
     * Filter tasks based on status or duedate or both
     * 
     * @param status
     * @param dueDate
     * @return
     */
    @GetMapping("/filter")
    public ResponseEntity<List<Task>> getAllTasksByStatusAndDueDate(
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "duedate", required = false) String dueDate) {
        if (status != null && dueDate != null) {
            // Both status and dueDate are provided
            LocalDate parsedDueDate = LocalDate.parse(dueDate);
            List<Task> tasks = taskService.filterTaskByStatusAndDueDate(status, parsedDueDate);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else if (status != null) {
            // Only status is provided
            List<Task> tasks = taskService.findTaskByStatus(status);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else if (dueDate != null) {
            // Only dueDate is provided
            LocalDate parsedDueDate = LocalDate.parse(dueDate);
            List<Task> tasks = taskService.findTaskByDueDate(parsedDueDate);
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        } else {
            // No parameters provided, return an error response or default result
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> uploadTaskAttachments(@PathVariable("taskId") String taskId) {
        Task task = taskService.getTaskByTid(taskId);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

}
