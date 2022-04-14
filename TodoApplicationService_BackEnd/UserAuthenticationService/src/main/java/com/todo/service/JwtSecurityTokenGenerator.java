package com.todo.service;

import com.todo.model.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtSecurityTokenGenerator implements SecurityTokenGenerator{
    @Override
    public Map<String,Object> generateToken(User user){
        String jsonWebToken=null;

        JwtBuilder jwtBuilder= Jwts.builder();
        jsonWebToken=jwtBuilder.setSubject(user.getUserId()).setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS256,"secret").compact();

        Map<String,Object> tokenMap = new HashMap<>();
        tokenMap.put("token",jsonWebToken);
        tokenMap.put("user",user);

        return tokenMap;
    }
}
