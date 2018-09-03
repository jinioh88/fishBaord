package com.fishing.fishboard.controller;

import com.fishing.fishboard.persistence.JohangRepository;
import groovy.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/johang")
@Log
public class JohangController {
    @Autowired
    JohangRepository repository;

    @GetMapping
    public String board() {
        return "/johang/johang";
    }
}
