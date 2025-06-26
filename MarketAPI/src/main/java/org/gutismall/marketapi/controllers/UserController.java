package org.gutismall.marketapi.controllers;

import org.gutismall.marketapi.entity.*;
import org.gutismall.marketapi.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.logging.*;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    private final Logger logger = Logger.getLogger(UserController.class.getName());
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UserEntity getUserByAppIdAndEmail(
            @RequestParam String appId,
            @RequestParam String email) {
        logger.info("fetching {} " + appId + " and email " + email);
        return userService.getUserByAppIdAndEmail(appId, email);
    }
    @GetMapping(path = "/hello")
    public String getHello() {
        return "Hello from UserController";
    }
    
    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void registerUser(
            @RequestBody UserEntity user)
    {
        logger.info("Registering user with email: " + user.getEmail());
        userService.registerUser(user);
    }
    
}
