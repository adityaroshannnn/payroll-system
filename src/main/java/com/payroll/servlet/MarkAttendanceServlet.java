package com.payroll.servlet;

import com.payroll.dao.AttendanceDAO;
import com.payroll.dao.EmployeeDAO;
import com.payroll.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet("/employee/mark-attendance")
public class MarkAttendanceServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User u = (User) req.getSession().getAttribute("user");
            int empId = new EmployeeDAO().findAll().stream()
                    .filter(e -> e.getUserId()!=null && e.getUserId()==u.getId())
                    .mapToInt(e -> e.getId()).findFirst().orElse(0);
            String status = req.getParameter("status");
            new AttendanceDAO().mark(empId, Date.valueOf(LocalDate.now()), status);
            resp.sendRedirect(req.getContextPath()+"/employee");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
