package com.tmt.TaskManagementTool.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    /*
     * showAllUsers(): show all users in the database
     */
    @GetMapping
    public ResponseEntity<List<User>> showAllUsers(){
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    /*
     * create a new user in the database
     */
    @GetMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    /*
     * search and update a user in the database by id
     */
    @PutMapping("/edit-user/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user){
        return new ResponseEntity<User>(userService.updateUser(username, user), HttpStatus.OK);
    }

    /*
     * delete a user in the database by username
     */
    @GetMapping("/delete-user/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }
    
}
