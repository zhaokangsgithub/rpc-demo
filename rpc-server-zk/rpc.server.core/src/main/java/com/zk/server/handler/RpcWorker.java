package com.zk.server.handler;

import com.zk.domian.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Map;

public class RpcWorker implements Runnable {

    private Socket socket;

    private Map serveiceObjs;

    public RpcWorker(Map serveiceObjs, Socket socket) {
        this.socket = socket;
        this.serveiceObjs = serveiceObjs;
    }

    @Override
    public void run() {
        System.out.println("current thread is " + Thread.currentThread());
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        Object response = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest request = (RpcRequest) inputStream.readObject();

            response = doInvoke(serveiceObjs, request);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(response);
            outputStream.flush();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object doInvoke(Object targetService, RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //RPC远程调用到这里
        System.out.println("RPC METHOD RUNNNING request =" + request);

        String className = request.getClassName();
        String methodName = request.getMethodName();
        Object[] args = request.getArgs();
        Class[] classes = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classes[i] = args[i].getClass();
        }

        Class<?> aClass = Class.forName(className);
        Method method = aClass.getMethod(methodName, classes);
        Object result = method.invoke(serveiceObjs.get(className), args);
        return result;
    }
}