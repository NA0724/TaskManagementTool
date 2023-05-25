package com.tmt.TaskManagementTool.controllers;

import java.util.ArrayList;
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
import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.NotificationService;
import com.tmt.TaskManagementTool.services.UserService;
import com.tmt.TaskManagementTool.util.CrudFormsUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
	private CrudFormsUtil crudFormsUtil;
    
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
    public ResponseEntity<User> createUser(@RequestBody String requestBody){
        ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
			User newUser = crudFormsUtil.createUser(jsonNode);
            return new ResponseEntity<User>(userService.createUser(newUser), HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println("@>@ Exception occurred in creating new user : " + e); 
        }
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
     * search and update a user in the database by id
     */
    @PutMapping("/edit-user/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody String requestBody){
        Optional<User> user = userService.getUserByUsername(username);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
			crudFormsUtil.updateUserForm(jsonNode, user);
            return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("@>@ Exception occurred in updating user : " + e); 
        }
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
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
        Optional<User> user = userService.getUserByUsername(username);
        Role role = userService.getRoleByUsername(username);
        //user.get().setRole(role);
        return new ResponseEntity<Optional<User>>(user, HttpStatus.OK);
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

    @GetMapping("/role/{username}")
    public void getUserRole(@PathVariable String username){
        userService.getRoleByUsername(username);
    }
    
}
