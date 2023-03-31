package com.tcam.auth.controller;


import com.tcam.auth.model.User;
import com.tcam.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v001/user")
@RequiredArgsConstructor
public class UserController  {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAllUser(){
        return ResponseEntity.ok(userService.findAllUser());
    }

}
