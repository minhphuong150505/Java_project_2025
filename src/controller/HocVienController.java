/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Doi_Tuong.HocVien;
import Service.HocVienService;
import Service.HocVienServiceimpl;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author nhuyc
 */
public class HocVienController {

    private JTextField jtfMHV;
    private JTextField jtfName;
    private JTextField jtfEmail;
    private JTextField jtfNgheNghiep;
    private JTextField jtfDiaChi;
    private JTextField jtfTrinhDo;
    private JTextField jtfMucTieu;
    private JTextField jtfSDT;
    private JDateChooser jDateChooser1;
    private JButton jbtLuu;
    private JComboBox<String> cbGioiTinh;
    
    private HocVien hocVien = null;
    private HocVienService hocVienService = null;

    private JLabel jlbMsg;
    public HocVienController(JTextField jtfMHV, JTextField jtfName, JTextField jtfEmail, JTextField jtfNgheNghiep, JTextField jtfDiaChi, JTextField jtfTrinhDo, JTextField jtfMucTieu, JTextField jtfSDT, JDateChooser jDateChooser1, JButton jbtLuu, JComboBox<String> cbGioiTinh,JLabel jlbMsg) {
        this.jtfMHV = jtfMHV;
        this.jtfName = jtfName;
        this.jtfEmail = jtfEmail;
        this.jtfNgheNghiep = jtfNgheNghiep;
        this.jtfDiaChi = jtfDiaChi;
        this.jtfTrinhDo = jtfTrinhDo;
        this.jtfMucTieu = jtfMucTieu;
        this.jtfSDT = jtfSDT;
        this.jDateChooser1 = jDateChooser1;
        this.jbtLuu = jbtLuu;
        this.cbGioiTinh = cbGioiTinh;
        
        this.hocVienService = new HocVienServiceimpl();
        this.jlbMsg = jlbMsg;
    }
    
     public void setView(HocVien hocVien) {

        jtfMHV.setText(hocVien.getMaHV());
        jtfName.setText(hocVien.getHoTen());

        // Nếu bạn đang dùng JDateChooser từ thư viện JCalendar:
        jDateChooser1.setDate(hocVien.getNgaySinh());

        cbGioiTinh.setSelectedItem(hocVien.getGioiTinh());
        jtfSDT.setText(hocVien.getSDT());
        jtfEmail.setText(hocVien.getEmail());
        jtfDiaChi.setText(hocVien.getDiaChi());
        jtfTrinhDo.setText(hocVien.getTrinhDo());
        jtfNgheNghiep.setText(hocVien.getNgheNghiep());
        jtfMucTieu.setText(hocVien.getMucTieu());
        this.hocVien = hocVien;
        setEvent();
    }

    private void setEvent() {
        jbtLuu.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!checkNotNull()) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        hocVien.setMaHV(jtfMHV.getText().trim());
                        hocVien.setHoTen(jtfName.getText().trim());
                        hocVien.setNgaySinh(jDateChooser1.getDate()); // kiểu Date
                        String gioiTinh;
                        if(cbGioiTinh.getSelectedItem().toString() == "Male")
                        {
                            gioiTinh = "M";
                        }
                        else
                        {
                            gioiTinh = "F";
                        }
                        hocVien.setGioiTinh(gioiTinh);
                        hocVien.setSDT(jtfSDT.getText().trim());
                        hocVien.setEmail(jtfEmail.getText().trim());
                        hocVien.setDiaChi(jtfDiaChi.getText().trim());
                        hocVien.setTrinhDo(jtfTrinhDo.getText().trim());
                        hocVien.setNgheNghiep(jtfNgheNghiep.getText().trim());
                        hocVien.setMucTieu(jtfMucTieu.getText().trim());

                        if (showDialog(hocVien)) {
                            int lastId = hocVienService.createOrUpdate(hocVien);
                            if (lastId != 0) {
                                jtfMHV.setText( hocVien.getMaHV());
                                jlbMsg.setText("Xử lý cập nhật dữ liệu thành công!");
                            } else {
                                jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                        }
                    }
                } catch (Exception ex) {
                    jlbMsg.setText(ex.toString());
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                jbtLuu.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                jbtLuu.setBackground(new Color(100, 221, 23));
            }
        });
   }

     private boolean checkNotNull() {
        return jtfName.getText() != null && !jtfName.getText().equalsIgnoreCase("");
    }

    public static boolean showDialog(HocVien hocVien) {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Học viên bạn muốn cập nhật là "+hocVien.getMaHV()+",Bạn muốn cập nhật dữ liệu hay không?", "Thông báo", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
     
}
