package com.lzw.meblog.mapper;

import com.lzw.meblog.dto.DetailedPostDto;
import com.lzw.meblog.dto.PostDto;
import com.lzw.meblog.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PostMapper继承基类
 */
@Repository
public interface PostMapper extends MyBatisBaseDao<Post, Integer> {
    DetailedPostDto getDetailedPostById(int id);
    List<PostDto> getAllPosts();
}