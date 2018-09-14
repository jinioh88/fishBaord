package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.JohangBoard;
import com.fishing.fishboard.domain.QJohangBoard;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface JohangRepository extends JpaRepository<JohangBoard,Long>, QuerydslPredicateExecutor<JohangBoard> {
    public JohangBoard findByJno(Long id);
    public default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        QJohangBoard board = QJohangBoard.johangBoard;

        builder.and(board.jno.gt(0));

        return builder;
    }
}
