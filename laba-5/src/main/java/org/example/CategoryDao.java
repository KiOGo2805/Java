package org.example;

import java.sql.*;

public class CategoryDao {
    private final Connection conn = Database.getConnection();

    // CREATE
    public void create(String name) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO categories (name) VALUES (?)")) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        }
    }

    // READ
    public void printAll() throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM categories")) {
            while (rs.next()) System.out.println("Категорія: " + rs.getString("name"));
        }
    }

    // UPDATE
    public void update(int id, String newName) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE categories SET name = ? WHERE id = ?")) {
            stmt.setString(1, newName);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // DELETE
    public void delete(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM categories WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}