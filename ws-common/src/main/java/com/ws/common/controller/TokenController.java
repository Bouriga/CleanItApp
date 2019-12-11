package com.ws.common.controller;

import com.ws.common.entity.User;
import com.ws.common.security.JwtGenerator;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtGenerator jwtgenerator;


    public TokenController(JwtGenerator jwtgenerator) {

        this.jwtgenerator = jwtgenerator;
    }


    @GetMapping
    @ApiOperation("Get token for users")
    public String generated(@RequestBody final User loginuser) {


        return jwtgenerator.generate(loginuser);

    }
}
