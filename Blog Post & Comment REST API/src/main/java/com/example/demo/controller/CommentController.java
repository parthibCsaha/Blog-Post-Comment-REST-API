package com.example.demo.controller;

import com.example.demo.service.Comment;
import com.example.demo.repo.BlogPostRepository;
import com.example.demo.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @GetMapping
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        if (!blogPostRepository.existsById(postId)) {
            return ResponseEntity.notFound().build();
        }
        List<Comment> comments = commentRepository.findByBlogPostId(postId);
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return blogPostRepository.findById(postId)
                .map(post -> {
                    comment.setBlogPost(post);
                    Comment savedComment = commentRepository.save(comment);
                    return ResponseEntity.ok(savedComment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        if (!blogPostRepository.existsById(postId)) {
            return ResponseEntity.notFound().build();
        }
        return commentRepository.findById(commentId)
                .map(comment -> {
                    commentRepository.delete(comment);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
