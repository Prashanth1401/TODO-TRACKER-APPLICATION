package com.todo.proxy;

import com.todo.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-authentication-service",url = "localhost:8083")
@CrossOrigin
public interface Proxy {

    @PostMapping("api/v2/save")
    public ResponseEntity<User> saveUser(@RequestBody User user);
}
