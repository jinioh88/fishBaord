package com.fishing.fishboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public void loginPost() {
    }

    @GetMapping("/accessDenided")
    public void accessDenied() {
    }

    @GetMapping("/logout")
    public void logout(){}
}
