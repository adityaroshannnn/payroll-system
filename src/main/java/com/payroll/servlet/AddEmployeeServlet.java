package com.payroll.servlet;

import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.UserDAO;
import com.payroll.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/admin/add-employee")
public class AddEmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/add-employee.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String salaryStr = req.getParameter("salary");
        String joinDateStr = req.getParameter("join_date");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            Employee e = new Employee();
            e.setName(name);
            e.setEmail(email);
            e.setSalary(Double.parseDouble(salaryStr));
            e.setJoinDate(Date.valueOf(joinDateStr));
            int empId = new EmployeeDAO().add(e);
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                new UserDAO().createEmployeeUser(empId, username, password);
            }
            resp.sendRedirect(req.getContextPath()+"/admin");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }
}
