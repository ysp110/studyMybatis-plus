package com.mybaitisplus.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @user:HaiTianJiangYou
 * @author： zht
 * @date ：2020/2/26 14:00
 * Version ：4.0
 */
@EnableTransactionManagement
@Configuration
@MapperScan("com.mybaitisplus.demo.mapper")
public class MybatisPlusConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
