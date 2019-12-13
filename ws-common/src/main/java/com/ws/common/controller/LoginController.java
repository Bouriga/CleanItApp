package com.ws.common.controller;

import com.ws.common.entity.User;



import com.ws.common.repository.LoginRepository;
import com.ws.common.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.codehaus.jettison.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;

import java.net.UnknownHostException;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/login")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
}

)
@Api(value = "LoginControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "Login User")
public class LoginController {

    @Autowired
    private LoginService loginservice;

    private final LoginRepository loginRepository;

    public LoginController(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;

    }

    @PostMapping
    @ApiOperation("Login user")
    public String login(@RequestBody User loginUser) throws JSONException, UnknownHostException {

        return loginservice.login(loginUser);
    }

    @GetMapping(value = "/userByUsername/{username}")
    @ApiOperation("User by Email")
    public User findOneByUsername(@PathVariable("username") String username) {
        User user = loginRepository.findOneByUsername(username);
        return user;
    }

}
