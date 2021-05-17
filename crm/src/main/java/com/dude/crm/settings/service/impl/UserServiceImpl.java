package com.dude.crm.settings.service.impl;

import com.dude.crm.exception.loginException;
import com.dude.crm.settings.dao.UserDao;
import com.dude.crm.settings.domain.User;
import com.dude.crm.settings.service.UserService;
import com.dude.crm.utils.DateTimeUtil;
import com.dude.crm.utils.SqlSessionUtil;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws loginException {

        Map<String,String> map = new HashMap<String, String>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User user = userDao.login(map);

        if (user == null){
            System.out.println("账号密码错误");
            throw new loginException("账号密码错误");
        }
        //如果程序能够成功执行到改行，说明账号密码正确
        //需要继续向下验证其他三项信息

        //验证失效时间
        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime)<0){
            System.out.println("账号已失效");
            throw new loginException("账号已失效");
        }

        //判断锁定状态
        String  lockState = user.getLockState();
        if ("0".equals(lockState)){
            System.out.println("账号已锁定");
            throw new loginException("账号已锁定");
        }


        //判断IP地址
        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)){
            System.out.println("ip地址受限");
            throw new loginException("ip地址受限");
        }


        return user;
    }

    @Override
    public List<User> getUserList() {

        List<User> uList = userDao.getUserList();

        return uList;
    }
}
