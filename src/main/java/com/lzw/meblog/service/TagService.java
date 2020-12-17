package com.lzw.meblog.service;

import com.lzw.meblog.dto.TagDto;
import com.lzw.meblog.dto.TagPostsDto;
import com.lzw.meblog.mapper.TagMapper;
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
}
