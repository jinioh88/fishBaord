package com.fishing.fishboard.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class FishBoardController {
    @GetMapping("/list")
    public void list() {
        log.info("list()...");
    }
}
