package org.example;

import java.security.MessageDigest;
import java.sql.*;

public class UserDao {
    private final Connection conn = Database.getConnection();

    // Хешування
    private String hash(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    // CREATE (Реєстрація)
    public void register(String username, String password) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, hash(password));
            stmt.executeUpdate();
        }
    }

    // READ (Логін)
    public User login(String username, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ? AND password_hash = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, hash(password));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password_hash"));
            return null;
        }
    }

    // UPDATE
    public void updatePassword(int id, String newPassword) throws SQLException {
        String sql = "UPDATE users SET password_hash = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hash(newPassword));
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}