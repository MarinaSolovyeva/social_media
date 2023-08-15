package com.effective_mobile.socialmedia.services;

import com.effective_mobile.socialmedia.models.User;
import com.effective_mobile.socialmedia.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void update(Long id, User updatedUser) {
        Optional<User> oldUser = userRepository.findById(id);
        updatedUser.setId(id);
        updatedUser.setPassword(oldUser.get().getPassword());
        updatedUser.setRole(oldUser.get().getRole());
        updatedUser.setPosts(oldUser.get().getPosts());
        userRepository.save(updatedUser);
    }

    @Transactional
    public User getUser(Long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User findByName(String username) {
        Optional<User> foundUser = userRepository.findByUsername(username);
        return foundUser.orElse(null);
    }
}