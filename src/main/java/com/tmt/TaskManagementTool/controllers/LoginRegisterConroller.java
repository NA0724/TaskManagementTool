package com.tmt.TaskManagementTool.controllers;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.Role;
import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.UserService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.*;

@RestController
@RequestMapping("/api/v1/loginRegister")
@CrossOrigin(origins = "*")
public class LoginRegisterConroller {

	@Autowired
	private UserService userService;

	@PostMapping("/loginUser")
	public ResponseEntity<Boolean> getUserByUsername(@RequestBody String requestBody) {
		// User user = new User();
		System.out.println("@>@" + requestBody);
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseEntity<Boolean> responseEntity = null;
		try {

			JsonNode jsonNode = objectMapper.readTree(requestBody);
			String username = jsonNode.get("username").asText();
			String password = jsonNode.get("password").asText();
			System.out.println("@>@ em" + username);
			System.out.println("@>@ pwd" + password);
			Optional<User> optUser = userService.getUserByUsername(username);
			User usr = optUser.get();
			if (usr != null && usr.getPassword().equals(password)) {
				responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
				System.out.println(responseEntity);
			} else {
				responseEntity = new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			System.out.println("@>@ E : " + e);
			responseEntity = new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
		}
		return responseEntity;
	}

	@PostMapping("/registerUser")
	public ResponseEntity<User> createUser(@RequestBody String requestBody) {
		System.out.println("@>@" + requestBody);
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseEntity<User> responseEntity = null;
		try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
			String email = jsonNode.get("email").asText();
			String password = jsonNode.get("password").asText();
			String firstName = jsonNode.get("firstname").asText();
			String lastName = jsonNode.get("lastname").asText();
			String userName = jsonNode.get("username").asText();

			User newUser = new User();
			newUser.setUsername(userName);
			newUser.setEmail(email);
			newUser.setPassword(password);
			newUser.setFirstname(firstName);
			newUser.setLastname(lastName);
			List<Role> list = new ArrayList<>();
			newUser.setRoles(list);

			responseEntity = new ResponseEntity<User>(userService.createUser(newUser),
					HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("@>@ E : " + e);

		}
		// return new ResponseEntity<String>("Done", HttpStatus.CREATED);
		return responseEntity;
	}

}
