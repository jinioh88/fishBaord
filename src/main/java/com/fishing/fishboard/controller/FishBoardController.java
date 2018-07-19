package com.fishing.fishboard.controller;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.persistence.FishBoardRepository;
import com.fishing.fishboard.vo.PageMaker;
import com.fishing.fishboard.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards/")
@Log
public class FishBoardController {

    @Autowired
    private FishBoardRepository repository;

    // 보안에 문제가 있음
    //    @GetMapping("/list")
//    public void list(@PageableDefault(direction = Sort.Direction.DESC,
//    sort = "bno", size = 10, page = 0)Pageable page) {
//        log.info("list()..."+page);
//    }

    @GetMapping("/list")
    public void list(PageVO vo, Model model) {
        Pageable page = vo.makePageable(0,"bno");
        Page<FishBoard> result = repository.findAll(repository.makePredicate(null,null),page);
        log.info(""+page);
        log.info(""+result);
        log.info("Total page number : "+result.getTotalPages());

        model.addAttribute("result",new PageMaker(result));
    }
}
