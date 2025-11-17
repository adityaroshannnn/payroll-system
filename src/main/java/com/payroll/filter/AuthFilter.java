package com.payroll.filter;

import com.payroll.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/admin", "/employee/*", "/employee"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        User u = (session==null) ? null : (User) session.getAttribute("user");
        String path = request.getRequestURI();
        if (u == null) {
            response.sendRedirect(request.getContextPath()+"/login");
            return;
        }
        if (path.startsWith(request.getContextPath()+"/admin") && !"ADMIN".equals(u.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        if (path.startsWith(request.getContextPath()+"/employee") && !"EMPLOYEE".equals(u.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(req, res);
    }
}
