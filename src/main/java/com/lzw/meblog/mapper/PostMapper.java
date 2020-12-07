package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * PostMapper继承基类
 */
@Repository
public interface PostMapper extends MyBatisBaseDao<Post, Integer> {
    List<Post> getAllPosts();
}