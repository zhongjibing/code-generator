package com.icezhg.codegenerator.component;

import java.io.IOException;
import java.util.List;

import com.icezhg.codegenerator.domain.Column;
import com.icezhg.codegenerator.domain.Table;

/**
 * Created by zhongjibing on 2019/01/01.
 */
public interface Generator {

    GenerateConfig getConfig();

    String getSchemas();

    List<Table> getTables();

    List<Column> getTableColumns(Table table);

    void generate(String schemaName) throws IOException;

    default void ge() {
    }


}
