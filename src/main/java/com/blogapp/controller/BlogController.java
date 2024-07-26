package com.blogapp.controller;

import com.blogapp.entity.BlogPost;
import com.blogapp.payload.BlogPostDto;
import com.blogapp.service.BlogPostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/v1/blogPosts")
public class BlogController {

    private BlogPostService  blogPostService;

    public BlogController(BlogPostService blogPostService) {
        this.blogPostService = blogPostService;
    }
// http://localhost:8080/api/v1/blogPosts/addPost
    @PostMapping("/addPost")
    public ResponseEntity<?> addPost(@Valid @RequestBody BlogPostDto postDto, BindingResult result){
        if (result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.BAD_REQUEST);
        }
         BlogPostDto blogPost = blogPostService.saveOrUpdateBlogPost(postDto);
        return new ResponseEntity<>(blogPost, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/v1/blogPosts?id=2
    @DeleteMapping()
    public ResponseEntity<String> deleteBlogPost(@RequestParam long id){
        blogPostService.deleteBlogPost(id);
        return new ResponseEntity<>("Post Deleted",HttpStatus.OK);
    }

    // http://localhost:8080/api/v1/blogPosts
    @GetMapping
    public ResponseEntity<List<BlogPost>> getAllBlogPosts(){
        List<BlogPost> posts = blogPostService.getAllBlogPosts();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

//    http://localhost:8080/api/v1/blogPost/byId?postId=5
    @GetMapping("/byId")
    public ResponseEntity<BlogPost> getBlogPostById(@RequestParam long postId){
        BlogPost post = blogPostService.getBlogPostById(postId);
        return new ResponseEntity<>(post,HttpStatus.OK);
    }

    // http://localhost:8080/api/v1/blogPost/6
    @PutMapping("/{postId}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable long postId, @RequestBody BlogPostDto blogPostDto){
        BlogPost post = blogPostService.updatePost(postId,blogPostDto);
        return new ResponseEntity<>(post,HttpStatus.CREATED);
    }
}
