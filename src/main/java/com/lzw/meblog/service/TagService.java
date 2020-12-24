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
    public String addTag(TagDto tagDto){
        //首先检查该tag是否已经存在
        Tag tag;
        tag = tagMapper.selectIfExist(tagDto.getName());

        if (tag!=null)
        {
            //该标签已经存在，不做插入处理
            return "该标签已经存在，不做插入处理";
        }
        else
        {
            //没有该标签信息，新增
            tag = new Tag();
            tag.setName(tagDto.getName());
            tagMapper.insertSelective(tag);
            return "该标签成功插入";
        }

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
