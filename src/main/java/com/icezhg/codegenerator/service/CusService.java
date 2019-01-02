package com.icezhg.codegenerator.service;

import java.io.IOException;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.icezhg.codegenerator.mapper.ColumnMapper;
import com.icezhg.codegenerator.mapper.TableMapper;
import com.icezhg.codegenerator.utils.ApplicationContextUtil;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

/**
 * Created by zhongjibing on 2019/01/01.
 */
@Service
public class CusService {


    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://rm-2zeuvr088920ann15io.mysql.rds.aliyuncs.com:3306/information_schema?useUnicode=true&characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("kb34M2051");
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(2);
        dataSource.setMinIdle(1);
        dataSource.setMaxWait(6000L);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setValidationQuery("select 1");
        return dataSource;
    }

    public SqlSessionFactory getSqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        try {
            factoryBean.setMapperLocations(resolver.getResources("classpath:**/mapper/*.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return factoryBean.getObject();
    }

    public MapperScannerConfigurer getMapperScannerConfigurer(String sqlSessionFactoryBeanName) {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.icezhg.codegenerator.mapper");
        configurer.setSqlSessionFactoryBeanName(sqlSessionFactoryBeanName);
        return configurer;
    }

    public <T> MapperFactoryBean<T> mapperFactoryBean(Class<T> mapperClass, SqlSessionFactory sqlSessionFactory)  {
        MapperFactoryBean<T> bean = new MapperFactoryBean<>();
        bean.setMapperInterface(mapperClass);
        bean.setSqlSessionFactory(sqlSessionFactory);
        return  bean;
    }

    public SqlSessionTemplate getSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    public void test() throws Exception {
        DataSource dataSource = ApplicationContextUtil.regist("dataSource", getDataSource());
        SqlSessionFactory sqlSessionFactory = ApplicationContextUtil.regist("sqlSessionFactory", getSqlSessionFactory(dataSource));
        ApplicationContextUtil.regist("mapperScannerConfigurer", getMapperScannerConfigurer("sqlSessionFactory"));
        ApplicationContextUtil.regist("sqlSessionTemplate", getSqlSessionTemplate(sqlSessionFactory));
        MapperRegistry mapperRegistry = ApplicationContextUtil.regist("mapperRegistry", new MapperRegistry(sqlSessionFactory.getConfiguration()));
        MapperFactoryBean<TableMapper> tableMapper = ApplicationContextUtil.regist("tableMapper", mapperFactoryBean(TableMapper.class, sqlSessionFactory));
        MapperFactoryBean<ColumnMapper> columnMapper = ApplicationContextUtil.regist("columnMapper", mapperFactoryBean(ColumnMapper.class, sqlSessionFactory));
        mapperRegistry.addMapper(TableMapper.class);
        mapperRegistry.addMapper(ColumnMapper.class);
        try {
            TableMapper bean = ApplicationContextUtil.getBean(TableMapper.class);
            System.out.println(bean != null);
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            SqlSessionTemplate bean = ApplicationContextUtil.getBean(SqlSessionTemplate.class);
            System.out.println(bean != null);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
