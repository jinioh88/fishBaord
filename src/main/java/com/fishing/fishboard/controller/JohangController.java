package com.fishing.fishboard.controller;

import com.fishing.fishboard.domain.JohangBoard;
import com.fishing.fishboard.persistence.JohangRepository;
import com.fishing.fishboard.vo.PageVO;
import lombok.extern.java.Log;
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

    @GetMapping("/list")
    public String board(@ModelAttribute("pageVO") PageVO vo, Model model) {
        List<JohangBoard> list = repository.findAll();
        for(int i=0;i<list.size();i++) {
            System.out.println(list.get(i).getMember().getUname());
        }
        model.addAttribute("list",list);
        return "/johang/johang";
    }

    @GetMapping("/register")
    public String register(Model model) {

        return "/johang/register";
    }

    @PostMapping("/register")
    public String registerp(String title, String content, Model model) {
        JohangBoard board = new JohangBoard();
        board.setContent(content);
        return "redirect:/johang";
    }
}
