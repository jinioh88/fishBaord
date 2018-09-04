package com.fishing.fishboard.controller;

import com.fishing.fishboard.domain.JohangBoard;
import com.fishing.fishboard.persistence.JohangRepository;
import groovy.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/johang")
@Log
public class JohangController {
    @Autowired
    JohangRepository repository;

    @GetMapping
    public String board(Model model) {
        List<JohangBoard> list = repository.findAll();
        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i).getMember().getUname());
        }
        model.addAttribute("list",list);
        return "/johang/johang";
    }

    @GetMapping("/register")
    public String register(Model model) {
        JohangBoard board = repository.findByJno(1L);
        model.addAttribute("board",board.getContent());
        System.out.println(board.getContent());
        return "/johang/johang";
    }
}
