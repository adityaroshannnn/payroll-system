package com.payroll.servlet;

import com.payroll.dao.PasswordUtil;
import com.payroll.dao.UserDAO;
import com.payroll.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class AuthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            UserDAO dao = new UserDAO();
            User u = dao.findByUsername(username);
            if (u != null && u.getPasswordHash().equals(PasswordUtil.sha256(password))) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", u);
                if ("ADMIN".equals(u.getRole())) {
                    resp.sendRedirect(req.getContextPath() + "/admin");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/employee");
                }
                return;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.setAttribute("error", "Invalid username or password");
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }
}
