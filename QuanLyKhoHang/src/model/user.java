/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Acer
 */
public class user {

    private int id;
    private String name;
    private String number;
    private String email;
    private String password;
    private String address;
    private String status;

    public user(int id, String name, String number, String email, String password, String address, String status) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.password = password;
        this.address = address;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public String getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }
}
