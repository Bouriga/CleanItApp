package com.ws.admin.service.impl;

import com.ws.admin.repository.UserRepository;
import com.ws.admin.service.UserService;
import com.ws.common.entity.User;
import com.ws.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(User user) {
        user.setPassword(SecurityUtil.getMD5(user.getPassword()));
        userRepository.save(user);
    }


    @Override
    public User addUser(User user) {

        userService.createUser(user);

        return user;
    }

}
