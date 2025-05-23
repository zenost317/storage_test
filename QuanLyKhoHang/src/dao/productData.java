/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class productData {

    public List<product> getAllProducts() throws SQLException {
        List<product> products = new ArrayList<>();
        String query = "SELECT product_id, name, price, quantity, supplier_id FROM product";
        try (Connection conn = database.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                product p = new product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("supplier_id")
                );
                products.add(p);
            }
        }
        return products;
    }

    public void addProduct(product p) throws SQLException {
        String query = "INSERT INTO product (name, supplier_id, price, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, p.getName());
            pstmt.setInt(2, p.getSupplierId());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setDouble(4, p.getQuantity());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int productId = rs.getInt(1);
                String inventoryQuery = "INSERT INTO inventory (product_id) VALUES (?)";
                try (PreparedStatement inventoryPstmt = conn.prepareStatement(inventoryQuery)) {
                    inventoryPstmt.setInt(1, productId);
                    inventoryPstmt.executeUpdate();
                }
            }
        }
    }

    public void updateProduct(product p) throws SQLException {
        String query = "UPDATE product SET name = ?, supplier_id = ?, price = ?, quantity = ? WHERE product_id = ?";
        try (Connection conn = database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, p.getName());
            pstmt.setInt(2, p.getSupplierId());
            pstmt.setDouble(3, p.getPrice());
            pstmt.setInt(4, p.getQuantity());
            pstmt.setInt(5, p.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM product WHERE product_id = ?";
        try (Connection conn = database.getConnection(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
