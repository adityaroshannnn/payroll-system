package com.payroll.servlet;

import com.payroll.dao.EmployeeDAO;
import com.payroll.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Employee> employees = new EmployeeDAO().findAll();
            req.setAttribute("employees", employees);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/jsp/admin-dashboard.jsp").forward(req, resp);
    }
}
