package com.example.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*") // 1. Áp dụng cho TẤT CẢ các request
public class AuthenticationFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 2. Ép kiểu để sử dụng các phương thức của HTTP
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 3. Lấy session hiện tại (không tạo mới nếu chưa có)
        HttpSession session = httpRequest.getSession(false);

        // 4. Xác định các "khu vực công cộng"
        String loginURI = httpRequest.getContextPath() + "/login";
        // Có thể thêm các URL khác như /css, /js...

        // 5. Kiểm tra "vé" (đã đăng nhập chưa?)
        boolean loggedIn = session != null && session.getAttribute("user") != null;

        // 6. Kiểm tra xem có phải đang cố vào "quầy vé" không?
        boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);

        // 7. Ra quyết định
        if (loggedIn || loginRequest) {
            // Nếu đã có vé HOẶC đang đi đến quầy vé -> Cho đi tiếp
            chain.doFilter(request, response);
        } else {
            // Không có vé VÀ không đi đến quầy vé -> Chặn lại và dẫn ra quầy vé
            httpResponse.sendRedirect(loginURI);
        }
    }
}
