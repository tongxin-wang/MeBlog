package com.lzw.meblog.controller;

import com.lzw.meblog.dto.*;
import com.lzw.meblog.model.*;
import com.lzw.meblog.service.*;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @program: meblog
 * @author: LJ TongxinWang
 * @create: 2020-12-18 19:24
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
    * @Date: 2020/12/18
    **/
    @ApiOperation(value = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form")
    })
    @PostMapping("/login")
    public LoginResult login(User user, HttpSession session){
        LoginResult loginResult = new LoginResult();
        // 验证管理员
        if(user.getUsername().equals(username) && user.getPassword().equals(password)){
            session.setAttribute("user", user);
            loginResult.setCode(200);
            loginResult.setMsg("Authentication successful!");
        }
        else{
            loginResult.setCode(400);
            loginResult.setMsg("Authentication failed!");
        }

        return loginResult;
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
    * @Date: 2020/12/18
    **/
    @ApiOperation(value = "增加一篇文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文章主键", required = false, dataType = "Integer"),
            @ApiImplicitParam(name = "title", value = "文章标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "summary", value = "文章简介", required = true, dataType = "String"),
            @ApiImplicitParam(name = "imgUrl", value = "文章题图url", required = true, dataType = "String"),
            @ApiImplicitParam(name = "gmtCreate", value = "创建时间", required = false, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "gmtModified", value = "创建时间", required = false, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "categories", value = "文章分类", required = true, dataType = "List<CategoryDto>"),
            @ApiImplicitParam(name = "tags", value = "文章标签", required = true, dataType = "List<TagDto>"),
            @ApiImplicitParam(name = "body", value = "文章md源码", required = true, dataType = "BodyDto")
    })
    @PostMapping("/addPost")        //@RequestBody 利用一个对象去获取前端传过来的数据
    public String addPost(@RequestBody DetailedPostDto detailedPostDto){
        postService.addPost(detailedPostDto);
        return "添加文章成功"+ detailedPostDto.toString();
    }
    
    /**
    * @Description: 删除一篇文章
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/20
    **/
    @ApiOperation(value = "删除一篇文章")
    @ApiImplicitParam(name = "id", value = "文章ID", required = true, dataType = "Integer")
    @DeleteMapping("/deletePost/{id}")
    public String deletePost(@PathVariable int id){
        postService.DeletePost(id);
        return "删除文章成功";
    }

    /**
    * @Description: 更新一篇文章
    * @Param: detailedPostDto
    * @author: LJ
    * @Date: 2020/12/20
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
    @PutMapping("/updatePost")
    public String updatPost(@RequestBody DetailedPostDto detailedPostDto){
        postService.updatePost(detailedPostDto);
        return "更新文章成功";
    }

    /**
    * @Description: 更新一篇文章的分类
    * @Param: post_id, category_id
    * @author: LJ
    * @Date: 2020/12/21
    **/
    @ApiOperation(value = "更新一篇文章的分类")
    @PutMapping("/updatePostCategory/{post_id}/{category_id}")
    public String updatePostCategory(@PathVariable int post_id, @PathVariable int category_id){
        postService.updatePostCategory(post_id, category_id);
        return "修改文章分类成功";
    }

    /**
    * @Description: 添加一条分类
    * @Param: categoryDto
    * @author: LJ
    * @Date: 2020/12/21
    **/
    @ApiOperation(value = "添加一条分类")
    @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    @PostMapping("/addCategory")
    public String addCategory(@RequestBody CategoryDto categoryDto){
        String message = categoryService.addCategory(categoryDto);
        return message;
    }

    /**
    * @Description: 删除一条分类信息
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/21
    **/
    @ApiOperation(value = "删除一条分类")
    @ApiImplicitParam(name = "id", value = "分类ID", required = true, dataType = "Integer")
    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id){
        categoryService.deleteCategory(id);
        return "删除分类成功";
    }

    /**
    * @Description: 更新一条分类信息
    * @Param:
    * @author: LJ
    * @Date: 2020/12/21
    **/
    @ApiOperation(value = "更新一条分类信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "分类id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "分类名称", required = true, dataType = "String")
    })
    @PutMapping("/updateCategory")
    public String updatCategory(@RequestBody CategoryDto categoryDto){
        categoryService.updateCategory(categoryDto);
        return "更新分类成功";
    }

    /**
    * @Description: 新增一条标签
    * @Param: tagDto
    * @author: LJ
    * @Date: 2020/12/21
    **/
    @ApiOperation(value = "新增一条标签")
    @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
    @PostMapping("/addTag")
    public String addCategory(@RequestBody TagDto tagDto){
        String message = tagService.addTag(tagDto);
        return message;
    }

    /**
    * @Description: 删除一条标签
    * @Param: id
    * @author: LJ
    * @Date: 2020/12/21
    **/
    @ApiOperation(value = "删除一条标签")
    @ApiImplicitParam(name = "id", value = "标签ID", required = true, dataType = "Integer")
    @DeleteMapping("/deleteTag/{id}")
    public String deleteTag(@PathVariable int id){
        tagService.deleteTag(id);
        return "删除标签成功";
    }

    /**
    * @Description: 更新一条标签
    * @Param: tagDto
    * @author: LJ
    * @Date: 2020/12/21
    **/
    @ApiOperation(value = "更新一条标签信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "标签id", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "name", value = "标签名称", required = true, dataType = "String")
    })
    @PutMapping("/updateTag")
    public String updatCategory(@RequestBody TagDto tagDto){
        tagService.updateTag(tagDto);
        return "更新标签成功";
    }

}
