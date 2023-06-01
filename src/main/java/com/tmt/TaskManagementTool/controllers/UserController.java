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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.AuthService;
import com.tmt.TaskManagementTool.services.RoleService;
import com.tmt.TaskManagementTool.services.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    /**
     * Get lis of all users
     * @return
     */
    @GetMapping
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
    }

    /**
     * Create a new user
     * @param requestBody
     * @return
     */
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody String requestBody){
        ObjectMapper objectMapper = new ObjectMapper();
		try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
			User newUser = new User();
            newUser.setFirstname(jsonNode.get("firstname").asText());
            newUser.setLastname(jsonNode.get("lastname").asText());
            newUser.setEmail(jsonNode.get("email").asText());
            newUser.setUsername(jsonNode.get("username").asText());
            newUser.setPassword(jsonNode.get("password").asText());
            return new ResponseEntity<User>(userService.createUser(newUser), HttpStatus.CREATED);
        }catch (Exception e) {
            System.out.println("@>@ Exception occurred in creating new user : " + e); 
        }
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Update user
     * @param username
     * @param requestBody
     * @return
     */
    @PutMapping("/edit-user")
    public ResponseEntity<User> updateUser(@RequestParam("user") String username, @RequestBody String requestBody){
        User user = userService.getUserByUsername(username);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
            user.setEmail(jsonNode.get("email").asText());
            user.setPassword(jsonNode.get("password").asText());
            userService.updateUser(user);
            return new ResponseEntity<User>(HttpStatus.OK);
        }catch (Exception e) {
            System.out.println("@>@ Exception occurred in updating user : " + e); 
        }
        return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Delete user
     * @param username
     */
    @GetMapping("/delete-user/{username}")
    public void deleteUser(@PathVariable String username){
        userService.deleteUser(username);
    }

    /**
     * Search user by ID
     * @param id
     * @return
     */
    @GetMapping("/search-id/{id}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable ObjectId id){
        return new ResponseEntity<Optional<User>>(userService.getUserById(id),HttpStatus.OK) ;
    }

    /**
     * Search user by email
     * @param email
     * @return
     */
    @GetMapping("/search-email/{email}")
    public ResponseEntity<Optional<User>> getUserByEmail(@PathVariable String email){
        return new ResponseEntity<Optional<User>>(userService.getUserByEmail(email), HttpStatus.OK);
    }

    /**
     * Search user by username
     * @param username
     * @return
     */
    @GetMapping("/search-username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        User user = userService.getUserByUsername(username);
        //user.get().setRole(role);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /**
     * Get role of an user
     * @param username
     * @param requestBody
     */
    @GetMapping("/role/{username}")
    public void getUserRole(@PathVariable String username, @RequestBody String requestBody){
        userService.getRoleByUsername(username);
    }

    /**
     * Assign Role to an user
     * @param username
     * @param requestBody
     */
    @PostMapping("/assign-role/{username}")
    public ResponseEntity<User> assignUserRole(@PathVariable String username, @RequestBody String requestBody, HttpSession session){
        User loggedUser = authService.getCurrentUser(session);
        if (loggedUser.getRole().equals(roleService.getRoleByName("Manager"))){
            User user = userService.getUserByUsername(username);
            ObjectMapper objectMapper = new ObjectMapper();
		    //ResponseEntity<User> responseEntity = null;
            try {
			    JsonNode jsonNode = objectMapper.readTree(requestBody);
                Role role = new Role();
                String rid = jsonNode.get("rid").asText();
                if (roleService.getRoleByRid(rid)!=null){
                    role = roleService.getRoleByRid(rid);
                }else{
                    role.setRid(rid);
                    role.setName(jsonNode.get("name").asText());
                    JsonNode permissionNode = jsonNode.get("permission");
                    if (permissionNode != null && permissionNode.isArray()){
                        List<String> permissions = new ArrayList<>();
                        for(JsonNode per : permissionNode){
                            permissions.add(per.asText());
                        }
                        role.setPermissions(permissions);
                    }
                    roleService.createRole(role);
                }
                user.setRole(role);
                return new ResponseEntity<User>(HttpStatus.OK);
            } catch (Exception e) {
                log.error("Cannot assign role to user", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    log.error("Logged in user is not permitted to assign role");
    return new ResponseEntity<User>(HttpStatus.UNAUTHORIZED);
}  


}
