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
    public boolean addPost(DetailedPostDto detailedPostDto){
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

        //增加body信息
        BodyDto bodyDto = detailedPostDto.getBody();
        Body body = new Body();
        body.setContent(bodyDto.getContent());
        body.setPostId(post.getId());
        bodyMapper.insertSelective(body);

        //增加postCategory信息
        List<CategoryDto> categoryDtos = detailedPostDto.getCategories();
        addPostCategory(categoryDtos, detailedPostDto);

        //增加postTag
        List<TagDto> tagDtos = detailedPostDto.getTags();
        addPostTag(tagDtos, detailedPostDto);

        if (detailedPostDto.getTitle()==null||detailedPostDto.getBody()==null)
        {
            return false;
        }
        return true;
    }

    /**
    * @Description: 增加post的Category信息
    * @Param: categories, detailedPostDto
    * @author: LJ
    * @Date: 2020/12/25
    **/
    public void addPostCategory(List<CategoryDto> categories, DetailedPostDto detailedPostDto){
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
    }
    /**
    * @Description: 增加post的tag信息
    * @Param: tagDtos, detailedPostDto
    * @author: LJ
    * @Date: 2020/12/25
    **/
    public void addPostTag(List<TagDto> tagDtos, DetailedPostDto detailedPostDto){
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
    public boolean DeletePost(int id){
        //从post中删除,级联删除
        int count = postMapper.deleteByPrimaryKey(id);
        if (count<=0)
        {
            return false;
        }
        return true;
    }
    /**
    * @Description: 更新文章信息
    * @Param: detailedPostDto
    * @author: LJ
    * @Date: 2020/12/09
    **/
    public boolean updatePost(DetailedPostDto detailedPostDto){
        //更新post中的信息
        Post post = new Post();
        post.setId(detailedPostDto.getId());
        post.setTitle(detailedPostDto.getTitle());
        post.setSummary(detailedPostDto.getSummary());
        post.setImgUrl(detailedPostDto.getImgUrl());
        post.setGmtCreate(detailedPostDto.getGmtCreate());
        postMapper.updateByPrimaryKeySelective(post);
        //修改body信息
        BodyDto bodyDto = detailedPostDto.getBody();
        Body body = bodyMapper.selectByPostId(detailedPostDto.getId());
        if (body==null)
        {
            body = new Body();
            body.setContent(bodyDto.getContent());
            body.setPostId(post.getId());
            bodyMapper.insertSelective(body);
        }
        else {
            body.setContent(bodyDto.getContent());
            body.setPostId(post.getId());
            bodyMapper.updateByPostIdSelective(body);
        }
        //更新Post的Category信息
        updatePostCategory(detailedPostDto);
        //更新Post的Tag信息
        updateTag(detailedPostDto);
        if (detailedPostDto.getTitle()==null||detailedPostDto.getBody()==null)
        {
            return false;
        }
        return true;
    }

    /**
    * @Description: 更新一篇文章的分类
    * @Param: post_id, category_id
    * @author: LJ
    * @Date: 2020/12/11
    **/
    public void updatePostCategory(DetailedPostDto detailedPostDto){

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
            postCategoryMapper.insertSelective(postCategory);
        }
    }


    /**
    * @Description: 更新一篇文章的标签信息
    * @Param: post_id, tag_id
    * @author: LJ
    * @Date: 2020/12/14
    **/
    public void updateTag(DetailedPostDto detailedPostDto){
        //获取新的tag信息
        List<TagDto> tagDtos = detailedPostDto.getTags();
        //删除原有表的信息
        postTagMapper.deleteByPostId(detailedPostDto.getId());
        //添加新的tag信息
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
    }

}
