package com.payroll.dao;

import com.payroll.model.User;
import java.sql.*;

public class UserDAO {
    public User findByUsername(String username) throws Exception {
        String sql = "SELECT * FROM users WHERE username=?";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("username"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setRole(rs.getString("role"));
                    return u;
                }
            }
        }
        return null;
    }

    public void createEmployeeUser(int employeeId, String username, String rawPassword) throws Exception {
        String sql = "INSERT INTO users (username, password_hash, role) VALUES (?,?, 'EMPLOYEE')";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, username);
            ps.setString(2, PasswordUtil.sha256(rawPassword));
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int userId = keys.getInt(1);
                    try (PreparedStatement ps2 = con.prepareStatement("UPDATE employees SET user_id=? WHERE id=?")) {
                        ps2.setInt(1, userId);
                        ps2.setInt(2, employeeId);
                        ps2.executeUpdate();
                    }
                }
            }
        }
    }
}
