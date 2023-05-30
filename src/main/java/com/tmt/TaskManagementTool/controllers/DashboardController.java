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
import org.springframework.web.bind.annotation.RequestMethod;
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
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
    
    
    @RequestMapping(value="/dashboard", method = RequestMethod.GET, produces = "application/text")
    public ResponseEntity<DasboardData> getDashboardPage(HttpSession session){
        String username = session.getAttribute("user").toString();
        User user = userService.getUserByUsername(username);
        log.info(user.getFirstname()+" " +user.getLastname() + " has logged in successfully");
        
        ObjectMapper objectMapper = new ObjectMapper();
        DasboardData dashboardData = new DasboardData();

        List<Task> tasks = taskService.getAllTasksAssignedToUser(username);
        if (user.getRole().getName().equalsIgnoreCase("Manager")){
            List<Task> createdTasks = taskService.getAllTasksCreatedByUser(username);
            dashboardData.setTasksCreatedBy(createdTasks);
        }
        List<Notification> notifications = notificationService.getAllNotificationsByUserId(username);
        int newTasksCount = taskService.countTaskByStatus("New");
        int inProgressTasksCount = taskService.countTaskByStatus("In Progress");
        int completedTasksCount = taskService.countTaskByStatus("Completed");
        dashboardData.setNewTaskCount(newTasksCount);
        dashboardData.setCompletedTasksCount(completedTasksCount);
        dashboardData.setInProgressTasksCount(inProgressTasksCount);
        dashboardData.setTasksAssignedTo(tasks);
        dashboardData.setNotifications(notifications);
        return new ResponseEntity<DasboardData>(dashboardData, HttpStatus.OK);
    }

    @GetMapping("/my-profile")
    public ResponseEntity<User> getMyProfile(HttpSession session){
        String username = session.getAttribute("user").toString();
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
    public ResponseEntity<List<Notification>> getAllNotificationForUser(HttpSession session){
        String username = session.getAttribute("user").toString();
        return new ResponseEntity<List<Notification>>(notificationService.getAllNotificationsByUserId(username), HttpStatus.OK);
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }


    
}
