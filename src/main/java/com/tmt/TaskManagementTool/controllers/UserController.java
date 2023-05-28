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

import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.UserService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

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
			User newUser = new User();
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
        User user = userService.getUserByUsername(username);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
            userService.updateUser(user);
            return new ResponseEntity<User>(HttpStatus.OK);
			// TODO update form
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
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        //user.get().setRole(role);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    @GetMapping("/role/{username}")
    public void getUserRole(@PathVariable String username, @RequestBody String requestBody){
        userService.getRoleByUsername(username);
    }

    @PostMapping("/create-role")
    public void createUserRole(@PathVariable String username, @RequestBody String requestBody){
        // TODO remove path variable parameter and get current user from spring session
        Role role = new Role();
        ObjectMapper objectMapper = new ObjectMapper();
		//ResponseEntity<User> responseEntity = null;
        try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
            String rid = jsonNode.get("rid").asText(requestBody);
            String name = jsonNode.get("name").asText(requestBody);
            //TODO get list of permissions created for the role
            //List<String> permissions = new  

            role.setRid(rid);
            role.setName(name);
        
            User user = userService.getUserByUsername(username);
            user.setRole(role);
            userService.updateUser(user);
    } catch (Exception e) {
        System.out.println("@>@ E : " + e);
        //responseEntity = new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
    
    }
}  


}
