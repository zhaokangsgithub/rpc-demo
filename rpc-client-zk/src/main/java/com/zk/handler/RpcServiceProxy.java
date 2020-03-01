package com.zk.handler;

import com.zk.domian.RpcRequest;
import org.springframework.stereotype.Component;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;


@Component
public class RpcServiceProxy implements InvocationHandler {

    private int port;

    public RpcServiceProxy(int port) {
        this.port = port;
    }

    //构建动态代理类，复写的invoke方法里进行了加强，进行远程调用
    public <T> T doCreateProxy(Class<T> serviceClazz) {

        /**
         * ClassLoader loader,
         *  Class<?>[] interfaces,
         * InvocationHandler h)
         */
        return (T) Proxy.newProxyInstance(serviceClazz.getClassLoader(), new Class<?>[]{serviceClazz}, this);

    }

    //远程调用的最终方法，
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //创建socket
        Socket socket = new Socket("localhost", port);
        //封装 RpcRequest
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setArgs(args);
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());

        //调用
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(rpcRequest);
        outputStream.flush();

        //获取响应
        inputStream = new ObjectInputStream(socket.getInputStream());
        Object response = inputStream.readObject();

        return response;
    }
}
