package com.effective_mobile.socialmedia.repositories;

import com.effective_mobile.socialmedia.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long>  {
    Optional<User> findByUsername(String username);
}
