package com.ws.admin.service;

import com.ws.common.entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

public interface UserService {
    List<User> getUsers();

    void createUser(User user);

    User addUser(@ModelAttribute User user);
}
