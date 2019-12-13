package com.ws.common.service;

import com.ws.common.entity.EventLog;
import com.ws.common.entity.User;
import com.ws.common.exception.UsernameInUseException;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.UnknownHostException;


public interface RegisterService {

    void createEvent(EventLog event_log);

    User register(@RequestBody User user) throws UsernameInUseException, UnknownHostException;

    void createUser(User user);

}