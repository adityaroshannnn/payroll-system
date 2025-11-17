package com.payroll.dao;

import com.payroll.model.Attendance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    // Mark attendance
    public void mark(int employeeId, java.sql.Date date, String status) throws Exception {
        String sql = "INSERT INTO attendance(employee_id, att_date, status) VALUES(?,?,?) "
                   + "ON DUPLICATE KEY UPDATE status=VALUES(status)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);
            ps.setDate(2, date);
            ps.setString(3, status);

            ps.executeUpdate();
        }
    }

    // List attendance entries for employee
    public List<Attendance> listForEmployee(int employeeId) throws Exception {
        List<Attendance> list = new ArrayList<>();

        String sql = "SELECT * FROM attendance WHERE employee_id=? ORDER BY att_date DESC";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, employeeId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Attendance a = new Attendance();
                    a.setId(rs.getInt("id"));
                    a.setEmployeeId(rs.getInt("employee_id"));
                    a.setAttDate(rs.getDate("att_date"));
                    a.setStatus(rs.getString("status"));
                    list.add(a);
                }
            }
        }

        return list;
    }
}