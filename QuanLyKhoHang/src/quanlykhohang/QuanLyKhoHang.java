/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package quanlykhohang;

import controller.LoginController;
import view.Login;

/**
 *
 * @author Acer
 */
public class QuanLyKhoHang {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        java.awt.EventQueue.invokeLater(() -> {
            Login loginView = Login.getInstance();
            new LoginController(loginView); // Khởi tạo LoginController
            loginView.setVisible(true);
        });
    }

}
