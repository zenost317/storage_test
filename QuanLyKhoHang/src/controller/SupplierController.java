/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.supplier;
import java.sql.SQLException;
import java.util.List;
import dao.supplierData;

/**
 *
 * @author Acer
 */
public class SupplierController {

    private supplierData supplierData = new supplierData();

    public List<supplier> getAllSuppliers() throws SQLException {
        return supplierData.getAllSuppliers();
    }

    public void addSupplier(supplier s) throws SQLException {
        supplierData.addSupplier(s);
    }

    public void updateSupplier(supplier s) throws SQLException {
        supplierData.updateSupplier(s);
    }

    public void deleteSupplier(int id) throws SQLException {
        supplierData.deleteSupplier(id);
    }
}
