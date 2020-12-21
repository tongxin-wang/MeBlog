package com.lzw.meblog.service;

import com.lzw.meblog.dto.TagDto;
import com.lzw.meblog.dto.TagPostsDto;
import com.lzw.meblog.mapper.TagMapper;
import com.lzw.meblog.model.Category;
import com.lzw.meblog.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    private TagMapper tagMapper;

    public List<TagDto> getAllTags(){
        return tagMapper.getAllTags();
    }

    public TagPostsDto getPostsByTagId(int id){
        return tagMapper.getPostsByTagId(id);
    }

    /**
    * @Description: 添加新的标签
    * @Param: tagDto
    * @author: LJ
    * @Date: 2020/12/21
    **/
    public void addTag(TagDto tagDto){
        //没有该分类信息，其他表无法使用
        Tag tag = new Tag();
        tag.setName(tagDto.getName());
        tagMapper.insertSelective(tag);
    }

    /**
    * @Description: 删除一条标签
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/21
    **/
    public void deleteTag(int id){
        //级联删除
        tagMapper.deleteByPrimaryKey(id);
    }

    /**
    * @Description: 更新标签信息
    * @Param: tagDto
    * @author: LJ
    * @Date: 2020/12/21
    **/
    public void updateTag(TagDto tagDto){
        //级联更新
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        tagMapper.updateByPrimaryKeySelective(tag);
    }



}
