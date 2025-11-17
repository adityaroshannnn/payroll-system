package com.payroll.servlet;

import com.payroll.dao.AttendanceDAO;
import com.payroll.dao.EmployeeDAO;
import com.payroll.model.Attendance;
import com.payroll.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/attendance")
public class AdminAttendanceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String empIdStr = req.getParameter("empId");

            // Load employee list for dropdown
            List<Employee> empList = new EmployeeDAO().findAll();
            req.setAttribute("empList", empList);

            if (empIdStr != null) {
                int empId = Integer.parseInt(empIdStr);

                List<Attendance> list = new AttendanceDAO().listForEmployee(empId);
                req.setAttribute("attendanceList", list);

                Employee emp = new EmployeeDAO().findById(empId);
                req.setAttribute("selectedEmployee", emp);
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

        req.getRequestDispatcher("/jsp/attendance-list.jsp").forward(req, resp);
    }
}