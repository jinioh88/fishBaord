package com.fishing.fishboard.persistence;

import com.fishing.fishboard.domain.WebReply;
import org.springframework.data.repository.CrudRepository;

public interface WebReplyRepository extends CrudRepository<WebReply,Long> {
}
