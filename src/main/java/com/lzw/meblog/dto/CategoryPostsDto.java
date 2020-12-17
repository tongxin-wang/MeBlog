package com.lzw.meblog.dto;

import java.util.List;

/**
 * 分类及所包含的文章信息类
 * @author TongxinWang
 */
public class CategoryPostsDto {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String name;

    /**
     *所包含的文章列表
     */
    private List<PostDto> posts;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDto> posts) {
        this.posts = posts;
    }
}
