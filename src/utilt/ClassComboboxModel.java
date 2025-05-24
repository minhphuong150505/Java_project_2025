/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilt;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author nhuyc
 */
public class ClassComboboxModel {
   public ComboBoxModel<String> setComboBoxUser(String username) {
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
       if(username != null){
        model.addElement(username);
        model.addElement("Thông tin");
        model.addElement("Đăng xuất");  
       }
       else
       {
           model.addElement("Owner");
       }
       return model;
    }

    public ClassComboboxModel() {
    }
    
    public ComboBoxModel<String> setComboBoxNhanVien() {
       DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Giáo viên");
        model.addElement("Nhân viên");  
       return model;
    }
}
