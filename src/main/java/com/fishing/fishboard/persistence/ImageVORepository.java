package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.ImageVO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageVORepository extends JpaRepository<ImageVO,Long> {
    default void saveImage(String s) {
        ImageVO vo = new ImageVO();
        vo.setFilename(s);
        this.save(vo);
    }

    public ImageVO findByFilename(String s);
}
