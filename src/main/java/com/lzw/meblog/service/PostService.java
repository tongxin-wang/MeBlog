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
    public String updatePost(DetailedPostDto detailedPostDto){
        //更新post中的信息
        Post post = new Post();
        post.setId(detailedPostDto.getId());
        post.setTitle(detailedPostDto.getTitle());
        post.setSummary(detailedPostDto.getSummary());
        post.setImgUrl(detailedPostDto.getImgUrl());
        post.setGmtCreate(detailedPostDto.getGmtCreate());
        postMapper.updateByPrimaryKeySelective(post);

         //获取新的Category信息
         List<CategoryDto> categories = detailedPostDto.getCategories();
         //删除原有表的信息
         postCategoryMapper.deleteByPostId(detailedPostDto.getId());
         //添加新的Category信息
         for (CategoryDto categoryDto:categories) {
            PostCategory postCategory = new PostCategory();
            //获取Category_id
            postCategory.setCategoryId(categoryMapper.selectIfExist(categoryDto.getName()).getId());
            postCategory.setPostId(detailedPostDto.getId());
            postCategory.setGmtCreate(detailedPostDto.getGmtCreate());
            postCategory.setGmtModified(detailedPostDto.getGmtModified());
            //id呢？自增
            postCategoryMapper.insertSelective(postCategory);
         }

        //获取新的Category信息
        List<TagDto> tagDtos = detailedPostDto.getTags();
        //删除原有表的信息
        postTagMapper.deleteByPostId(detailedPostDto.getId());
        //添加新的Category信息
         for (TagDto tagDto:tagDtos) {
            PostTag postTag = new PostTag();
            postTag.setTagId(tagDto.getId());
            postTag.setPostId(detailedPostDto.getId());
            postTag.setGmtCreate(detailedPostDto.getGmtCreate());
            postTag.setGmtModified(detailedPostDto.getGmtModified());
            //id呢？自增
            postTagMapper.insertSelective(postTag);
         }
        return "进行更新操作";
    }

    /**
    * @Description: 更新一篇文章的分类
    * @Param: post_id, category_id
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

    /**
    * @Description: 更新一篇文章的标签信息
    * @Param: post_id, tag_id
    * @author: LJ
    * @Date: 2020/12/24
    **/
    public void updateTag(int post_id, int tag_id){
        PostTag postTag;
        postTag = postTagMapper.selectByPostId(post_id);
        //更新标签id信息
        postTag.setTagId(tag_id);
        postTagMapper.updateByPrimaryKeySelective(postTag);
    }

}
