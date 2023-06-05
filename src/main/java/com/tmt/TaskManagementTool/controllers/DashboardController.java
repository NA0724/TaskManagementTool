package com.tmt.TaskManagementTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/home")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@Slf4j
public class DashboardController {

    @Autowired
    private GeneratePdfReportUtil generatePdfReportUtil;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthService authService;

    /**
     * Get dashboard Page
     * @param session
     * @return
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<DasboardData> getDashboardPage(HttpSession session) {
        //String username = session.getAttribute("user").toString();
        String username = "nraj";
        User user = userService.getUserByUsername(username);
        log.info(user.getFirstname() + " " + user.getLastname() + " has logged in successfully");
        DasboardData dashboardData = new DasboardData();

        List<Task> tasks = taskService.getAllTasksAssignedToUser(username);
        // @>@ //// if (user.getRole().getName().equalsIgnoreCase("Manager")) {
        List<Task> createdTasks = taskService.getAllTasksCreatedByUser(username);
        dashboardData.setTasksCreatedBy(createdTasks);
        // @>@ //// }
        List<Notification> notifications = notificationService.getAllNotificationsByUserId(username);
        int newTasksCount = taskService.countTaskByStatus("New");
        int inProgressTasksCount = taskService.countTaskByStatus("InProgress");
        int completedTasksCount = taskService.countTaskByStatus("Completed");
        dashboardData.setNewTaskCount(newTasksCount);
        dashboardData.setCompletedTasksCount(completedTasksCount);
        dashboardData.setInProgressTasksCount(inProgressTasksCount);
        dashboardData.setTasksAssignedTo(tasks);
        dashboardData.setNotifications(notifications);
        return new ResponseEntity<DasboardData>(dashboardData, HttpStatus.OK);
    }

    /**
     * Get My Profile Page
     * 
     * @param session
     * @return
     */
    @GetMapping("/my-profile")
    public ResponseEntity<User> getMyProfile(HttpSession session) {
        String username = session.getAttribute("user").toString();
        User user = userService.getUserByUsername(username);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Generate PDF Report
     */
    @GetMapping("/pdfreport")
    public void getPDFReport(HttpSession session) {
        // String username = session.getAttribute("user").toString();
        // User user = userService.getUserByUsername(username);
        User user = authService.getCurrentUser(session);
        // @>@ // if (user.getRole().getName().equalsIgnoreCase("Manager")) {
        generatePdfReportUtil.generatePdfReport();
        // }

    }

    /**
     * Get all notifications for a user
     * 
     * @param session
     * @return
     */
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotificationForUser(HttpSession session) {
        String username = session.getAttribute("user").toString();
        return new ResponseEntity<List<Notification>>(notificationService.getAllNotificationsByUserId(username),
                HttpStatus.OK);
    }

}