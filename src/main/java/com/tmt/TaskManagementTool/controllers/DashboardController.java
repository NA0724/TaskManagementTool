package com.tmt.TaskManagementTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmt.TaskManagementTool.dtos.DasboardData;
import com.tmt.TaskManagementTool.models.Notification;
import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.AuthService;
import com.tmt.TaskManagementTool.services.NotificationService;
import com.tmt.TaskManagementTool.services.TaskService;
import com.tmt.TaskManagementTool.services.UserService;
import com.tmt.TaskManagementTool.util.GeneratePdfReportUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/home")
@Slf4j
public class DashboardController {


    @Autowired
    private  GeneratePdfReportUtil generatePdfReportUtil;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
	private AuthService authService;
    
    
    public ResponseEntity<String> getDashboard(HttpServletRequest request){
        String auth = request.getParameter("Authorization");
        log.info(auth);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", auth);
        ResponseEntity<String> responseEntity;
        responseEntity = ResponseEntity.ok().headers(headers).body("dashboard");
        User user = authService.getCurrentUser(auth);
        log.info(user.getFirstname()+" " +user.getLastname() + " has logged in successfully");
        return responseEntity;
    }
    
    @GetMapping("/dashboard")
    public ResponseEntity<DasboardData> getDashboardPage(HttpServletRequest request, String username){
        //TODO call the methods for dashboard - all tasks by status for user and count of tasks

        //get https header from request
        String auth = request.getParameter("Authorization");
        User user = authService.getCurrentUser(auth);
        log.info(user.getFirstname()+" " +user.getLastname() + " has logged in successfully");
        ObjectMapper objectMapper = new ObjectMapper();
        DasboardData dashboardData = new DasboardData();
        //JSONObject jsonObject = objectMapper.writeValueAsString(objectMapper)
        List<Task> tasks = taskService.getAllTasksAssignedToUser(username);
        List<Notification> notifications = notificationService.getAllNotificationsByUserId(username);
        int newTasksCount = taskService.countTaskByStatus("New");
        int inProgressTasksCount = taskService.countTaskByStatus("In Progress");
        int completedTasksCount = taskService.countTaskByStatus("Completed");
        dashboardData.setNewTaskCount(newTasksCount);
        dashboardData.setCompletedTasksCount(completedTasksCount);
        dashboardData.setInProgressTasksCount(inProgressTasksCount);
        dashboardData.setTasks(tasks);
        dashboardData.setNotifications(notifications);
        return new ResponseEntity<DasboardData>(dashboardData, HttpStatus.OK);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<User> getMyProfile(String username){
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
        
    }

    @GetMapping("/pdfreport")
    public void getPDFReport(){
        generatePdfReportUtil.generatePdfReport();
    }

     /*
     * get all notifications from the database for user
     */
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotificationForUser(String username){
        return new ResponseEntity<List<Notification>>(notificationService.getAllNotificationsByUserId(username), HttpStatus.OK);
    }


    
}
