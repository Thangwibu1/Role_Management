package com.example.DAO;

import com.example.connection.DBConnection;
import com.example.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    // Phương thức lấy tất cả role của một user
    public List<Role> getRolesByUserId(int userId) {
        String sql = "SELECT r.RoleID, r.RoleName FROM Roles r " +
                "JOIN User_Roles ur ON r.RoleID = ur.RoleID " +
                "WHERE ur.UserID = ?";
        // ... thực thi query và trả về List<Role> ...
        List<Role> roles = new ArrayList<Role>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            // Execute query and check if user exists
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int roleId = resultSet.getInt("RoleID");
                String roleName = resultSet.getString("RoleName");
                roles.add(new Role(roleId, roleName));
            }
        } catch (Exception e) {

        }
        return roles; // Placeholder for actual implementation
    }

    // Phương thức gán role cho user
    public void addUserRole(int userId, int roleId) {
        // ... Viết câu lệnh INSERT INTO User_Roles ...
        String sql = "INSERT INTO User_Roles (UserID, RoleID) VALUES (?, ?)";

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            ps.executeUpdate();
            System.out.println("Add role to user successfully");
        } catch (Exception e) {
            System.out.println("Fail to connnect database");
            e.printStackTrace();
        }
    }

    // Phương thức xóa role của user
    public void removeUserRole(int userId, int roleId) {
        // ... Viết câu lệnh DELETE FROM User_Roles ...
        String sql = "DELETE FROM User_Roles WHERE UserID = ? AND RoleID = ?";
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setInt(2, roleId);
            ps.executeUpdate();
            System.out.println("Add role to user successfully");
        } catch (Exception e) {
            System.out.println("Fail to connnect database");
            e.printStackTrace();
        }
    }

    // ... các phương thức khác ...
}
