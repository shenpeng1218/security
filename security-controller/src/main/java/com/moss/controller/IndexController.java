package com.moss.controller;

import com.moss.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/user")
    @ResponseBody
    public User getUser(){
        User user = new User("shenpeng", 18);
        return user;
    }
}
