package com.fishing.fishboard.controller;

import com.fishing.fishboard.aws.S3Uploader;
import com.fishing.fishboard.domain.JohangBoard;
import com.fishing.fishboard.domain.Member;
import com.fishing.fishboard.persistence.ImageVORepository;
import com.fishing.fishboard.persistence.JohangRepository;
import com.fishing.fishboard.persistence.MemberRepository;
import com.fishing.fishboard.vo.PageMaker;
import com.fishing.fishboard.vo.PageVO;
import com.fishing.fishboard.vo.PageVO2;
import com.sun.xml.internal.bind.v2.TODO;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/johang")
@RequiredArgsConstructor
@Log
public class JohangController {
    @Autowired
    JohangRepository repository;
    private String baseDir = "/Users/osejin/fishImg/";

    @Autowired
    ImageVORepository imageVORepository;

    @Autowired
    MemberRepository memberRepository;

    private final S3Uploader s3Uploader;

    @GetMapping("/list")
    public String board(@ModelAttribute("pageVO") PageVO2 vo, Model model) {
//        List<JohangBoard> list = repository.findAll();
//        for(int i=0;i<list.size();i++) {
//            System.out.println(list.get(i).getMember().getUname());
//        }
//        model.addAttribute("list",list);
//
//
//        return "/johang/johang";
        Pageable page = vo.makePageable(0,"jno");
//        Pageable page = PageRequest.of(0,20, Sort.Direction.DESC,"jno");
        Page<JohangBoard> result = repository.findAll(repository.makePredicate(null,null),page);
        model.addAttribute("result",new PageMaker(result));
        return "/johang/johang";
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @GetMapping("/register")
    public String register(Model model) {

        return "/johang/register";
    }

    // TODO : aws에 올라가는 파일명 수정해야 함.
    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/register")
    public String registerp(String title, String content, HttpServletResponse response
                                , @RequestParam MultipartFile fileUpload, Principal principal) throws Exception {
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

        if(fileUpload!=null && fileUpload.getSize()>0) {
            String formmatedData = baseDir + new SimpleDateFormat("yyyy"+File.separator+"MM"
                    +File.separator+"dd").format(new Date());
            File f = new File(formmatedData);
            if(!f.exists()) {
                f.mkdirs();
            }

                MultipartFile file = fileUpload;
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
        board.setImagevo(imageVORepository.findByFilename(saveFileName));
        repository.save(board);
        s3Uploader.upload(fileUpload, "static");

        return "redirect:/johang/list";
    }
}
