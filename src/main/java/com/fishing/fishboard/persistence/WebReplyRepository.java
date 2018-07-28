package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.domain.WebReply;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WebReplyRepository extends CrudRepository<WebReply,Long> {
    @Query("select r from WebReply r where r.board = ?1 and r.rno > 0 order by r.rno asc")
    public List<WebReply> getWebRepliesOfBoard(FishBoard board);

}
