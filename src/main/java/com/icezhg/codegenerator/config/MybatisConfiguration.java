package com.icezhg.codegenerator.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhongjibing on 2018/12/27.
 */
@Configuration
@MapperScan("com.icezhg.codegenerator.mapper")
public class MybatisConfiguration {
}
