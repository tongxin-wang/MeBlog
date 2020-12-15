package com.lzw.meblog.controller;

import com.lzw.meblog.model.Post;
import com.lzw.meblog.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @CrossOrigin("http://localhost:8080")
    @ApiOperation(value = "获取文章列表")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    @CrossOrigin("http://localhost:8080")
    @ApiOperation(value = "获取文章详细信息", notes = "根据id获取文章详细信息")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "int")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable int id){
        return postService.getPostById(id);
    }
}
