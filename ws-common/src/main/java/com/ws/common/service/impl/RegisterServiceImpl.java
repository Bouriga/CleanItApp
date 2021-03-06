package com.ws.common.service.impl;



import com.ws.common.entity.EventLog;
import com.ws.common.entity.User;

import com.ws.common.exception.UsernameInUseException;

import com.ws.common.repository.LoginRepository;
import com.ws.common.repository.RegisterRepository;
import com.ws.common.service.LoginService;


import com.ws.common.service.RegisterService;
import com.ws.common.util.DateUtil;
import com.ws.common.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Service
public class RegisterServiceImpl implements RegisterService {

    private final RegisterRepository registerRepository;

    private final LoginRepository loginRepository;

    @Autowired
    private LoginService userService;

    @Autowired
    private RegisterService registerService;

    @Autowired
    public RegisterServiceImpl(RegisterRepository registerRepository,LoginRepository loginRepository) {

        this.registerRepository = registerRepository;
        this.loginRepository=loginRepository;

    }


    @Override
    public void createEvent(EventLog event_log) {
         registerRepository.save(event_log);
    }

    @Override
    public void createUser(User user) {
        loginRepository.save(user);
    }

    @Override
    public User register(User user) throws UsernameInUseException, UnknownHostException {
        //System.out.println("Username esgale"+userService.findByUsername(user.getEmail()));
        if (userService.findByUsername(user.getUsername()) != null) {
            throw new UsernameInUseException();
        }

        user.setPassword(SecurityUtil.getMD5(user.getPassword()));
        user.setRole("client");
        userService.createUser(user);

        DateUtil date = new DateUtil();
        InetAddress address = InetAddress.getLocalHost();

        EventLog eventLog = EventLog.builder().email(user.getEmail()).timestamp(date.getDateFormat().format(date.getDate())).userHost(address.getHostAddress()).action("Register").build();
        registerService.createEvent(eventLog);

        return user;
    }

}
