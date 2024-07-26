package com.blogapp.service;

import com.blogapp.entity.BlogPost;
import com.blogapp.payload.BlogPostDto;

import java.util.List;

public interface BlogPostService {
    BlogPostDto saveOrUpdateBlogPost(BlogPostDto postDto);

    void deleteBlogPost(long id);

    List<BlogPost> getAllBlogPosts();

    BlogPost getBlogPostById(long postId);

    BlogPost updatePost(long postId, BlogPostDto blogPostDto);
}
