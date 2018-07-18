package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.FishBoard;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

public interface FishBoardRepository extends CrudRepository<FishBoard,Long>, QuerydslPredicateExecutor<FishBoard> {

}
