package com.example.demo.controller;

import com.example.demo.service.BlogPost;
import com.example.demo.repo.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getPostById(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(post -> ResponseEntity.ok(post))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updatePost(@PathVariable Long id, @RequestBody BlogPost postDetails) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    post.setTitle(postDetails.getTitle());
                    post.setContent(postDetails.getContent());
                    post.setAuthor(postDetails.getAuthor());
                    BlogPost updatedPost = blogPostRepository.save(post);
                    return ResponseEntity.ok(updatedPost);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        return blogPostRepository.findById(id)
                .map(post -> {
                    blogPostRepository.delete(post);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
