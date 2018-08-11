package com.fishing.fishboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/accessDenided")
    public void accessDenied() {
    }

    @GetMapping("/logout")
    public void logout(){}
}
