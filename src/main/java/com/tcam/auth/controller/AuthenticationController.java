package com.tcam.auth.controller;

import com.tcam.auth.domain.AuthenticationRequestDTO;
import com.tcam.auth.domain.AuthenticationResponse;
import com.tcam.auth.domain.SingUpDTO;
import com.tcam.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v001/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody SingUpDTO singUpDTO){

      return  ResponseEntity.ok(authenticationService.register(singUpDTO));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequestDTO authenticationRequestDTO){
        return  ResponseEntity.ok(authenticationService.login(authenticationRequestDTO));

    }


}
