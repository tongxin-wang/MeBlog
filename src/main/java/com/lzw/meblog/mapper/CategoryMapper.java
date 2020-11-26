package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Category;
import org.springframework.stereotype.Repository;

/**
 * CategoryMapper继承基类
 */
@Repository
public interface CategoryMapper extends MyBatisBaseDao<Category, Integer> {
}