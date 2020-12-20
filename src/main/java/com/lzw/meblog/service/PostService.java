package com.lzw.meblog.service;

import com.lzw.meblog.dto.*;
import com.lzw.meblog.model.*;
import com.lzw.meblog.mapper.*;
import io.swagger.annotations.ApiImplicitParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private BodyMapper bodyMapper;
    @Autowired
    private PostCategoryMapper postCategoryMapper;
    @Autowired
    private PostTagMapper postTagMapper;

    public DetailedPostDto getDetailedPostById(int id){
        return postMapper.getDetailedPostById(id);
    }

    public List<PostDto> getAllPosts(){
        return postMapper.getAllPosts();
    }

    /**
    * @Description: 增加一篇文章
    * @Param: PostDto 文章封装类
    * @author: LJ
    * @Date: 2020/12/18
    **/
    public void addPost(DetailedPostDto detailedPostDto){
        //增加post
        Post post = new Post();
        post.setId(detailedPostDto.getId());
        post.setImgUrl(detailedPostDto.getImgUrl());
        post.setSummary(detailedPostDto.getSummary());
        post.setTitle(detailedPostDto.getTitle());
        post.setGmtCreate(detailedPostDto.getGmtCreate());
        post.setGmtModified(detailedPostDto.getGmtModified());
        postMapper.insert(post);

        /**
        //增加postCategory
        List<CategoryDto> categories = detailedPostDto.getCategories();
        for (CategoryDto categoryDto:categories
             ) {
            PostCategory postCategory = new PostCategory();
            postCategory.setCategoryId(categoryDto.getId());
            postCategory.setPostId(detailedPostDto.getId());
            postCategory.setGmtCreate(detailedPostDto.getGmtCreate());
            postCategory.setGmtModified(detailedPostDto.getGmtModified());
            //id呢？自增
            postCategoryMapper.insert(postCategory);
        }

        //增加postTag
        List<TagDto> tagDtos = detailedPostDto.getTags();
        for (TagDto tagDto:tagDtos
             ) {
            PostTag postTag = new PostTag();
            postTag.setTagId(tagDto.getId());
            postTag.setPostId(detailedPostDto.getId());
            postTag.setGmtCreate(detailedPostDto.getGmtCreate());
            postTag.setGmtModified(detailedPostDto.getGmtModified());
            //id呢？自增
            postTagMapper.insert(postTag);
        }
         **/
    }

    /**
    * @Description: 删除一篇文章
    * @Param:id
    * @author: LJ
    * @Date: 2020/12/19
    **/
    public void DeletePost(int id){
        //从post中删除,级联删除
        postMapper.deleteByPrimaryKey(id);
    }
    /**
    * @Description: 更新文章信息
    * @Param:
    * @author: LJ
    * @Date: 2020/12/19
    **/
    public void updatePost(){

    }


    /**
     * @Description: 获取刚刚插入数据的id
     * @Param:
     * @author: LJ
     * @Date: 2020/12/20
     **/
    public int getLastId(){

        return 0;
    }

}
