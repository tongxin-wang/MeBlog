package com.lzw.meblog.mapper;

import com.lzw.meblog.dto.BodyDto;
import com.lzw.meblog.model.Body;
import org.springframework.stereotype.Repository;

/**
 * BodyMapper继承基类
 */
@Repository
public interface BodyMapper extends MyBatisBaseDao<Body, Integer> {

    public int updateByPostIdSelective(Body body);

    public Body selectByPostId(int post_id);
}