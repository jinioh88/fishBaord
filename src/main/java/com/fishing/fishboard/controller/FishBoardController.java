package com.fishing.fishboard.controller;

import lombok.extern.java.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class FishBoardController {
    @GetMapping("/list")
    public void list(@PageableDefault(direction = Sort.Direction.DESC,
    sort = "bno", size = 10, page = 0)Pageable page) {
        log.info("list()..."+page);
    }

}
