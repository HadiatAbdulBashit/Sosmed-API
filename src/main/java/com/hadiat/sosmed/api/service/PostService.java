package com.hadiat.sosmed.api.service;

import com.hadiat.sosmed.api.model.Post;
import com.hadiat.sosmed.api.utils.dto.PostRequestDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostService {
    Post create(PostRequestDTO newPost);
    Page<Post> findAll(Pageable pageable);
    Page<Post> findAllByUser(Pageable pageable);
    Post findById(Integer id);
    Post updateById(Integer id, PostRequestDTO updatedPost);
    void deleteById(Integer id);
}
