package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Tag;
import org.springframework.stereotype.Repository;

/**
 * TagMapper继承基类
 */
@Repository
public interface TagMapper extends MyBatisBaseDao<Tag, Integer> {
}