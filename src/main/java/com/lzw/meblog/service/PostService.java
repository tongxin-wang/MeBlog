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
    @Autowired
    private TagMapper tagMapper;


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
    * @Date: 2020/12/08
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

        //增加postCategory信息
        List<CategoryDto> categories = detailedPostDto.getCategories();
        for (CategoryDto categoryDto:categories
             ) {
            //获取name，检查是否在数据库中存在
            Category category = categoryMapper.selectIfExist(categoryDto.getName());
            if (category==null)
            {
                //不存在则新增信息
                category = new Category();
                category.setName(categoryDto.getName());
                categoryMapper.insertSelective(category);
            }
            categoryDto.setId(category.getId());
            PostCategory postCategory = new PostCategory();
            postCategory.setCategoryId(categoryDto.getId());
            postCategory.setPostId(detailedPostDto.getId());
            postCategoryMapper.insertSelective(postCategory);
        }

        //增加postTag
        List<TagDto> tagDtos = detailedPostDto.getTags();
        for (TagDto tagDto:tagDtos
             ) {
            //获取name，检查是否在数据库中存在
            Tag tag = tagMapper.selectIfExist(tagDto.getName());
            if (tag==null)
            {
                //不存在则新增信息
                tag = new Tag();
                tag.setName(tagDto.getName());
                tagMapper.insertSelective(tag);
            }
            tagDto.setId(tag.getId());
            PostTag postTag = new PostTag();
            postTag.setTagId(tagDto.getId());
            postTag.setPostId(detailedPostDto.getId());
            postTagMapper.insertSelective(postTag);
        }
    }

    /**
    * @Description: 删除一篇文章
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/09
    **/
    public void DeletePost(int id){
        //从post中删除,级联删除
        postMapper.deleteByPrimaryKey(id);
    }
    /**
    * @Description: 更新文章信息
    * @Param: detailedPostDto
    * @author: LJ
    * @Date: 2020/12/09
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

         //获取新的Category信息,这时候只有名字信息
         List<CategoryDto> categories = detailedPostDto.getCategories();
         //删除原有表的信息
         postCategoryMapper.deleteByPostId(detailedPostDto.getId());
         //添加新的Category信息
         for (CategoryDto categoryDto:categories) {
             //获取name，检查是否在数据库中存在
             Category category = categoryMapper.selectIfExist(categoryDto.getName());
             if (category==null)
             {
                 //不存在则新增信息
                 category = new Category();
                 category.setName(categoryDto.getName());
                 categoryMapper.insertSelective(category);
             }
             categoryDto.setId(category.getId());
            PostCategory postCategory = new PostCategory();
            //获取Category_id
            postCategory.setCategoryId(categoryDto.getId());
            postCategory.setPostId(detailedPostDto.getId());
            postCategoryMapper.updateByPrimaryKeySelective(postCategory);
         }

        //获取新的Category信息
        List<TagDto> tagDtos = detailedPostDto.getTags();
        //删除原有表的信息
        postTagMapper.deleteByPostId(detailedPostDto.getId());
        //添加新的Category信息
         for (TagDto tagDto:tagDtos) {
             //获取name，检查是否在数据库中存在
             Tag tag = tagMapper.selectIfExist(tagDto.getName());
             if (tag==null)
             {
                 //不存在则新增信息
                 tag = new Tag();
                 tag.setName(tagDto.getName());
                 tagMapper.insertSelective(tag);
             }
             tagDto.setId(tag.getId());
            PostTag postTag = new PostTag();
            //获取tag_id
            postTag.setTagId(tagDto.getId());
            postTag.setPostId(detailedPostDto.getId());
            postTagMapper.insertSelective(postTag);
         }
        return "进行更新操作";
    }

    /**
    * @Description: 更新一篇文章的分类
    * @Param: post_id, category_id
    * @author: LJ
    * @Date: 2020/12/11
    **/
    public void updatePostCategory(int post_id, String name){
        Category category;
        category = categoryMapper.selectIfExist(name);
        //更新分类id信息
        PostCategory postCategory = new PostCategory();
        postCategory.setPostId(post_id);
        postCategory.setId(category.getId());
        postCategoryMapper.updateByPrimaryKeySelective(postCategory);
    }


    /**
    * @Description: 更新一篇文章的标签信息
    * @Param: post_id, tag_id
    * @author: LJ
    * @Date: 2020/12/14
    **/
    public void updateTag(int post_id, String name){
        PostTag postTag;
        Tag tag = tagMapper.selectIfExist(name);
        postTag = postTagMapper.selectByPostId(post_id);
        //更新标签id信息
        postTag.setTagId(tag.getId());
        postTagMapper.updateByPrimaryKeySelective(postTag);
    }

}
