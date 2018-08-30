package com.fishing.fishboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class InitController {
    @GetMapping
    public String init() {
        return "/index";
    }
}
