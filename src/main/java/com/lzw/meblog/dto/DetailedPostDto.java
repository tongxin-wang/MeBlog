package com.lzw.meblog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章详细信息类
 * @author TongxinWang
 */
public class DetailedPostDto {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章摘要，简要介绍
     */
    private String summary;

    /**
     * 文章题图
     */
    private String imgUrl;

    /**
     * 文章创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtCreate;

    /**
     * 文章修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime gmtModified;

    /**
     * 文章所属类别（可能有多个）
     */
    private List<CategoryDto> categories;

    /**
     * 文章拥有标签
     */
    private List<TagDto> tags;

    /**
     * 文章正文部分（数据库存储的为md格式文本）
     */
    private BodyDto body;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryDto> categories) {
        this.categories = categories;
    }

    public List<TagDto> getTags() {
        return tags;
    }

    public void setTags(List<TagDto> tags) {
        this.tags = tags;
    }

    public BodyDto getBody() {
        return body;
    }

    public void setBody(BodyDto body) {
        this.body = body;
    }
}
