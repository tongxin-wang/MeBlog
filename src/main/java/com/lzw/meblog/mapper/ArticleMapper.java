package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Article;
import org.springframework.stereotype.Repository;

/**
 * ArticleMapper继承基类
 */
@Repository
public interface ArticleMapper extends MyBatisBaseDao<Article, Integer> {
}