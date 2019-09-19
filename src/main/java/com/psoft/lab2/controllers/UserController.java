package com.psoft.lab2.controllers;

import com.psoft.lab2.entities.DTOs.UserDTO;
import com.psoft.lab2.entities.User;
import com.psoft.lab2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    @Autowired
    UserService userService;

    public UserController(UserService userService)
    {
        super();
        this.userService = userService;
    }

    @PostMapping("/api/usuarios")
    public ResponseEntity<UserDTO> addUser (@RequestBody User u)
    {
        return new ResponseEntity(userService.addUser(u), HttpStatus.CREATED);
    }


}
