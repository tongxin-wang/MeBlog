package com.lzw.meblog.mapper;

import com.lzw.meblog.dto.TagDto;
import com.lzw.meblog.dto.TagPostsDto;
import com.lzw.meblog.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * TagMapper继承基类
 */
@Repository
public interface TagMapper extends MyBatisBaseDao<Tag, Integer> {
    List<TagDto> getAllTags();
    TagPostsDto getPostsByTagId(int id);
}