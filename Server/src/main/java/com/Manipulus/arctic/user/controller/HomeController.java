package com.Manipulus.arctic.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {
    @RequestMapping("/")
    public String hello()
    {
        return "Hello";
    }
}
