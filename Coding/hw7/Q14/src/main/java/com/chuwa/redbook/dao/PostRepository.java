package com.chuwa.redbook.dao;

import com.chuwa.redbook.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // No need to write code
    List<Post> findByTitle(String title);

    // Custom query method to find posts that contain a specific keyword in the title
    List<Post> findByTitleContaining(String keyword);

    // Custom query method to find posts by title and order by creation date descending
    List<Post> findByTitleOrderByCreateDateTimeDesc(String title);
}