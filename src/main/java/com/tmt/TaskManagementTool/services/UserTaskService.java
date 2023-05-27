package com.tmt.TaskManagementTool.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.tmt.TaskManagementTool.models.Task;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.repositories.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserTaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;


    public List<Task> getAllTasksCreatedByUser(String username){
        // TODO fetch user from current session
        //check code in userservice
        Optional<User> userOptional = userService.getUserByUsername(username);
        User user = userOptional.orElseThrow(()-> new IllegalArgumentException("User Not Found"));
        
       List<Task> allTasks = taskRepository.findTasksByCreatedBy(user.getUsername());
        log.info("found tasks for user " + user.getUsername());
        return allTasks;
    }

    public List<Task> getAllTasksAssignedToUser(String username){
        // TODO fetch user from current session
        //check code in userservice
        Optional<User> userOptional = userService.getUserByUsername(username);
        User user = userOptional.orElseThrow(()-> new IllegalArgumentException("User Not Found"));
        
       List<Task> allTasks = taskRepository.findTasksByAssignedTo(user.getUsername());
        log.info("found tasks for user " + user.getUsername());
        return allTasks;
    }

    public List<Task> findTaskListByStatusAssignedToUser(String username, String status){
        /*List<Task> allTasksAssignedToUser = getAllTasksAssignedToUser(username);
        List<Task> taskByStatus = new ArrayList<Task>();
        for (Task task : allTasksAssignedToUser){
            if (task.getStatus().equalsIgnoreCase(status)){
                taskByStatus.add(task);
            }
        }*/

        Optional<User> userOptional = userService.getUserByUsername(username);
        User user = userOptional.orElseThrow(()-> new IllegalArgumentException("User Not Found"));

        List<Task> alltasksByStatus = taskRepository.findTasksByStatus(status);
        alltasksByStatus.stream().filter(t -> t.getCreatedBy().equalsIgnoreCase(user.getUsername()))
                    .collect(Collectors.toList());
        return alltasksByStatus;
    }

    public int countTaskByStatus(String status){
        List<Task> allTaskByStatus = taskRepository.findTasksByStatus(status);
        return allTaskByStatus.size();
    }

    
}
