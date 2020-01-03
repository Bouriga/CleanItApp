package com.ws.admin.controller;


import com.ws.admin.repository.UserRepository;
import com.ws.admin.service.UserService;
import com.ws.common.entity.Object;
import com.ws.common.entity.User;
import com.ws.common.repository.ObjectRepository;
import com.ws.common.service.LoginService;
import com.ws.common.service.ObjectService;
import com.ws.common.service.RegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@CrossOrigin(origins = "*")
@RequestMapping(value = "/users")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
}

)
@Api(value = "UserControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE, description = "User Controller")
public class UsersController{

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private UserService userService;

    private final UserRepository userRepository;


    public UsersController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/all")
    public List<User> getAll() {
        return loginService.getUsers();
    }

    @PutMapping("/acceptUser")
    public User acceptUser(String username) {
        User user = loginService.findByUsername(username);
        user.setApproved(true);
        registerService.createUser(user);
        return user;

    }

    @GetMapping(value = "/userById/{Id}")
    @ApiOperation("Gets User by id")
    public User findById(@PathVariable("Id") Long Id) {
        return userRepository.findById(Id).orElse(null);
    }


    @PostMapping(value = "/ajoutUser")
    @ApiOperation("ajout user")
    public User addUser(@ModelAttribute User user) {
        return userService.addUser(user);
    }

    @PostMapping(value = "/updateUser/{Id}")
    @ApiOperation("update User")
    public User updateUser(@ModelAttribute User user) {

        userRepository.save(user);

        return user;
    }


    @DeleteMapping(value = "/deleteUser/{Id}")
    @ApiOperation("delete User")
    public User deleteUser(@PathVariable("Id") Long Id) {

        User user = userRepository.findById(Id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }

        return user;
    }

}