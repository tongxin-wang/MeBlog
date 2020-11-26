package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Comment;
import org.springframework.stereotype.Repository;

/**
 * CommentMapper继承基类
 */
@Repository
public interface CommentMapper extends MyBatisBaseDao<Comment, Integer> {
}