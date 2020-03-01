package com.zk.service;

import com.zk.handler.RpcServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParmentService {

    @Autowired
    private RpcServiceProxy rpcServiceProxy;

    public void subscribeObj() {

        /**
         * 能实现这样，就可以达到RPC远程调用的目标
         * 调用远程的服务，就和调用本地的一样，
         * 所以我们要想 创建userService对象  --> 使用proxy
         * 调用远端 怎么调 --> 远程调用  tcp调过去  使用socket来
         */
       // RpcServiceProxy rpcServiceProxy = new RpcServiceProxy(8080);
        UserService userService = rpcServiceProxy.doCreateProxy(UserService.class);
        //invoke() --running
        String result = userService.getUserInfoByName("lisi");
        System.out.println(result);

    }

}
