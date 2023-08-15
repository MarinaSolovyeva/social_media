package com.effective_mobile.socialmedia.services;

import com.effective_mobile.socialmedia.models.Post;
import com.effective_mobile.socialmedia.models.User;
import com.effective_mobile.socialmedia.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Transactional
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

//    @Transactional
//    public void update(Long id, Post updatedPost) {
//        updatedPost.setId(id);
//        postRepository.save(updatedPost);
//    }

    @Transactional
    public Post getPost(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        return foundPost.orElse(null);
    }

    @Transactional
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void addPost(Post post, User user) {
        List <Post> posts = user.getPosts();
        posts.add(post);
    }

    @Transactional
    public void deleteTask(Post post, String username) {
        User user = userService.findByName(username);
        List <Post> posts = user.getPosts();
        posts.remove(post);
    }

    @Transactional
    public List<Post> getAllPostsByUser(User user) {
        List <Post> postList = postRepository.findAllPostsByUser(user.getId());
        return postList;
    }

}
