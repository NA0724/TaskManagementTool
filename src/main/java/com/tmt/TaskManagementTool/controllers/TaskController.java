package com.tmt.TaskManagementTool.controllers;

import java.util.List;

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
import com.tmt.TaskManagementTool.models.Comment;
import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.services.TaskService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    @Autowired
    private TaskService taskService;
    
    /*
     * create a new user in the database
     */
    @PostMapping("/create-task")
    public ResponseEntity<Task> createTask(@RequestBody String requestBody, HttpSession session){
        ObjectMapper objectMapper = new ObjectMapper();
		try {
            String username = session.getAttribute("user").toString();
			JsonNode jsonNode = objectMapper.readTree(requestBody);
			Task newTask = new Task();
            newTask.setCreatedBy(username);
            return new ResponseEntity<Task>(taskService.createTask(newTask), HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println("@>@ Exception occurred in creating new user : " + e); 
        }
        return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * search and update a user in the database by id
     */
    @PutMapping("/edit-task/{tid}")
    public ResponseEntity<Task> updateTask(@PathVariable String tid, @RequestBody String requestBody){
        Task task = taskService.getTaskByTid(tid);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
            //map data from jsonnode to task object
            taskService.updateTask(task);
            return new ResponseEntity<Task>(HttpStatus.OK);
			// TODO update form
        }catch (Exception e) {
            System.out.println("@>@ Exception occurred in updating user : " + e); 
        }
        return new ResponseEntity<Task>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/delete-task/{tid}")
    public void deleteRole(@PathVariable String tid){
        taskService.deleteTask(tid);
    }

    @PostMapping("/{taskId}/attachments")
    public ResponseEntity<String> uploadTaskAttachments(@PathVariable("taskId") String taskId, @RequestParam("files") MultipartFile file) {
        Task task = taskService.getTaskByTid(taskId);
        List<MultipartFile> attachments = task.getAttachments();
        attachments.add(file);
        taskService.updateTask(task);
        return ResponseEntity.ok("Attachments uploaded successfully.");
    }

    @PostMapping("/{taskid}/add-comment")
    public ResponseEntity<Comment> addComment(@PathVariable("taskId") String taskId, HttpSession session, @RequestBody String requestBody){
        String username = session.getAttribute("user").toString();
        Task task = taskService.getTaskByTid(taskId);
        Comment comment = new Comment();
        List<Comment> comments = task.getComments();
        comment.setCreatedBy(username);
        comments.add(comment);
        taskService.createCommentForTask(taskId, comment);
        return new ResponseEntity<Comment>(comment, HttpStatus.OK);
    }

    @PostMapping("/{taskid}/all-comments")
    public ResponseEntity<List<Comment>> addComment(@PathVariable("taskId") String taskId, @RequestBody String requestBody){
        return new ResponseEntity<List<Comment>>(taskService.getAllCommentsByTaskId(taskId), HttpStatus.OK);
    }
}

