package com.lzw.meblog.controller;

import com.lzw.meblog.model.Post;
import com.lzw.meblog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @CrossOrigin("http://localhost:8080")
    @RequestMapping("/getPost/{id}")
    public Post getPost(@PathVariable int id){
        return postService.getPostById(id);
    }

    @CrossOrigin("http://localhost:8080")
    @RequestMapping("/getPost/all")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }
}
