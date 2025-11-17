package com.payroll.servlet;

import com.payroll.dao.PayslipDAO;
import com.payroll.model.Payslip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/create-payslip")
public class CreatePayslipServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/create-payslip.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int employeeId = Integer.parseInt(req.getParameter("employee_id"));
            int month = Integer.parseInt(req.getParameter("month"));
            int year = Integer.parseInt(req.getParameter("year"));
            double basic = Double.parseDouble(req.getParameter("basic"));
            double hra = Double.parseDouble(req.getParameter("hra"));
            double deductions = Double.parseDouble(req.getParameter("deductions"));
            double net = basic + hra - deductions;
            Payslip p = new Payslip();
            p.setEmployeeId(employeeId);
            p.setMonth(month);
            p.setYear(year);
            p.setBasic(basic);
            p.setHra(hra);
            p.setDeductions(deductions);
            p.setNet(net);
            new PayslipDAO().create(p);
            resp.sendRedirect(req.getContextPath()+"/admin");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
