package com.chuwa.redbook.dao;

import com.chuwa.redbook.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author b1go
 * @date 6/23/22 11:05 PM
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 不用实现。但要学语法
     * @param postId
     * @return
     */
    // 1. Find comments by post ID
    List<Comment> findByPostId(Long postId);

    // 2. Find comments by content containing a specific keyword (case-insensitive)
    List<Comment> findByContentIgnoreCaseContaining(String keyword);

    // 3. Find comments by post ID and sort them by creation date in descending order
    List<Comment> findByPostIdOrderByCreatedDateTimeDesc(Long postId);

    // 4. Count the number of comments for a specific post
    long countByPostId(Long postId);

    // 5. Delete all comments related to a specific post
    void deleteByPostId(Long postId);
}
