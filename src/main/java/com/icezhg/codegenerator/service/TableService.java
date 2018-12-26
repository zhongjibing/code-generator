package com.icezhg.codegenerator.service;

import java.util.List;

import com.icezhg.codegenerator.domain.Table;
import com.icezhg.codegenerator.mapper.TableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjibing on 2018/12/27.
 */
@Service
public class TableService {

    @Autowired
    private TableMapper tableMapper;

    public List<Table> selectAll() {
        return tableMapper.selectAll();
    }

    public List<Table> select(String tableSchema, String tableType, String tableName) {
        return tableMapper.select(tableSchema, tableType, tableName);
    }
}
