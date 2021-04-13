package com.dude.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入到系统");

        String path = req.getServletPath();

        if("/student/save.do".equals(path)){

            save(req,resp);
                
        }else if ("/student/update.do".equals(path)){

            update(req,resp);

        }else if ("/student/delete.do".equals(path)){

            delete(req,resp);

        }else if ("/student/select.do".equals(path)){

            select(req,resp);
        }
    }

    private void save(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("执行添加操作");

    }
    private void update(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("执行修改操作");

    }
    private void delete(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("执行删除操作");

    }
    private void select(HttpServletRequest req, HttpServletResponse resp) {

        System.out.println("执行查询操作");

    }
}
