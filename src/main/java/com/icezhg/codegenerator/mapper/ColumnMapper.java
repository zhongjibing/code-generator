package com.icezhg.codegenerator.mapper;

import java.util.List;

import com.icezhg.codegenerator.domain.Column;
import org.apache.ibatis.annotations.Param;


/**
 * Created by zhongjibing on 2018/12/27.
 */
public interface ColumnMapper {

    List<Column> selectAll();

    List<Column> select(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);
}