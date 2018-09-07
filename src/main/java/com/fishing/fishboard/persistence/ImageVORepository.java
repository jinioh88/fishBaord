package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.ImageVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageVORepository extends JpaRepository<ImageVO,Long> {
}
