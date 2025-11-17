package com.payroll.servlet;

import com.payroll.dao.EmployeeDAO;
import com.payroll.model.Employee;
import com.payroll.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/employee")
public class EmployeeDashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User u = (User) req.getSession().getAttribute("user");
            Employee emp = null;
            if (u != null) emp = new EmployeeDAO().findById(findEmployeeIdByUser(u.getId()));
            req.setAttribute("employee", emp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/jsp/employee-dashboard.jsp").forward(req, resp);
    }

    private int findEmployeeIdByUser(int userId) throws Exception {
        // Quick lookup
        return new com.payroll.dao.EmployeeDAO() {{
        }}.findAll().stream()
                .filter(e -> e.getUserId()!=null && e.getUserId()==userId)
                .mapToInt(e -> e.getId())
                .findFirst().orElse(0);
    }
}
