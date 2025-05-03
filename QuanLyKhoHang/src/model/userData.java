/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Acer
 */
public class userData {

    public static List<user> getUsers() {
        List<user> users = new ArrayList<>();
        try {
            Connection con = database.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                users.add(new user(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("number"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("address"),
                        rs.getString("status")
                ));
            }
            rs.close();
            st.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static int addUser(user user) {
        try {
            Connection con = database.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO user (name, number, email, password, address, status) VALUES (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getNumber());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getStatus());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int id = rs.next() ? rs.getInt(1) : -1;
            rs.close();
            ps.close();
            con.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static void updateUser(user user) {
        try {
            Connection con = database.getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE user SET name=?, number=?, email=?, password=?, address=?, status=? WHERE id=?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getNumber());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getStatus());
            ps.setInt(7, user.getId());
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id) {
        try {
            Connection con = database.getConnection();
            PreparedStatement ps = con.prepareStatement("DELETE FROM user WHERE id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
