package com.icezhg.codegenerator.controller;

import java.io.IOException;

import com.icezhg.codegenerator.component.GenerateConfig;
import com.icezhg.codegenerator.component.MySqlGenerator;
import com.icezhg.codegenerator.domain.Table;
import com.icezhg.codegenerator.service.ColumnService;
import com.icezhg.codegenerator.service.CusService;
import com.icezhg.codegenerator.service.TableService;
import com.icezhg.codegenerator.utils.ApplicationContextUtil;
import com.sun.javaws.security.AppContextUtil;
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
    @Autowired
    private GenerateConfig config;
    @Autowired
    private CusService cusService;

    @RequestMapping(value = "/tables", method = RequestMethod.GET)
    public Object listTables() {
        return tableService.findAll();
    }

    @RequestMapping(value = "/listTable", method = RequestMethod.GET)
    public Object listTable(String tableSchema, String tableType, String tableName) {
        return tableService.find(tableSchema, tableType, tableName);
    }

    @RequestMapping(value = "/tableColumns", method = RequestMethod.GET)
    public Object tableColumns(String tableSchema, String tableName) {
        return columnService.find(tableSchema, tableName);
    }

    @RequestMapping(value = "/build", method = RequestMethod.GET)
    public Object gen() throws IOException {
        MySqlGenerator generator = new MySqlGenerator();
        generator.setConfig(config);
        generator.setTableService(tableService);
        generator.setColumnService(columnService);
        generator.generate();
        return null;
    }

    @RequestMapping("/test")
    public Object test() throws Exception {
        try {
            Table bean = ApplicationContextUtil.getBean(Table.class);
            System.out.println(bean);
            Table bean2 = ApplicationContextUtil.getBean(Table.class);
            System.out.println(bean2);
        } catch (Exception e){
            e.printStackTrace();
        }
        //        cusService.test();
        return System.currentTimeMillis();
    }

}
