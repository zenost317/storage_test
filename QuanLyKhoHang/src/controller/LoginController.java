/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.database;
import model.login;
import view.Login;
import view.Home;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Acer
 */
public class LoginController {

    private Login view;
    private database db;

    public LoginController(Login view) {
        this.view = view;
        this.db = new database();

        view.setLoginListener(this::handleLogin);
    }

    private void handleLogin(ActionEvent e) {
        String email = view.getEmail();
        String password = view.getPassword();
        String query = "SELECT * FROM user WHERE email = ? AND password = ? AND status = 'Active'";

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Hãy nhật đủ email với password");
            return;
        }

        int tmp = 0;
        try {
            Connection connection = database.getConnection();
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, email); // Gán giá trị cho email
            st.setString(2, password); // Gán giá trị cho password
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                view.hideView();
                new Home(rs.getString("role")).setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(null, "Sai email hoặc mật khẩu");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        }
    }

}
