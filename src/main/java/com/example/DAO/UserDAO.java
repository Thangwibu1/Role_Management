package com.example.DAO;

import com.example.connection.DBConnection;
import com.example.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public User checkLogin(String username, String password) {
        String sql = "select * from Users where Username = ? and PasswordHash = ?";

        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            // Execute query and check if user exists
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // User found, create and return User object
                return new User(rs.getInt("UserID"), rs.getString("Username"), rs.getString("Email"));
            }
        } catch (Exception e) {

        }

        return null; // User not found or an error occurred
    }

    public List<User> getAllUser() {
        String sql = "select * from Users";
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DBConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            // Execute query and check if user exists
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int UserID = rs.getInt("UserID");
                String Username = rs.getString("Username");
                String Email = rs.getString("Email");

                User user = new User(UserID, Username, Email);
                users.add(user);
            }
        } catch (Exception e) {

        }
        return users; // User not found or an error occurred
    }
}
