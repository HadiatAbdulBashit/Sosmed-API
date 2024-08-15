package com.hadiat.sosmed.api.controller;

import com.hadiat.sosmed.api.model.Post;
import com.hadiat.sosmed.api.service.PostService;
import com.hadiat.sosmed.api.utils.dto.PostRequestDTO;
import com.hadiat.sosmed.api.utils.responseWrapper.PaginationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Post> create(@RequestBody PostRequestDTO request) {
        return new ResponseEntity<>(postService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> findAllPost(@PageableDefault Pageable pageable) {
        Page<Post> voucherPage = postService.findAll(pageable);
        return new ResponseEntity<>(new PaginationResponse<>(voucherPage), HttpStatus.OK);
    }

    @GetMapping("/my/posts")
    public ResponseEntity<?> findAllPostByUser(@PageableDefault Pageable pageable) {
        Page<Post> voucherPage = postService.findAllByUser(pageable);
        return new ResponseEntity<>(new PaginationResponse<>(voucherPage), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Integer id) {
        return new ResponseEntity<>(postService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/my/posts/{id}")
    public ResponseEntity<Post> updatePostById(@PathVariable Integer id, @RequestBody PostRequestDTO postRequestDTO) {
        return new ResponseEntity<>(postService.updateById(id, postRequestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/my/posts/{id}")
    public ResponseEntity<Post> deletePostById(@PathVariable Integer id) {
        postService.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
}
