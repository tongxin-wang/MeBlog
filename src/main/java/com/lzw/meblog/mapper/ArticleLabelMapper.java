package com.lzw.meblog.mapper;

import com.lzw.meblog.model.ArticleLabel;
import org.springframework.stereotype.Repository;

/**
 * ArticleLabelMapper继承基类
 */
@Repository
public interface ArticleLabelMapper extends MyBatisBaseDao<ArticleLabel, Integer> {
}