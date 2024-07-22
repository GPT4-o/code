package com.demo;

import com.demo.EmailUtil;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SendCodeServlet")
public class SendCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain; charset=UTF-8");

        String email = request.getParameter("email");
        String code = generateVerificationCode();
        EmailUtil.sendEmail(email, "登录码", "您的验证码是: " + code + "，请在5分钟内输入。\n如非本人操作，请忽略此邮件。");
        request.getSession().setAttribute("verificationCode", code);
        response.getWriter().write("验证码已发送至邮箱，请查收！");
    }

    private String generateVerificationCode() {
        // 生成6位验证码
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }
}