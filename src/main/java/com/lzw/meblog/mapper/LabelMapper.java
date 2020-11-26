package com.lzw.meblog.mapper;

import com.lzw.meblog.model.Label;
import org.springframework.stereotype.Repository;

/**
 * LabelMapper继承基类
 */
@Repository
public interface LabelMapper extends MyBatisBaseDao<Label, Integer> {
}