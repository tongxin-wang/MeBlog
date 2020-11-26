package com.lzw.meblog.mapper;

import com.lzw.meblog.model.ArticleContent;
import org.springframework.stereotype.Repository;

/**
 * ArticleContentMapper继承基类
 */
@Repository
public interface ArticleContentMapper extends MyBatisBaseDao<ArticleContent, Integer> {
}