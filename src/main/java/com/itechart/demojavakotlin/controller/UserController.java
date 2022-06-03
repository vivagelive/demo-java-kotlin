package com.itechart.demojavakotlin.controller;

import com.itechart.demojavakotlin.entity.UserEntity;
import com.itechart.demojavakotlin.model.UserRequest;
import com.itechart.demojavakotlin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserEntity> create(@RequestBody final UserRequest userRequest) {
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.OK);
    }
}
