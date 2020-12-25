package com.lzw.meblog.controller;

import com.lzw.meblog.dto.*;
import com.lzw.meblog.model.*;
import com.lzw.meblog.model.Tag;
import com.lzw.meblog.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: meblog
 * @author: LJ TongxinWang
 * @create: 2020-12-08 19:24
 **/
@RestController
@CrossOrigin
@RequestMapping("/admin")
@Api(description = "提供给博客网站管理员的接口")
public class BackController {
    @Autowired
    PostService postService;
    @Autowired
    TagService tagService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;

    /* 后台登录账号密码 */
    private static String username = "admin";
    private static String password = "justfortest";

    /**
    * @Description: 后台登陆操作
    * @Param:
    * @author: LJ
    * @Date: 2020/12/08
    **/
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping("/login")
    public Result login(User user, HttpSession session){
        Result result = new Result();
        // 验证管理员
        if(user.getUsername().equals(username) && user.getPassword().equals(password)){
            session.setAttribute("user", user);
            result.setCode(200);
            result.setMsg("Authentication successful!");
        }
        else{
            result.setCode(400);
            result.setMsg("Authentication failed!");
        }

        return result;
    }

    @ApiOperation(value = "退出当前登录")
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "Logout successful!";
    }

    /**
    * @Description: 增加一篇文章
    * @Param:
    * @author: LJ
    * @Date: 2020/12/08
    **/
    @ApiOperation(value = "增加一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章主键", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "imgUrl", value = "文章题图url", required = false, dataType = "String"),
            @ApiImplicitParam(name = "gmtCreate", value = "创建时间", required = false, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "gmtModified", value = "创建时间", required = false, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "categories", value = "文章分类", required = true, dataType = "List<CategoryDto>"),
            @ApiImplicitParam(name = "tags", value = "文章标签", required = true, dataType = "List<TagDto>"),
            @ApiImplicitParam(name = "body", value = "文章md源码", required = true, dataType = "BodyDto")
    })
    @PostMapping("/post")        //@RequestBody 利用一个对象去获取前端传过来的数据
    public Result addPost(@RequestBody DetailedPostDto detailedPostDto){
        boolean fig = postService.addPost(detailedPostDto);
        Result result = new Result();
        if (fig==true)
        {
            result.setCode(200);
            result.setMsg("添加文章成功!");
        }
        else
        {
            result.setCode(400);
            result.setMsg("添加文章失败!");
        }
        return result;
    }
    
    /**
    * @Description: 删除一篇文章
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/10
    **/
    @ApiOperation(value = "删除一篇文章")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Integer")
    @DeleteMapping("/post/{id}")
    public Result deletePost(@PathVariable int id){
        boolean fig = postService.DeletePost(id);
        Result result = new Result();
        if (fig==true)
        {
            result.setCode(200);
            result.setMsg("删除文章成功!");
        }
        else
        {
            result.setCode(400);
            result.setMsg("删除文章失败!");
        }
        return result;
    }

    /**
    * @Description: 更新一篇文章
    * @Param: detailedPostDto
    * @author: LJ
    * @Date: 2020/12/10
    **/
    @ApiOperation(value = "更新一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章主键", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "imgUrl", value = "文章题图url", required = true, dataType = "String"),
            @ApiImplicitParam(name = "gmtCreate", value = "创建时间", required = true, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "gmtModified", value = "创建时间", required = true, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "categories", value = "文章分类", required = true, dataType = "List<CategoryDto>"),
            @ApiImplicitParam(name = "tags", value = "文章标签", required = true, dataType = "List<TagDto>"),
            @ApiImplicitParam(name = "body", value = "文章md源码", required = true, dataType = "BodyDto")
    })
    @PutMapping("/post")
    public Result updatePost(@RequestBody DetailedPostDto detailedPostDto){
         boolean fig = postService.updatePost(detailedPostDto);
        Result result = new Result();
        if (fig==true)
        {
            result.setCode(200);
            result.setMsg("更新文章成功!");
        }
        else
        {
            result.setCode(400);
            result.setMsg("更新文章失败!");
        }
        return result;
    }


    /**
    * @Description: 添加一条分类
    * @Param: categoryDto
    * @author: LJ
    * @Date: 2020/12/11
    **/
    @ApiOperation(value = "添加一条分类")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    @PostMapping("/category")
    public String addCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.addCategory(categoryDto);
        return category.toString();
    }

    /**
    * @Description: 删除一条分类信息
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/11
    **/
    @ApiOperation(value = "删除一条分类")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Integer")
    @DeleteMapping("/category/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "删除分类成功";
    }

    /**
    * @Description: 更新一条分类信息
    * @Param:
    * @author: LJ
    * @Date: 2020/12/11
    **/
    @ApiOperation(value = "更新一条分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    })
    @PutMapping("/category")
    public String updatCategory(@RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(categoryDto);
        return "更新分类成功";
    }

    /**
    * @Description: 新增一条标签
    * @Param: tagDto
    * @author: LJ
    * @Date: 2020/12/11
    **/
    @ApiOperation(value = "新增一条标签")
    @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
    @PostMapping("/tag")
    public String addCategory(@RequestBody TagDto tagDto){
        Tag tag = tagService.addTag(tagDto);
        return tag.toString();
    }

    /**
    * @Description: 删除一条标签
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/11
    **/
    @ApiOperation(value = "删除一条标签")
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "Integer")
    @DeleteMapping("/tag/{id}")
    public String deleteTag(@PathVariable int id){
        tagService.deleteTag(id);
        return "删除标签成功";
    }

    /**
    * @Description: 更新一条标签
    * @Param: tagDto
    * @author: LJ
    * @Date: 2020/12/11
    **/
    @ApiOperation(value = "更新一条标签信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
    })
    @PutMapping("/tag")
    public String updateCategory(@RequestBody TagDto tagDto){
        tagService.updateTag(tagDto);
        return "更新标签成功";
    }

}
