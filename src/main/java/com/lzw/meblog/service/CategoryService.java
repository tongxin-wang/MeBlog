package com.lzw.meblog.service;

import com.lzw.meblog.dto.CategoryDto;
import com.lzw.meblog.dto.CategoryPostsDto;
import com.lzw.meblog.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories(){
        return categoryMapper.getAllCategories();
    }

    public CategoryPostsDto getPostsByCategoryId(int id){
        return categoryMapper.getPostsByCategoryId(id);
    }
}
