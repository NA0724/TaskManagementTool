package com.tmt.TaskManagementTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tmt.TaskManagementTool.dtos.DasboardData;
import com.tmt.TaskManagementTool.models.Notification;
import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.NotificationService;
import com.tmt.TaskManagementTool.services.TaskService;
import com.tmt.TaskManagementTool.services.UserService;
import com.tmt.TaskManagementTool.util.GeneratePdfReportUtil;

@RestController
@RequestMapping("/api/v1/home")
public class DashboardController {


    @Autowired
    private  GeneratePdfReportUtil generatePdfReportUtil;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;
    
    @GetMapping("/dashboard")
    public ResponseEntity<DasboardData> getDashboardPage(String username){
        //TODO call the methods for dashboard - all tasks by status for user and count of tasks
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
