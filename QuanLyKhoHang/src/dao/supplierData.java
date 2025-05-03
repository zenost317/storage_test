/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.supplier;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Acer
 */
public class supplierData {
    public List<supplier> getAllSuppliers() throws SQLException {
        List<supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM supplier";
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                supplier s = new supplier(
                    rs.getInt("supplier_id"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    rs.getString("email"),
                    rs.getString("address")
                );
                suppliers.add(s);
            }
        }
        return suppliers;
    }

    public void addSupplier(supplier s) throws SQLException {
        String query = "INSERT INTO supplier (name, contact, email, address) VALUES (?, ?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, s.getName());
            pstmt.setString(2, s.getPhone());
            pstmt.setString(3, s.getEmail());
            pstmt.setString(4, s.getAddress());
            pstmt.executeUpdate();
        }
    }
}
