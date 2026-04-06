package org.example;

import java.sql.*;

public class ComponentDao {
    private final Connection conn = Database.getConnection();

    public void create(String name, double price, int categoryId) throws SQLException {
        String sql = "INSERT INTO components (name, price, category_id) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, categoryId);
            stmt.executeUpdate();
        }
    }

    public void searchByName(String keyword) throws SQLException {
        String sql = "SELECT * FROM components WHERE name LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("Результати пошуку:");
            while (rs.next()) {
                System.out.println("- " + rs.getString("name") + " ($" +
                        rs.getDouble("price") + ")");
            }
        }
    }

    public void updatePrice(int id, double newPrice) throws SQLException {
        String sql = "UPDATE components SET price = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newPrice);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM components WHERE id = ?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public void printMetadata() throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM components LIMIT 1");
             ResultSet rs = stmt.executeQuery()) {
            ResultSetMetaData meta = rs.getMetaData();
            System.out.println("\nМетадані таблиці components:");
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.println(meta.getColumnName(i) + " (" + meta.getColumnTypeName(i) + ")");
            }
        }
    }
}