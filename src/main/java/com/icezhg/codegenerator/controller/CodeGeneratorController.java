package com.icezhg.codegenerator.controller;

import com.icezhg.codegenerator.service.ColumnService;
import com.icezhg.codegenerator.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2018/12/27.
 */
@RestController
@RequestMapping("/generate")
public class CodeGeneratorController {

    @Autowired
    private TableService tableService;
    @Autowired
    private ColumnService columnService;

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public Object listTables() {
        return tableService.selectAll();
    }

    @RequestMapping(value = "/listTable", method = RequestMethod.GET)
    public Object listTable(String tableSchema, String tableType, String tableName) {
        return tableService.select(tableSchema, tableType, tableName);
    }

    @RequestMapping(value = "/tableColumns", method = RequestMethod.GET)
    public Object tableColumns(String tableSchema, String tableName) {
        return columnService.select(tableSchema, tableName);
    }
}
