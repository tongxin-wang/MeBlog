package com.lzw.meblog.controller;

import com.lzw.meblog.dto.*;
import com.lzw.meblog.model.Post;
import com.lzw.meblog.model.User;
import com.lzw.meblog.service.CategoryService;
import com.lzw.meblog.service.PostService;
import com.lzw.meblog.service.TagService;
import com.lzw.meblog.service.CommentService;
import io.swagger.annotations.*;
import io.swagger.models.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: meblog
 * @author: LJ
 * @create: 2020-12-18 19:24
 **/
@RestController
@RequestMapping("/admin")
@Api(description = "提供给博客网站拥有者的接口")
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
    private static String username = "root";
    private static String password = "root";

    /**
    * @Description: 后台登陆操作
    * @Param:
    * @author: LJ
    * @Date: 2020/12/18
    **/
    @PostMapping("/login")
    public String login(User user, HttpServletRequest request, HttpServletResponse  response) throws IOException {
        // 验证用户账号是否正确
        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/admin/index.html");
        } else {
            response.sendRedirect(request.getContextPath() + "/toLogin");
        }
        return null;
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
            @ApiImplicitParam(name = "gmtCreate", value = "创建时间", required = true, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "gmtModified", value = "创建时间", required = true, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "categories", value = "文章分类", required = true, dataType = "List<CategoryDto>"),
            @ApiImplicitParam(name = "tags", value = "文章标签", required = true, dataType = "List<TagDto>"),
            @ApiImplicitParam(name = "body", value = "文章md源码", required = true, dataType = "BodyDto")
    })
    @PostMapping("/addPost")        //@RequestBody 利用一个对象去获取前端传过来的数据
    public String addPost(@RequestBody DetailedPostDto detailedPostDto){
        postService.addPost(detailedPostDto);
        return "添加文章成功";
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


}
