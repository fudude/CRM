package com.dude.crm.settings.web.controller;

import com.dude.crm.settings.domain.User;
import com.dude.crm.settings.service.UserService;
import com.dude.crm.settings.service.impl.UserServiceImpl;
import com.dude.crm.utils.MD5Util;
import com.dude.crm.utils.PrintJson;
import com.dude.crm.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("进入到用户控制器");

        String path = request.getServletPath();

        if("/settings/user/login.do".equals(path)){

            login(request,response);

        }else if ("/settings/user/xxx.do".equals(path)){

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到验证登录操作");
        String loginAct = request.getParameter("loginAct");
        String loginPwd = request.getParameter("loginPwd");
        //将密码的铭文形式转换为MD5的密文形式
        loginPwd = MD5Util.getMD5(loginPwd);
        //接受ip地址
        String ip = request.getRemoteAddr();
        System.out.println("----------ip"+ip);
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{

            User user = us.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);
            //如果程序执行到此处，说明业务层没有为controller抛出任何异常
            //表示登录成功

            System.out.println("---------执行了try");
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e){
            System.out.println("---------执行了catch");
            e.printStackTrace();

            //一旦程序执行了catch块的信息，说明业务层为我们验证登录失败，为controller抛出了异常
            //表示登录失败
            String msg = e.getMessage();

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success", false);
            map.put("msg", msg);
            PrintJson.printJsonObj(response,map);
        }

    }


}
