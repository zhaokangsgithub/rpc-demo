package com.zk.config;

import com.zk.server.handler.RpcServerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.zk")
public class ApplicationConfig {

    @Bean
    public RpcServerHandler rpcServerHandler() {
        return new RpcServerHandler(8081);
    }
}
