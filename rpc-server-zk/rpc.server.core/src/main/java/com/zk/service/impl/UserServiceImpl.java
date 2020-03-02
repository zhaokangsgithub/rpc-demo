package com.zk.service.impl;

import com.zk.annotation.RpcService;
import com.zk.service.UserService;
import org.springframework.stereotype.Component;


@Component
@RpcService(UserService.class)
public class UserServiceImpl implements UserService {
    @Override
    public String getUserInfoByName(String userName) {
        System.out.println("method running request =" + userName);
        return userName + " - server - return ";
    }
}
