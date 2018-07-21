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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public void list(@ModelAttribute("pageVO") PageVO vo, Model model) {
        Pageable page = vo.makePageable(0,"bno");
        Page<FishBoard> result = repository.findAll(repository.makePredicate(vo.getType(),vo.getKeyword()),page);
        log.info(""+page);
        log.info(""+result);
        log.info("Total page number : "+result.getTotalPages());

        model.addAttribute("result",new PageMaker(result));
    }

    @GetMapping("/register")
    public void registerGET(@ModelAttribute("vo") FishBoard vo) {
        log.info("register get call...");
    }

    @PostMapping("/register")
    public String registerPOST(@ModelAttribute("vo") FishBoard vo, RedirectAttributes rttr) {
        log.info("register post");
        log.info("+vo");

        repository.save(vo);
        rttr.addFlashAttribute("msg","success");

        return "redirect:/boards/list";
    }

    @GetMapping("/view")
    public void view(Long bno, @ModelAttribute("pageVO")PageVO vo, Model model) {
        log.info("BNO: "+bno);
        repository.findById(bno).ifPresent(board -> model.addAttribute("vo",board));
    }

    @GetMapping("/modify")
    public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
        log.info("Modify : "+bno);
        repository.findById(bno).ifPresent(board->model.addAttribute("vo",board));
    }

    @PostMapping("/delete")
    public String delete(Long bno, PageVO vo, RedirectAttributes rttr) {
        log.info("Delete : "+bno);
        repository.deleteById(bno);
        rttr.addFlashAttribute("msg","success");
        rttr.addAttribute("page",vo.getPage());
        rttr.addAttribute("size",vo.getSize());
        rttr.addAttribute("type",vo.getType());
        rttr.addAttribute("keyword",vo.getKeyword());

        return "redirect:/boards/list";
    }

    @PostMapping("/modify")
    public String modifyPost(FishBoard board, PageVO vo, RedirectAttributes rttr) {
        log.info("Modi board : "+board);

        repository.findById(board.getBno()).ifPresent(mboard -> {
            mboard.setTitle(board.getTitle());
            mboard.setContent(board.getContent());

            repository.save(mboard);
            rttr.addFlashAttribute("msg","success");
            rttr.addAttribute("bno",mboard.getBno());
        });
        rttr.addAttribute("page",vo.getPage());
        rttr.addAttribute("size",vo.getSize());
        rttr.addAttribute("type",vo.getType());
        rttr.addAttribute("keyword",vo.getKeyword());

        return "redirect:/boards/view";
    }
}
