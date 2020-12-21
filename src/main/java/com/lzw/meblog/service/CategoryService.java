package com.lzw.meblog.service;

import com.lzw.meblog.dto.CategoryDto;
import com.lzw.meblog.dto.CategoryPostsDto;
import com.lzw.meblog.mapper.CategoryMapper;
import com.lzw.meblog.model.Category;
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

    /**
    * @Description: 增加分类信息
    * @Param: name
    * @author: LJ
    * @Date: 2020/12/21
    **/
    public void addCategory(CategoryDto categoryDto){
        //没有该分类信息，其他表无法使用
        Category category = new Category();
        category.setName(categoryDto.getName());
        categoryMapper.insertSelective(category);
    }

    /**
    * @Description: 删除分类信息
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/21
    **/
    public void deleteCategory(int id){
        //级联删除
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
    * @Description: 更新分类名称
    * @Param: name
    * @author: LJ
    * @Date: 2020/12/21
    **/
    public void updateCategory(CategoryDto categoryDto){
        //级联更新
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        categoryMapper.updateByPrimaryKeySelective(category);
    }



}
