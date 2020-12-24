package com.lzw.meblog.mapper;

import com.lzw.meblog.model.PostTag;
import org.springframework.stereotype.Repository;

/**
 * PostTagMapper继承基类
 */
@Repository
public interface PostTagMapper extends MyBatisBaseDao<PostTag, Integer> {

    public PostTag selectByPostId(int post_id);
    public void deleteByPostId(int post_id);
}