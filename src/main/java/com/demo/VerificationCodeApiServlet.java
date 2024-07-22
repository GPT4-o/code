package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/sendVerificationCode")
public class VerificationCodeApiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String email = request.getParameter("email");
        String code = generateVerificationCode();
        String htmlContent = getString(code);

        try {
            EmailUtil.sendEmail(email, "登录码", htmlContent);
            // Assuming a simple JSON response indicating success
            response.getWriter().write("{\"status\":\"success\",\"message\":\"验证码已发送至邮箱\"}");
        } catch (Exception e) {
            e.printStackTrace();
            // Assuming a simple JSON response indicating failure
            response.getWriter().write("{\"status\":\"error\",\"message\":\"发送邮件失败\"}");
        }
    }

    private String generateVerificationCode() {
        // 生成6位验证码
        return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
    }

    private static String getString(String code) {
        String logoPath = "https://ming999.tech/m.jpg";
        String htmlContent = "<html>\n" +
                "<body style=\"font-family: Arial, sans-serif;\">\n" +
                "<img src=\"" + logoPath + "\" alt=\"您的公司徽标\" style=\"width: 150px; height: auto;\">\n" +
                "<h2 style=\"color: #007BFF;\">验证码是：" + code + "</h2>\n" +
                "<p>欢迎使用我们的服务。我们是[公司名称]，专注于[公司业务]。我们的使命是[公司使命]。我们的价值观是[公司价值观]。我们致力于为您提供最好的服务，帮助您解决问题。如果您有任何疑问或需要协助，请随时与我们联系。</p>\n" +
                "</body>\n" +
                "</html>";
        return htmlContent;
    }
}