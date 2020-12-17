package com.lzw.meblog.controller;

import com.lzw.meblog.dto.*;
import com.lzw.meblog.model.Post;
import com.lzw.meblog.model.Tag;
import com.lzw.meblog.service.CategoryService;
import com.lzw.meblog.service.PostService;
import com.lzw.meblog.service.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 前端请求控制器
 * @author TongxinWang
 */
@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:8080")
@Api(description = "提供给博客网站访问者的接口")
public class FrontController {
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;

    /**
     * 获取所有文章
     * @return 文章列表
     */
    @ApiOperation(value = "获取所有文章")
    @GetMapping("/posts")
    public List<PostDto> getAllPosts(){
        return postService.getAllPosts();
    }

    /**
     * 根据id获取文章详细信息
     * @param id
     * @return 文章详细信息
     */
    @ApiOperation(value = "获取文章详细信息", notes = "根据文章id获取文章详细信息")
    @ApiImplicitParam(name = "id", value = "文章id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/post/{id}")
    public DetailedPostDto getDetailedPost(@PathVariable int id){
        return postService.getDetailedPostById(id);
    }

    /**
     * 获取所有分类
     * @return 分类列表
     */
    @ApiOperation(value = "获取所有分类")
    @GetMapping("/categories")
    public List<CategoryDto> getAllCategories(){
        return categoryService.getAllCategories();
    }

    /**
     * 根据分类id获取某一分类下的所有文章
     * @param id
     * @return 该分类下的文章列表
     */
    @ApiOperation(value = "获取某一分类下的所有文章", notes = "根据分类id获取该分类下的文章列表")
    @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/category/{id}/posts")
    public CategoryPostsDto getPostsByCategoryId(@PathVariable int id){
        return categoryService.getPostsByCategoryId(id);
    }

    /**
     * 获取所有标签
     * @return 标签列表
     */
    @ApiOperation(value = "获取所有标签")
    @GetMapping("/tags")
    public List<TagDto> getAllTags(){
        return tagService.getAllTags();
    }

    /**
     * 根据标签id获取某一标签下的所有文章
     * @param id
     * @return 该标签下的文章列表
     */
    @ApiOperation(value = "获取某一标签下的所有文章", notes = "根据标签id获取该标签下的文章列表")
    @ApiImplicitParam(name = "id", value = "标签id", required = true, dataType = "int", paramType = "path")
    @GetMapping("/tag/{id}/posts")
    public TagPostsDto getPostsByTagId(@PathVariable int id){
        return tagService.getPostsByTagId(id);
    }
}
