package com.example.task_manager.fiengclient;

import com.example.task_manager.dto.AuthReqRes;
import com.example.task_manager.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "jwt-service")
public interface JwtServiceClient {

    @PostMapping("/auth/login")
    public AuthReqRes login(@RequestBody UserDTO userDTO);

    @PostMapping("/auth/refresh")
    public AuthReqRes refreshJwtToken(@RequestBody AuthReqRes authReqRes);

//    @PostMapping("/auth/validate")
//    public Authentication validate(@RequestBody String token);
}
