package com.fishing.fishboard.controller;

import com.fishing.fishboard.domain.WebReply;
import com.fishing.fishboard.persistence.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/replies/*")
@Log
public class WebReplyController {

    @Autowired
    private WebReplyRepository repository;

    @PostMapping("/{bno}")
    public ResponseEntity<Void> addReply(@PathVariable("bno")Long bno, @RequestBody WebReply reply) {
        log.info("reply add...");

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
