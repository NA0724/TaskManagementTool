package com.tmt.TaskManagementTool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tmt.TaskManagementTool.dtos.ApiResponse;
import com.tmt.TaskManagementTool.dtos.AuthenticationResponse;
import com.tmt.TaskManagementTool.dtos.LoginRequestDto;
import com.tmt.TaskManagementTool.dtos.RegisterRequestDto;
import com.tmt.TaskManagementTool.services.AuthService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@ComponentScan(basePackages = {"com.tmt.TaskManagementTool.services"})
public class LoginRegisterConroller {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> getUserByUsername(@RequestBody String requestBody) {
		System.out.println("@>@" + requestBody);
		ObjectMapper objectMapper = new ObjectMapper();
		//ResponseEntity<Boolean> responseEntity = null;
		try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
			String username = jsonNode.get("username").asText();
			String password = jsonNode.get("password").asText();
			System.out.println("@>@ em" + username);
			System.out.println("@>@ pwd" + password);
			LoginRequestDto loginRequestDto = new LoginRequestDto(username, password);
			return new ResponseEntity<>(authService.authenticate(loginRequestDto), HttpStatus.OK);
			/*Optional<User> optUser = userService.getUserByUsername(username);
			//User usr = optUser.get();
			if (usr != null && usr.getPassword().equals(password)) {
				responseEntity = new ResponseEntity<Boolean>(true, HttpStatus.OK);
				System.out.println(responseEntity);
			} else {
				responseEntity = new ResponseEntity<Boolean>(false, HttpStatus.UNAUTHORIZED);
			}*/
		} catch (Exception e) {
			System.out.println("@>@ E : " + e);
		}
		return null;
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> createUser(@RequestBody String requestBody) {
		System.out.println("@>@" + requestBody);
		ObjectMapper objectMapper = new ObjectMapper();
		//dResponseEntity<User> responseEntity = null;
		try {
			JsonNode jsonNode = objectMapper.readTree(requestBody);
			String email = jsonNode.get("email").asText();
		  String password = jsonNode.get("password").asText();
		  String firstName = jsonNode.get("firstname").asText();
		  String lastName = jsonNode.get("lastname").asText();
		  String userName = jsonNode.get("username").asText();
			//User newUser = crudFormsUtil.registerUser(jsonNode);
		  RegisterRequestDto registerRequestDto = new RegisterRequestDto(userName,firstName,lastName,email,password,password);
			/*responseEntity = new ResponseEntity<User>(userService.createUser(newUser),
					HttpStatus.CREATED);*/
		  if (authService.existsByUserName(registerRequestDto)) {
						return new ResponseEntity<>(new ApiResponse(400, "Username already exists"), HttpStatus.BAD_REQUEST);
					}
			
					authService.createUser(registerRequestDto);
					return new ResponseEntity<>(new ApiResponse(200, "User Registration Completed Successfully!!"), HttpStatus.OK);
		} catch (Exception e) {
			System.out.println("@>@ E : " + e);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse(404, "Unknown"), HttpStatus.NOT_IMPLEMENTED);
	}

}
