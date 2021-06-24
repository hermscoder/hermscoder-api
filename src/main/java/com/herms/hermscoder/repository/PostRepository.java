package com.herms.hermscoder.repository;

import com.herms.hermscoder.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT (p) FROM Post p order by date desc")
    List<Post> findAllOrderByDateDesc();
}
