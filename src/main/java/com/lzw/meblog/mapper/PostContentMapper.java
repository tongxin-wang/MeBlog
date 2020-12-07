package com.lzw.meblog.mapper;

import com.lzw.meblog.model.PostContent;
import org.springframework.stereotype.Repository;

/**
 * PostContentMapper继承基类
 */
@Repository
public interface PostContentMapper extends MyBatisBaseDao<PostContent, Integer> {
}