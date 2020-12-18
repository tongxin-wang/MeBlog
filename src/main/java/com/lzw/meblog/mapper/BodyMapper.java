package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Body;
import org.springframework.stereotype.Repository;

/**
 * BodyMapper继承基类
 */
@Repository
public interface BodyMapper extends MyBatisBaseDao<Body, Integer> {
}