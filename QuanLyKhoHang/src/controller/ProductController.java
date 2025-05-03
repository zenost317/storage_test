/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.product;
import java.sql.SQLException;
import java.util.List;
import dao.productData;
/**
 *
 * @author Acer
 */
public class ProductController {
    private productData productData = new productData();

    public List<product> getAllProducts() throws SQLException {
        return productData.getAllProducts();
    }

    public void addProduct(product p) throws SQLException {
        productData.addProduct(p);
    }

    public void updateProduct(product p) throws SQLException {
        productData.updateProduct(p);
    }

    public void deleteProduct(int id) throws SQLException {
        productData.deleteProduct(id);
    }
}
