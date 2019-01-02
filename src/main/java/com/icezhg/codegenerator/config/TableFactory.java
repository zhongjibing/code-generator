package com.icezhg.codegenerator.config;

import com.icezhg.codegenerator.domain.Table;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhongjibing on 2019/01/03.
 */
@Configuration
public class TableFactory implements FactoryBean<Table> {


    @Override
    public Table getObject() throws Exception {
        return new Table();
    }

    @Override
    public Class<?> getObjectType() {
        return Table.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
