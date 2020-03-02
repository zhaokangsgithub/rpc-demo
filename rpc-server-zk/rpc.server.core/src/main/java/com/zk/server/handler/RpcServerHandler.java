package com.zk.server.handler;


import com.zk.annotation.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.annotation.Annotation;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class RpcServerHandler implements InitializingBean, ApplicationContextAware {

    private final ExecutorService pools = new ThreadPoolExecutor(5, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

    private int port;

    private Map serveiceObjs = new HashMap();

    public RpcServerHandler(int port) {
        this.port = port;
    }

    /*public void publishServer(Object targetService, int port) {

        ServerSocket serverSocket = null;
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                pools.execute(new RpcWorker(targetService,socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                pools.execute(new RpcWorker(serveiceObjs, socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext");
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
        for (Object beanObj : beansWithAnnotation.values()) {
            RpcService rpcService = beanObj.getClass().getAnnotation(RpcService.class);
            // key: beanName  value beanInstance
            serveiceObjs.put(rpcService.value().getName(), beanObj);
        }

    }
}