package com.effective_mobile.socialmedia.repositories;

import com.effective_mobile.socialmedia.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository <Post, Long> {
    @Query(value = "SELECT o FROM Post o WHERE o.user.id = :id")
    List<Post> findAllPostsByUser(@Param("id") Long id) ;

}
