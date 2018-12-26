package com.icezhg.codegenerator.mapper;

import java.util.List;

import com.icezhg.codegenerator.domain.Table;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhongjibing on 2018/12/27.
 */
public interface TableMapper {

    List<Table> selectAll();

    List<Table> select(@Param("tableSchema") String tableSchema, @Param("tableType") String tableType, @Param("tableName") String tableName);
}