package com.tmt.TaskManagementTool.controllers;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
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
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.Notification;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.NotificationService;
import com.tmt.TaskManagementTool.services.UserService;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;
    
    /*
     * showAllUsers(): show all users in the database
     */
    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    /*
     * create a new user in the database
     */

    @PostMapping("/create-user")
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

      /*
     * search and get a user in the database by id
     */
    @GetMapping("/search-id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<User>>(userService.getUserById(id),HttpStatus.OK) ;
    }

    /*
     * search and get a user in the database by email
     */
    @GetMapping("/search-email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<Optional<User>>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    /*
     * search and get a user in the database by username
     */
    @GetMapping("/search-username/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<Optional<User>>(userService.getUserByUsername(username), HttpStatus.OK);
    }

     /*
     * get all notifications from the database for user
     */
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getAllNotificationForUser(@PathVariable String username){
        return new ResponseEntity<List<Notification>>(notificationService.getAllNotificationsByUserId(username), HttpStatus.OK);
    }

    /*
     * get all notifications from the database for user
     */
    @GetMapping("/myprofile")
    public ResponseEntity<Optional<User>> getMyProfilePage(@PathVariable String username){
        Optional<User> user = userService.getUserByUsername(username);
        // TODO create query for the following methods
        //Optional<Role> role = roleService.getRoleByUser(username);
        //Optional<Permission> permission = permissionService.getPermissionByRole(role);
        return new ResponseEntity<Optional<User>>(HttpStatus.OK);
    }
    

}
