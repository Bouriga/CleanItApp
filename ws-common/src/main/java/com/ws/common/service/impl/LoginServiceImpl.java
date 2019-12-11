package com.ws.common.service.impl;

import com.ws.common.entity.EventLog;
import com.ws.common.repository.LoginRepository;
import com.ws.common.entity.User;
import com.ws.common.repository.RegisterRepository;
import com.ws.common.security.JwtGenerator;
import com.ws.common.service.LoginService;

import com.ws.common.util.DateUtil;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginRepository loginrepository;

    private final RegisterRepository registerrepository;


    @Autowired
    private LoginService loginservice;

    @Autowired
    private JwtGenerator jwt;


    @Autowired
    public LoginServiceImpl(LoginRepository loginrepository,RegisterRepository registerrepository) {
        this.loginrepository = loginrepository;
        this.registerrepository = registerrepository;
    }

    @Override
    public List<User> findUserByEmailAndPassword(String email, String password) {

        return loginrepository.findByEmailAndPassword(email, password);
    }
    @Override
    public void createEvent(EventLog event_log) {
        registerrepository.save(event_log);
    }
    @Override
    public List<User> findUserByEmail(String sqlQuery) {

        return loginrepository.findByEmail(sqlQuery);
    }
    @Override
    public List<User> getUsers() {
        return loginrepository.findAll();
    }


    @Override
    public User createUser(User user) {
        return loginrepository.save(user);
    }


    @Override
    public User findByUsername(String username) {
        return loginrepository.findOneByUsername(username);
    }



    @Override
    public String login(@RequestBody User loginUser) throws UnknownHostException, JSONException {
        String token = "false";
        String jwtToken;

        if (loginUser != null) {


            if (StringUtils.isEmpty(loginUser.getEmail()) || StringUtils.isEmpty(loginUser.getPassword())) {
                return "false";
            } else if (loginservice.findUserByEmailAndPassword(loginUser.getEmail(), loginUser.getPassword()).isEmpty()) {
                return "false";
            } else {
                List list = loginservice.findUserByEmail(loginUser.getEmail());

                if (list.size() > 0) {

                    Object[] obj = (Object[]) list.get(0);

                    JSONObject result = new JSONObject();

                    JSONArray array = new JSONArray();

                    User login = User.builder().id(Long.parseLong(obj[0].toString())).email(obj[1].toString()).role(obj[2].toString()).build();

                    DateUtil date = new DateUtil();

                    InetAddress address = InetAddress.getLocalHost();

                    EventLog eventLog = EventLog.builder().email(obj[1].toString()).timestamp(date.getDateFormat().format(date.getDate())).userHost(address.getHostAddress()).action("Login").build();

                    login.setUsername(obj[3].toString());
                    login.setPassword(obj[4].toString());
                    login.setLast_login(date.getDateFormat().format(date.getDate()));

                    loginservice.createUser(login);
                    loginservice.createEvent(eventLog);

                    jwtToken = jwt.generate(login);
                    array.put(jwtToken);
                    array.put(obj[1].toString());
                    array.put(obj[2].toString());
                    array.put(obj[3].toString());
                    result.put("data", array);
                    token = result.toString();
                }
            }
        }
        return token;
    }
}
