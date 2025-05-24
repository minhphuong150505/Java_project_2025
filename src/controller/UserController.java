/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import DoiTuong.User;
import javax.swing.JTextField;

/**
 *
 * @author nhuyc
 */
public class UserController {
    private JTextField jtfUsername;
    private JTextField jtfName;
    private JTextField jtfEmail;
    private JTextField jtfSDT;

    User user = null;
    public UserController(JTextField jtfUsername, JTextField jtfName, JTextField jtfEmail, JTextField jtfSDT) {
        this.jtfUsername = jtfUsername;
        this.jtfName = jtfName;
        this.jtfEmail = jtfEmail;
        this.jtfSDT = jtfSDT;
    }
    
    public void setView(User user)
    {
        jtfUsername.setText(user.getUsername());
        jtfName.setText(user.getName());
        jtfEmail.setText(user.getEmail());
        jtfSDT.setText(user.getSdt());
    }
    
    
}
