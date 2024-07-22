package com.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/example")
public class ExampleApiServlet extends HttpServlet {
    private static final String AUTH_CODE = "1234";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authCode = request.getHeader("Authorization");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        if (authCode == null || !authCode.equals(AUTH_CODE)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\":\"访问失败\",\"message\":\"需要授权码\"}");
            return;
        }


        // 从请求中提取参数
        String parameter = request.getParameter("parameter");

        // 执行业务逻辑，这里仅作为示例
        String result = "处理结果";

        // 构建JSON响应
        String jsonResponse = "{\"status\":\"访问成功\",\"result\":\"" + result + "\"}";

        // 发送响应
        response.getWriter().write(jsonResponse);
    }
}