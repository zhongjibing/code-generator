package com.icezhg.codegenerator.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2019/01/02.
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    private static DefaultListableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
        ApplicationContextUtil.beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    public static ApplicationContext getApplicationContext() {
        return ApplicationContextUtil.applicationContext;
    }

    private static DefaultListableBeanFactory getBeanFactory() {
        return ApplicationContextUtil.beanFactory;
    }

    public static Object getBean(String beanName) {
        return ApplicationContextUtil.applicationContext.getBean(beanName);
    }

    public static <B> B getBean(String beanName, Class<B> clazz) {
        return ApplicationContextUtil.applicationContext.getBean(beanName, clazz);
    }

    public static <B> B getBean(Class<B> clazz) {
        return ApplicationContextUtil.applicationContext.getBean(clazz);
    }

    public static <B> B regist(String beanName, B bean) {
        if (ApplicationContextUtil.beanFactory.containsBeanDefinition(beanName)) {
            ApplicationContextUtil.beanFactory.removeBeanDefinition(beanName);
        } else {
            if (bean != null) {
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(bean.getClass());
                ApplicationContextUtil.beanFactory.registerBeanDefinition(beanName, builder.getBeanDefinition());
            }
        }
        if (bean != null) {
            ApplicationContextUtil.beanFactory.registerSingleton(beanName, bean);
        }
        return bean;
    }

    public static void test() {
        //        AutowireCapableBeanFactory autowireCapableBeanFactory = applicationContext.getAutowireCapableBeanFactory();
        //        autowireCapableBeanFactory
        //        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //        final String JDBC_TEMPLATE_BEAN = "jdbcTemplate";
        //
        //
        //
        //            //创建一个新的JdbcTemplate
        //            DataSource dataSource = DataSourceBuilder.create()
        //                    .driverClassName("com.mysql.jdbc.Driver")
        //                    .url("jdbc:mysql://rm-2zeuvr088920ann15io.mysql.rds.aliyuncs.com:3306?useUnicode=true&characterEncoding=utf-8&useSSL=false")
        //                    .username("root")
        //                    .password("kb34M2051")
        //                    .build();
        //            JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //
        //            //判断
        //            if (context.containsBeanDefinition(JDBC_TEMPLATE_BEAN)){
        //                //如果Spring上下文已存在Bean，先删除
        //                context.removeBeanDefinition(JDBC_TEMPLATE_BEAN);
        //            }else{
        //                //如果不存在，创建
        //                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(JdbcTemplate.class);
        //                context.registerBeanDefinition(JDBC_TEMPLATE_BEAN,builder.getBeanDefinition());
        //            }
        //
        //            //注入JdbcTemplate实例
        //        context.getBeanFactory().registerSingleton(JDBC_TEMPLATE_BEAN,jdbcTemplate);
        //
        //
        //            return jdbcTemplate;

    }

}
