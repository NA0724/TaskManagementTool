package com.tmt.TaskManagementTool.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.models.User;
import com.tmt.TaskManagementTool.services.AuthService;
import com.tmt.TaskManagementTool.services.UserService;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@Slf4j
public class LoginRegisterConroller {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthService authService;


	@PostMapping("/loginUser")
	public ResponseEntity<Boolean> getUserByUsername(@RequestBody String requestBody) {
		// User user = new User();
		System.out.println("@>@" + requestBody);
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseEntity<Boolean> responseEntity = null;
		HttpHeaders headers = new HttpHeaders();
		try {

			JsonNode jsonNode = objectMapper.readTree(requestBody);
			String username = jsonNode.get("username").asText();
			String password = jsonNode.get("password").asText();
			System.out.println("@>@ em" + username);
			System.out.println("@>@ pwd" + password);
			User usr= userService.getUserByUsername(username);
			if (usr != null && usr.getPassword().equals(password)) {
				String val = authService.getBasicAuthenticationHeader(username, password);
				log.info(val);
				log.info(authService.decode(val));
				headers.add("Authorization", val);
				responseEntity = ResponseEntity.ok().headers(headers).body(true);
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
			//Role role = new Role();
			//newUser.setRole(role);

			responseEntity = new ResponseEntity<User>(userService.createUser(newUser),
					HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println("@>@ E : " + e);

		}
		// return new ResponseEntity<String>("Done", HttpStatus.CREATED);
		return responseEntity;
	}

}
