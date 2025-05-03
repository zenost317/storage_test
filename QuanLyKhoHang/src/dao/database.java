package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.sql.*;

/**
 *
 * @author Acer
 */
public class database {

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/storagedb", "root", "Nino31720045");
            System.out.println("Connected with the database successfully");
            return connection;
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database");
            return null;
        }
    }
}
    
