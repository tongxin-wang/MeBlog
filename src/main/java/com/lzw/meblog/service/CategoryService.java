package com.lzw.meblog.service;

import com.lzw.meblog.dto.CategoryDto;
import com.lzw.meblog.dto.CategoryPostsDto;
import com.lzw.meblog.mapper.CategoryMapper;
import com.lzw.meblog.model.Category;
import com.lzw.meblog.model.Tag;
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
    * @Date: 2020/12/11
    **/
    public boolean addCategory(CategoryDto categoryDto){
        //首先检查该分类是否已经存在
        Category category;
        category = categoryMapper.selectIfExist(categoryDto.getName());

        if (category!=null)
        {
            //该分类已经存在，不做插入处理
        }
        else
        {
            //没有该分类信息，新增
            //没有该分类信息，其他表无法使用
            category = new Category();
            category.setName(categoryDto.getName());
            categoryMapper.insertSelective(category);
        }

        return true;
    }

    /**
    * @Description: 删除分类信息
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/11
    **/
    public boolean deleteCategory(int id){
        //级联删除
        categoryMapper.deleteByPrimaryKey(id);
        return true;
    }

    /**
    * @Description: 更新分类名称
    * @Param: name
    * @author: LJ
    * @Date: 2020/12/11
    **/
    public boolean updateCategory(CategoryDto categoryDto){
        //级联更新
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        categoryMapper.updateByPrimaryKeySelective(category);
        return true;
    }



}
