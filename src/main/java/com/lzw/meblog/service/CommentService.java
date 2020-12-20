package com.lzw.meblog.service;

import com.lzw.meblog.model.Comment;
import com.lzw.meblog.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @program: meblog
 * @author: LJ
 * @create: 2020-12-18 19:35
 **/
@Service
public class CommentService {
    @Autowired
    private CommentMapper   commentMapper;

    public List<Comment> getAllComments(int post_id) {
        return commentMapper.getAllComments(post_id);
    }
}
