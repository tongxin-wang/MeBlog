package com.lzw.meblog.mapper;

import com.lzw.meblog.model.PostCategory;
import org.springframework.stereotype.Repository;

/**
 * PostCategoryMapper继承基类
 */
@Repository
public interface PostCategoryMapper extends MyBatisBaseDao<PostCategory, Integer> {
}