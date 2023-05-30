package com.tmt.TaskManagementTool.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Notification;
import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.repositories.TaskRepository;
import com.tmt.TaskManagementTool.util.GenericUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private GenericUtil genericUtil;

    /**
     * Get all the tasks that is created by user
     * @param username
     * @return
     */
    public List<Task> getAllTasksCreatedByUser(String username){
        // TODO fetch user from current session
        //check code in userservice
        User user = userService.getUserByUsername(username);
        
       List<Task> allTasks = taskRepository.findTasksByCreatedBy(user.getUsername());
        log.info("found tasks for user " + user.getUsername());
        return allTasks;
    }

    /**
     * Get all tasks assigned to user
     * @param username
     * @return
     */
    public List<Task> getAllTasksAssignedToUser(String username){
        // TODO fetch user from current session
        //check code in userservice
        User user = userService.getUserByUsername(username);
        
       List<Task> allTasks = taskRepository.findTasksByAssignedTo(user.getUsername());
        log.info("found tasks for user " + user.getUsername());
        return allTasks;
    }

    /**
     * find specific status tasks for user
     * @param username
     * @param status
     * @return
     */
    public List<Task> findTaskListByStatusAssignedToUser(String username, String status){
        /*List<Task> allTasksAssignedToUser = getAllTasksAssignedToUser(username);
        List<Task> taskByStatus = new ArrayList<Task>();
        for (Task task : allTasksAssignedToUser){
            if (task.getStatus().equalsIgnoreCase(status)){
                taskByStatus.add(task);
            }
        }*/

        User user = userService.getUserByUsername(username);

        List<Task> alltasksByStatus = taskRepository.findTasksByStatus(status);
        alltasksByStatus.stream().filter(t -> t.getCreatedBy().equalsIgnoreCase(user.getUsername()))
                    .collect(Collectors.toList());
        return alltasksByStatus;
    }

    /**
     * count the number of tasks by status
     * @param status
     * @return
     */
    public int countTaskByStatus(String status){
        List<Task> allTaskByStatus = taskRepository.findTasksByStatus(status);
        return allTaskByStatus.size();
    }

    public Task createTask(Task task){
        task.setCreatedAt(genericUtil.getCurrentDateTime());
        // TODO task.setAssignedBy() and task.setcreatedby()
        if (task.getAssignedTo() != null && task.getCreatedBy()!=null){
            Notification notification = notificationService.createNotificationForTask(task.getTid());
            notification.setBody("Task " + task.getTid() + ": has been assigned to you by " + task.getCreatedBy());
            notification.setTaskId(task.getTid());
            notification.setUserId(task.getAssignedTo());
            notificationService.saveNotification(notification);
        }
        return taskRepository.insert(task);
    }

    public Task getTaskByTid(String tid){
        Optional<Task> taskOptional = taskRepository.findTaskByTid(tid);
        Task task = taskOptional.orElseThrow(()-> new IllegalArgumentException("Task not found with tid: "+ tid));
        return task;
    }

    public void deleteTask(String tid) {
        Task task = getTaskByTid(tid);
        ObjectId id = task.getId();
        taskRepository.deleteById(id);
    }

    public Task updateTask(Task task) {
        Task oldTask = getTaskByTid(task.getTid());
        Notification notification = notificationService.createNotificationForTask(task.getTid());
        
        if(!(oldTask.getStatus().equalsIgnoreCase(task.getStatus()))){
            notification.setBody("Task " + task.getTid() + ": Status changed to " +task.getStatus());
            
        }else if (!(oldTask.getAssignedTo().equalsIgnoreCase(task.getAssignedTo()))){
            notification.setBody("Task " + task.getTid() + ": has been assigned to you.");
        }else{
            notification.setBody("There is an update to Task " + task.getTid() +". Please check.");
        }

        notification.setUserId(task.getAssignedTo());
        notificationService.saveNotification(notification);

        return taskRepository.save(task);
    }

    public boolean isTaskDue(Task task) {
        LocalDate today = LocalDate.now();
        return task.getDueDate().isEqual(today) || task.getDueDate().isBefore(today.plusDays(1));
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
}
