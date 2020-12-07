package com.lzw.meblog.service;

import com.lzw.meblog.mapper.PostMapper;
import com.lzw.meblog.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    public Post getPostById(int id){
        return postMapper.selectByPrimaryKey(id);
    }

    public List<Post> getAllPosts(){
        return postMapper.getAllPosts();
    }
}
