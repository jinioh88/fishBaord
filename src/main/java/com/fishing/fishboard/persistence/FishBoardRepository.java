package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.FishBoard;
import com.fishing.fishboard.domain.QFishBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

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

//    @Query("select b.bno, b.title, b.writer, b.regdate, count(r) from FishBoard b " +
//            "left outer join b.replies r where b.bno>0 group by b")
//    public List<FishBoard> getListWithQuery(Pageable page);

}
