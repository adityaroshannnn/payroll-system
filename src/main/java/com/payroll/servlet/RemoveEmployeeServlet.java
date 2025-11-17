package com.payroll.servlet;

import com.payroll.dao.EmployeeDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/remove-employee")
public class RemoveEmployeeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        try {
            int id = Integer.parseInt(idStr);
            new EmployeeDAO().remove(id);
            resp.sendRedirect(req.getContextPath()+"/admin");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
