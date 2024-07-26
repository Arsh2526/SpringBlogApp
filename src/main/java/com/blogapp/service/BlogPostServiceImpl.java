package com.blogapp.service;

import com.blogapp.entity.BlogPost;
import com.blogapp.exception.ResourceNotFoundException;
import com.blogapp.payload.BlogPostDto;
import com.blogapp.repository.BlogPostRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostServiceImpl implements BlogPostService{

    private BlogPostRepository blogPostRepository;

    public BlogPostServiceImpl(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }


    @Override
    public BlogPostDto saveOrUpdateBlogPost(BlogPostDto postDto) {
        BlogPost blogPost = new BlogPost();
        blogPost.setTitle(postDto.getTitle());
        blogPost.setContent(postDto.getContent());

        BlogPost saved = blogPostRepository.save(blogPost);

        postDto.setId(saved.getId());
        postDto.setTitle(saved.getTitle());
        postDto.setContent(saved.getContent());
        postDto.setDate(new Date());

        return postDto;
    }

    @Override
    public void deleteBlogPost(long id) {
        blogPostRepository.deleteById(id);
    }

    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public BlogPost getBlogPostById(long postId) {
        Optional<BlogPost> opPost = blogPostRepository.findById(postId);
        if (opPost.isPresent()){
            BlogPost blogPost = opPost.get();
            return blogPost;
        }else{
            throw new ResourceNotFoundException("post with id "+postId+" not found");
        }

    }

    @Override
    public BlogPost updatePost(long postId, BlogPostDto blogPostDto) {

        Optional<BlogPost> opPost = blogPostRepository.findById(postId);
        BlogPost post=null;
        if (opPost.isPresent()) {
             post = opPost.get();
        }
        post.setTitle(blogPostDto.getTitle());
        post.setContent(blogPostDto.getContent());

        return blogPostRepository.save(post);
    }
}
