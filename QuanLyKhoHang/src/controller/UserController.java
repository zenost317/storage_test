/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import dao.userData;
import model.user;
import dao.database;
import view.User;

/**
 *
 * @author Acer
 */
public class UserController {

    private User view;

    public UserController(User view) {
        this.view = view;
        updateTableData();

        view.addTableListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int row = e.getFirstRow();
                if (row >= 0) {
                    saveData(row);
                }
            }
        });

        view.addButtonListener(
                (ActionEvent e) -> view.addEmptyRow(),
                (ActionEvent e) -> {
                    int row = view.getSelectedRow();
                    if (row >= 0) {
                        deleteData(row);             
                    } else {
                        JOptionPane.showMessageDialog(view, "Vui lòng chọn dòng để xóa");
                    }
                }
        );
    }

    private void updateTableData() {
        List<user> users = userData.getUsers();
        Object[][] data = new Object[users.size()][6];
        for (int i = 0; i < users.size(); i++) {
            user user = users.get(i);
            data[i] = new Object[]{
                user.getId(),
                user.getName(),
                user.getNumber(),
                user.getEmail(),
                user.getPassword(),
                user.getAddress(),
                user.getStatus()
            };
        }
        view.updateTable(data);
    }

    private void saveData(int row) {
        Object[] rowData = view.getRowData(row);
        Integer id = (Integer) rowData[0];
        user user = new user(
                id != null ? id : 0,
                (String) rowData[1],
                (String) rowData[2],
                (String) rowData[3],
                (String) rowData[4],
                (String) rowData[5],
                (String) rowData[6]
        );

        if (id == null) {
            int newId = userData.addUser(user);
            if (newId != -1) {
                user.setId(newId);
                view.setRowData(row, new Object[]{
                    user.getId(),
                    user.getName(),
                    user.getNumber(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getAddress(),
                    user.getStatus()
                });
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi khi thêm người dùng!");
            }
        } else {
            userData.updateUser(user);
        }
    }

    private void deleteData(int row) {
        Object[] rowData = view.getRowData(row);
        Integer id = (Integer) rowData[0];
        if (id != null) {
            userData.deleteUser(id);
            view.removeRow(row);
        }
    }
}
