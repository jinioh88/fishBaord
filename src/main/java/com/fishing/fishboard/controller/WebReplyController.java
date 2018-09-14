package com.fishing.fishboard.controller;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.domain.WebReply;
import com.fishing.fishboard.persistence.FishBoardRepository;
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
    private WebReplyRepository replyRepo;

    @Autowired
    private FishBoardRepository boardRepo;

    @GetMapping("/{bno}")
    public ResponseEntity<List<WebReply>> getReplies(
            @PathVariable("bno")Long bno){

        log.info("get All Replies..........................");

        FishBoard board = new FishBoard();
        board.setBno(bno);
        return new ResponseEntity<>(getListByBoard(board),HttpStatus.OK );
    }

    @Transactional
    @PostMapping("/{bno}")
    public ResponseEntity<List<WebReply>> addReply(
            @PathVariable("bno")Long bno,
            @RequestBody WebReply reply){

        log.info("addReply..........................");
        log.info("BNO: " + bno);
        log.info("REPLY: " + reply);

        FishBoard board = new FishBoard();
        board.setBno(bno);

        reply.setBoard(board);

        replyRepo.save(reply);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.CREATED);

    }

    @Transactional
    @DeleteMapping("/{bno}/{rno}")
    public ResponseEntity<List<WebReply>> remove(
            @PathVariable("bno")Long bno,
            @PathVariable("rno")Long rno){

        log.info("delete reply: "+ rno);

        replyRepo.deleteById(rno);

        FishBoard board = new FishBoard();
        board.setBno(bno);

        return  new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);

    }



    @Transactional
    @PutMapping("/{bno}")
    public ResponseEntity<List<WebReply>> modify(@PathVariable("bno")Long bno,
                                                 @RequestBody WebReply reply){

        log.info("modify reply: "+ reply);

        replyRepo.findById(reply.getRno()).ifPresent(origin -> {

            origin.setReplyText(reply.getReplyText());
            origin.setReplyer(reply.getReplyer());

            replyRepo.save(origin);
        });

        FishBoard board = new FishBoard();
        board.setBno(bno);

        return new ResponseEntity<>(getListByBoard(board), HttpStatus.OK);
    }

    private List<WebReply> getListByBoard(FishBoard board)throws RuntimeException{

        log.info("getListByBoard...." + board);
        return replyRepo.getWebRepliesOfBoard(board);
    }


}