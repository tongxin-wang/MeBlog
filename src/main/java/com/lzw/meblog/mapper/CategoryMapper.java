package com.lzw.meblog.mapper;

import com.lzw.meblog.dto.CategoryDto;
import com.lzw.meblog.dto.CategoryPostsDto;
import com.lzw.meblog.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CategoryMapper继承基类
 */
@Repository
public interface CategoryMapper extends MyBatisBaseDao<Category, Integer> {
    List<CategoryDto> getAllCategories();
    CategoryPostsDto getPostsByCategoryId(int id);

    Category selectIfExist(String name);

}