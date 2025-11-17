package com.payroll.servlet;

import com.payroll.dao.EmployeeDAO;
import com.payroll.dao.PayslipDAO;
import com.payroll.model.Employee;
import com.payroll.model.Payslip;
import com.payroll.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/employee/payslips")
public class ListPayslipsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User u = (User) req.getSession().getAttribute("user");
            int empId = new EmployeeDAO().findAll().stream()
                    .filter(e -> e.getUserId()!=null && e.getUserId()==u.getId())
                    .mapToInt(e -> e.getId()).findFirst().orElse(0);
            List<Payslip> payslips = new PayslipDAO().listForEmployee(empId);
            req.setAttribute("payslips", payslips);
        } catch (Exception e) {
            throw new ServletException(e);
        }
        req.getRequestDispatcher("/jsp/payslips.jsp").forward(req, resp);
    }
}
