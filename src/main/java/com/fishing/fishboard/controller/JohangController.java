package com.fishing.fishboard.controller;

import com.fishing.fishboard.domain.JohangBoard;
import com.fishing.fishboard.domain.Member;
import com.fishing.fishboard.persistence.ImageVORepository;
import com.fishing.fishboard.persistence.JohangRepository;
import com.fishing.fishboard.persistence.MemberRepository;
import com.fishing.fishboard.vo.PageVO;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/johang")
@Log
public class JohangController {
    @Autowired
    JohangRepository repository;
    private String baseDir = "/Users/osejin/fishImg/";

    @Autowired
    ImageVORepository imageVORepository;

    @Autowired
    MemberRepository memberRepository;

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
    public String registerp(String title, String content, HttpServletResponse response
                                , @RequestParam MultipartFile[] fileUpload, Principal principal) throws Exception {
        JohangBoard board = new JohangBoard();
        board.setTitle(title);
        board.setContent(content);

        Member member = memberRepository.findMemberByUid(principal.getName());
        System.out.println(principal.getName());
        if(member==null) {
            member.setUname("비회원");
        }
        board.setMember(member);

        String saveFileName="";

        if(fileUpload!=null && fileUpload.length>0) {
            String formmatedData = baseDir + new SimpleDateFormat("yyyy"+File.separator+"MM"
                    +File.separator+"dd").format(new Date());
            File f = new File(formmatedData);
            if(!f.exists()) {
                f.mkdirs();
            }

            for(MultipartFile file : fileUpload) {
                String uuid = UUID.randomUUID().toString();
                saveFileName = formmatedData + File.separator + uuid +".jpeg";
                response.setContentType(file.getContentType());
                response.setContentLength((int)file.getSize());

                imageVORepository.saveImage(saveFileName);

                try(InputStream in = file.getInputStream();
                    FileOutputStream out = new FileOutputStream(saveFileName)){

                    int count = 0;
                    byte[] buffer = new byte[1024];
                    while((count=in.read(buffer))!=-1) {
                        out.write(buffer,0,count);
                    }
                }catch(Exception e){
                    e.getMessage();
                }
            }
        }
        board.setImagevo(imageVORepository.findByFilename(saveFileName));
        repository.save(board);

        return "redirect:/johang/list";
    }
}
