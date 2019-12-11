package com.ws.common.security;


import com.ws.common.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

@Component
public class JwtValidator {


    private String secret = "Efive";

    public User validate(String token) {
        User jwtUser = null;
        try {
            String secret = "Efive";
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            jwtUser = User.builder().build();

            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String) body.get("userId")));
            jwtUser.setRole((String) body.get("role"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jwtUser;
    }
}
