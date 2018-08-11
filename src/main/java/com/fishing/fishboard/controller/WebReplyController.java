package com.fishing.fishboard.controller;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.domain.WebReply;
import com.fishing.fishboard.persistence.WebReplyRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies/*")
@Log
public class WebReplyController {

    @Autowired
    private WebReplyRepository repository;

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<WebReply>> addReply(@PathVariable("bno")Long bno, @RequestBody WebReply reply) {
        log.info("reply add...");

        FishBoard board = new FishBoard();
        board.setBno(bno);

        reply.setBoard(board);
        repository.save(reply);
        return new ResponseEntity<>(getListByBoard(board),HttpStatus.CREATED);
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @Transactional
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<WebReply>> remove(@PathVariable("bno")Long bno, @PathVariable("rno")Long rno) {
        log.info("delete reply..");

        repository.deleteById(rno);

        FishBoard board = new FishBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK);
    }

    @Secured(value = {"ROLE_USER","ROLE_ADMIN"})
    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<WebReply>> modify(@PathVariable("bno")Long bno, @RequestBody WebReply reply) {
        log.info("modify reply.."+reply);

        repository.findById(reply.getRno()).ifPresent(original->{
            original.setReplyText(reply.getReplyText());
            repository.save(original);
        });

        FishBoard board = new FishBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board),HttpStatus.CREATED);
    }

    @GetMapping("/{bno}")
    public ResponseEntity<List<WebReply>> getReplies(@PathVariable("bno")Long bno) {
        log.info("select Replies..");

        FishBoard board = new FishBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK);
    }

    private List<WebReply> getListByBoard(FishBoard board) throws RuntimeException {
        log.info("reply get..."+board);
        return repository.getWebRepliesOfBoard(board);
    }
}
