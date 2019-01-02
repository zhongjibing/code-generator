package com.icezhg.codegenerator;

import com.icezhg.codegenerator.component.GenerateConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Created by zhongjibing on 2018/12/27.
 */
@SpringBootApplication
public class CodeGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeGeneratorApplication.class, args);
    }

    @Bean
    public GenerateConfig generateConfig() {
        GenerateConfig config = new GenerateConfig();
        config.setHost("rm-2zeuvr088920ann15io.mysql.rds.aliyuncs.com");
        config.setSchema("zeus");
        config.setUser("user");
        config.setPassword("kb34M2051");
        config.setBasePackage("com.icezhg.test");
        return config;
    }
}
