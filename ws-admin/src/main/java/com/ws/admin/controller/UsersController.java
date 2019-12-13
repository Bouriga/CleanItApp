package com.ws.admin.controller;


import com.ws.common.entity.User;
import com.ws.common.service.LoginService;
import com.ws.common.service.RegisterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UsersController{

    private LoginService loginService;

    private RegisterService registerService;


    public UsersController(LoginService loginService,RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
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

}