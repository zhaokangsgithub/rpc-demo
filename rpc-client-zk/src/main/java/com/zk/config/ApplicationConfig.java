package com.zk.config;

import com.zk.handler.RpcServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.zk")
public class ApplicationConfig {

    @Bean
    public RpcServiceProxy rpcServiceProxy(){
        return new RpcServiceProxy(8081);
    }
}
