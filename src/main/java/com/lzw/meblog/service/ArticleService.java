package com.lzw.meblog.service;

import com.lzw.meblog.mapper.ArticleMapper;
import com.lzw.meblog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    public Article getArticleById(int id){
        return articleMapper.selectByPrimaryKey(id);
    }
}
