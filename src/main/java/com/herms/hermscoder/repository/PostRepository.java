package com.herms.hermscoder.repository;

import com.herms.hermscoder.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
