package com.lzw.meblog.mapper;

import com.lzw.meblog.model.ArticleCategory;
import org.springframework.stereotype.Repository;

/**
 * ArticleCategoryMapper继承基类
 */
@Repository
public interface ArticleCategoryMapper extends MyBatisBaseDao<ArticleCategory, Integer> {
}