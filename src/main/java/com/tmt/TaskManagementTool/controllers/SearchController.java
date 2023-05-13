package com.tmt.TaskManagementTool.controllers;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.UserService;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String search(){
        return "searchpage";
    }

      /*
     * search and get a user in the database by id
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<User>>(userService.getUserById(id),HttpStatus.OK) ;
    }

    /*
     * search and get a user in the database by email
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<Optional<User>>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    /*
     * search and get a user in the database by username
     */
    @GetMapping("/username/{username}")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username){
        return new ResponseEntity<Optional<User>>(userService.getUserByUsername(username), HttpStatus.OK);
    }
    
}
