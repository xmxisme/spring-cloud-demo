package com.xmxisme.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.xmxisme.uaa.mapper")
public class UaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(UaaApplication.class, args);
    }

}
