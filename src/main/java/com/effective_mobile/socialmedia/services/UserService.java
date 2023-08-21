package com.effective_mobile.socialmedia.services;

import com.effective_mobile.socialmedia.DTO.RegistrationUserDto;
import com.effective_mobile.socialmedia.models.User;
import com.effective_mobile.socialmedia.repositories.UserRepository;
import com.effective_mobile.socialmedia.security.UserDetail;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }

    public Optional<User> findByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);

        if (user.isEmpty())
            throw new UsernameNotFoundException("User not found");
        return new UserDetail(user.get());
    }

    @Transactional
    public User registerUser (RegistrationUserDto registrationUserDto) {
        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setEmail(registrationUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationUserDto.getPassword()));
        user.setRole("ROLE_USER");
        return userRepository.save(user);
    }
}
