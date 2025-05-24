package BeAn;

import DoiTuong.User;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nhuyc
 */
public class DanhMucBean {
    private String kind;
    private JPanel jpn;
    private JLabel jlb;
    private User user;

    public DanhMucBean(String kind, JPanel jpn, JLabel jlb, User user) {
        this.kind = kind;
        this.jpn = jpn;
        this.jlb = jlb;
        this.user = user;
    }

    public DanhMucBean() {
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public JPanel getJpn() {
        return jpn;
    }

    public void setJpn(JPanel jpn) {
        this.jpn = jpn;
    }

    public JLabel getJlb() {
        return jlb;
    }

    public void setJlb(JLabel jlb) {
        this.jlb = jlb;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    
    
    
}
