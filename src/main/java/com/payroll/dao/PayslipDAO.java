package com.payroll.dao;

import com.payroll.model.Payslip;
import java.sql.*;
import java.util.*;

public class PayslipDAO {
    public void create(Payslip p) throws Exception {
        String sql = "INSERT INTO payslips (employee_id, month, year, basic, hra, deductions, net) VALUES (?,?,?,?,?,?,?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, p.getEmployeeId());
            ps.setInt(2, p.getMonth());
            ps.setInt(3, p.getYear());
            ps.setDouble(4, p.getBasic());
            ps.setDouble(5, p.getHra());
            ps.setDouble(6, p.getDeductions());
            ps.setDouble(7, p.getNet());
            ps.executeUpdate();
        }
    }

    public List<Payslip> listForEmployee(int employeeId) throws Exception {
        List<Payslip> list = new ArrayList<>();
        String sql = "SELECT * FROM payslips WHERE employee_id=? ORDER BY year DESC, month DESC";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
        }
        return list;
    }

    private Payslip map(ResultSet rs) throws Exception {
        Payslip p = new Payslip();
        p.setId(rs.getInt("id"));
        p.setEmployeeId(rs.getInt("employee_id"));
        p.setMonth(rs.getInt("month"));
        p.setYear(rs.getInt("year"));
        p.setBasic(rs.getDouble("basic"));
        p.setHra(rs.getDouble("hra"));
        p.setDeductions(rs.getDouble("deductions"));
        p.setNet(rs.getDouble("net"));
        p.setGeneratedAt(rs.getTimestamp("generated_at"));
        return p;
    }
}
