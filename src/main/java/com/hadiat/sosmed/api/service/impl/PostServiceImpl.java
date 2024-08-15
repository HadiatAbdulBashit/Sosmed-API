package com.hadiat.sosmed.api.service.impl;

import com.hadiat.sosmed.api.model.Post;
import com.hadiat.sosmed.api.model.User;
import com.hadiat.sosmed.api.model.enums.PostStatus;
import com.hadiat.sosmed.api.repository.PostRepository;
import com.hadiat.sosmed.api.service.AuthenticationService;
import com.hadiat.sosmed.api.service.PostService;
import com.hadiat.sosmed.api.utils.dto.PostRequestDTO;
import com.hadiat.sosmed.api.utils.specifitation.PostSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final AuthenticationService authenticationService;

    @Override
    public Post create(PostRequestDTO newPost) {
        User user = authenticationService.getUserAuthenticated();
        return postRepository.save(
                Post.builder()
                        .title(newPost.getTitle())
                        .description(newPost.getDescription())
                        .dueDate(newPost.getDueDate())
                        .status(PostStatus.POSTED)
                        .user(user)
                        .createdAt(LocalDateTime.now())
                        .build()
                );
    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public Page<Post> findAllByUser(Pageable pageable) {
        Specification<Post> spec = PostSpecification.postByUser(authenticationService.getUserAuthenticated());
        return postRepository.findAll(spec, pageable);
    }

    @Override
    public Post findById(Integer id) {
        User user = authenticationService.getUserAuthenticated();
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("To Do Not Found"));
        if (!post.getUser().equals(user)) {
            throw new RuntimeException("You don't have agreement to see other todos");
        }
        return post;
    }


    @Override
    public Post updateById(Integer id, PostRequestDTO updatedPost) {
        User user = authenticationService.getUserAuthenticated();
        Post selectedPost = postRepository.findById(id).orElseThrow(() -> new RuntimeException("To Do Not Found"));
        if (!selectedPost.getUser().equals(user)) {
            throw new RuntimeException("You don't have agreement to edit other todos");
        }
        if (!updatedPost.getTitle().isEmpty()) selectedPost.setTitle(updatedPost.getTitle());
        if (!updatedPost.getDescription().isEmpty()) selectedPost.setDescription(updatedPost.getDescription());
        if (updatedPost.getStatus() != null) selectedPost.setStatus(updatedPost.getStatus());
        if (updatedPost.getDueDate().isAfter(LocalDate.of(2020, 1,1))) selectedPost.setDueDate(updatedPost.getDueDate());
        return postRepository.save(selectedPost);
    }

    @Override
    public void deleteById(Integer id) {
        User user = authenticationService.getUserAuthenticated();
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("To Do Not Found"));
        if (!post.getUser().equals(user)) {
            throw new RuntimeException("You don't have agreement to see other todos");
        }
        postRepository.deleteById(id);
    }
}
