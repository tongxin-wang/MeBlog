package com.lzw.meblog.controller;

import com.lzw.meblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/getArticle/{id}")
    public String getArticle(@PathVariable int id){
        return articleService.getArticleById(id).toString();
    }
}
