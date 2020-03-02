package com.zk;


import com.zk.config.ApplicationConfig;
import com.zk.server.handler.RpcServerHandler;
import com.zk.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        context.start();
        /*RpcServerHandler rpcServerHandler = new RpcServerHandler();
        //服务注册发布
        rpcServerHandler.publishServer(new UserServiceImpl(), 8080);*/
    }
}
