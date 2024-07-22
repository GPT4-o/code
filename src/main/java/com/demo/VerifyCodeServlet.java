package com.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VerifyCodeServlet")
public class VerifyCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        String inputCode = request.getParameter("code");
        String sessionCode = (String) request.getSession().getAttribute("verificationCode");

        if (inputCode.equals(sessionCode)) {
            response.getWriter().write("验证码验证成功！");
        } else {
            response.getWriter().write("验证码验证失败！");
        }
    }
}