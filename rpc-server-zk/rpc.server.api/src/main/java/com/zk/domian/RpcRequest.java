package com.zk.domian;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable {

    private String className;

    private Object[] args;

    private String methodName;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "className='" + className + '\'' +
                ", args=" + Arrays.toString(args) +
                ", methodName='" + methodName + '\'' +
                '}';
    }
}
