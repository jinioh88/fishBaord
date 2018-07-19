package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.domain.QFishBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FishBoardRepository extends CrudRepository<FishBoard,Long>, QuerydslPredicateExecutor<FishBoard> {
    public default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        QFishBoard board = QFishBoard.fishBoard;
        builder.and(board.bno.gt(0));

        if(type==null) {
            return builder;
        }

        switch(type) {
            case "t":
                builder.and(board.title.like("%"+keyword+"%"));
                break;
            case "c":
                builder.and(board.content.like("%"+keyword+"%"));
                break;
            case "w":
                builder.and(board.writer.like("%"+keyword+"%"));
                break;
        }

        return builder;
    }
}
