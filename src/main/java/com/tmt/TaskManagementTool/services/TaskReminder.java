package com.tmt.TaskManagementTool.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.tmt.TaskManagementTool.models.Task;

@Configuration
@EnableScheduling
public class TaskReminder {

    @Autowired
    private TaskService taskService;

    @Autowired
    private NotificationService notificationService;

    @Scheduled(cron = "0 0 9 * * *") // Run every day at 9 AM
    public void sendTaskReminders() {
        //TODO get current user information
        String username = "";
        List<Task> tasks = taskService.getAllTasksAssignedToUser(username);
        for (Task task : tasks) {
            if (task.getStatus().equalsIgnoreCase("New") && taskService.isTaskDue(task)) {
                notificationService.sendTaskReminderNotification(task);
            }
        }
    }
    
}
