package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CommentMapper继承基类
 */
@Repository
public interface CommentMapper extends MyBatisBaseDao<Comment, Integer> {

    public List<Comment> getAllComments(int post_id);
}