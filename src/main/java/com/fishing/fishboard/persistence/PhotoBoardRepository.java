package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.PhotoBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard,Long> {

}
