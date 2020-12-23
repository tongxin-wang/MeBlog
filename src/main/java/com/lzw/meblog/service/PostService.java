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
    @Autowired
    private CategoryMapper categoryMapper;


    public DetailedPostDto getDetailedPostById(int id){
        return postMapper.getDetailedPostById(id);
    }

    public List<PostDto> getAllPosts(){
        return postMapper.getAllPosts();
    }

    /**
    * @Description: 增加一篇文章
    * @Param: detailedPostDto
    * @author: LJ
    * @Date: 2020/12/18
    **/
    public void addPost(DetailedPostDto detailedPostDto){
        //增加post
        Post post = new Post();
        post.setImgUrl(detailedPostDto.getImgUrl());
        post.setSummary(detailedPostDto.getSummary());
        post.setTitle(detailedPostDto.getTitle());
        post.setGmtCreate(detailedPostDto.getGmtCreate());
        post.setGmtModified(detailedPostDto.getGmtModified());
        //插入非空数据,除id以外,并获取生成的主键id
        postMapper.insertSelective(post);
        detailedPostDto.setId(post.getId());

        //在这之前，我应该是知道Category的

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
            postCategoryMapper.insertSelective(postCategory);
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
            postTagMapper.insertSelective(postTag);
        }
    }

    /**
    * @Description: 删除一篇文章
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/19
    **/
    public void DeletePost(int id){
        //从post中删除,级联删除
        postMapper.deleteByPrimaryKey(id);
    }
    /**
    * @Description: 更新文章信息
    * @Param: detailedPostDto
    * @author: LJ
    * @Date: 2020/12/19
    **/
    public void updatePost(DetailedPostDto detailedPostDto){
        //更新post中的信息， 级联更新
        Post post = new Post();
        post.setId(detailedPostDto.getId());
        post.setTitle(detailedPostDto.getTitle());
        post.setSummary(detailedPostDto.getSummary());
        post.setImgUrl(detailedPostDto.getImgUrl());
        post.setGmtCreate(detailedPostDto.getGmtCreate());
        post.setGmtModified(detailedPostDto.getGmtModified());
        postMapper.updateByPrimaryKey(post);
        /**
         //更新postCategory
         List<CategoryDto> categories = detailedPostDto.getCategories();
         for (CategoryDto categoryDto:categories
         ) {
         PostCategory postCategory = new PostCategory();
         postCategory.setCategoryId(categoryDto.getId());
         postCategory.setPostId(detailedPostDto.getId());
         postCategory.setGmtCreate(detailedPostDto.getGmtCreate());
         postCategory.setGmtModified(detailedPostDto.getGmtModified());
         //id呢？自增
         postCategoryMapper.updateByPrimaryKey(postCategory);
         }

         //更新postTag
         List<TagDto> tagDtos = detailedPostDto.getTags();
         for (TagDto tagDto:tagDtos
         ) {
         PostTag postTag = new PostTag();
         postTag.setTagId(tagDto.getId());
         postTag.setPostId(detailedPostDto.getId());
         postTag.setGmtCreate(detailedPostDto.getGmtCreate());
         postTag.setGmtModified(detailedPostDto.getGmtModified());
         //id呢？自增
         postTagMapper.updateByPrimaryKey(postTag);
         }
         **/
    }

    /**
    * @Description: 更新一篇文章的分类
    * @Param: name
    * @author: LJ
    * @Date: 2020/12/21
    **/
    public void updatePostCategory(int post_id, int category_id){
        PostCategory postCategory;
        postCategory = postCategoryMapper.selectByPostId(post_id);
        //更新分类id信息
        postCategory.setCategoryId(category_id);
        postCategoryMapper.updateByPrimaryKeySelective(postCategory);

    }


}
