package com.ws.common.service;


import com.ws.common.entity.EventLog;
import com.ws.common.entity.User;
import org.codehaus.jettison.json.JSONException;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.UnknownHostException;
import java.util.List;

public interface LoginService {
    void createEvent(EventLog event_log);

     List<User> findUserByEmailAndPassword(String email, String password);

     List<User> findUserByEmail(String strQuery);

    List<User> getUsers();

    User createUser(User user);

    User findByUsername(String username);

    String login(@RequestBody User user) throws UnknownHostException, JSONException;
}
