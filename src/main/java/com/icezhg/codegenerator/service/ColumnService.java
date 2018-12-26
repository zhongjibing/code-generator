package com.icezhg.codegenerator.service;

import java.util.List;

import com.icezhg.codegenerator.domain.Column;
import com.icezhg.codegenerator.mapper.ColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjibing on 2018/12/27.
 */
@Service
public class ColumnService {

    @Autowired
    private ColumnMapper tableMapper;

    public List<Column> select(String tableSchema, String tableName) {
        return tableMapper.select(tableSchema, tableName);
    }
}
