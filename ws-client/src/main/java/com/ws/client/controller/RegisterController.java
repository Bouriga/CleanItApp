package com.ws.client.controller;



import com.ws.common.entity.User;

import com.ws.common.exception.UsernameInUseException;
import com.ws.common.service.LoginService;
import com.ws.client.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.http.MediaType;


import org.springframework.web.bind.annotation.*;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/registration")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
}

)
@Api(value = "RegistrationControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Registration and Get User")
public class RegisterController {
    @Autowired
    private LoginService userService;
    @Autowired
    private RegisterService registerService;


    @PostMapping(value = "/register")
    @ApiOperation("register user")
    public User register(@RequestBody User user) throws UsernameInUseException, UnknownHostException {
        return registerService.register(user);
    }

    @GetMapping(value = "/allUser")
    @ApiOperation("Gets all users")
    public List<User> findAll() {
        return userService.getUsers();
    }


}