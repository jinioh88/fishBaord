package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.JohangBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JohangRepository extends JpaRepository<JohangBoard,Long> {

}
