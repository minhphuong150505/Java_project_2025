package controller;

import Doi_Tuong.GiaoVien;
import Service.GIaoVienServiceimpl;
import Service.GiaoVienService;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.*;

public class GiaoVienController {
    private JTextField jtfMaGV;
    private JTextField jtfHoTen;
    private JTextField jtfCMND;
    private JTextField jtfSDT;
    private JTextField jtfEmail;
    private JTextField jtfDiaChi;
    private JTextField jtfTrinhDo;
    private JTextField jtfKinhNghiem;
    private JTextField jtfLuongCoBan;
    private JDateChooser jdcNgaySinh;
    private JDateChooser jdcNgayVaoLam;
    private JComboBox<String> cbGioiTinh;
    private JButton jbtLuu;
    private JLabel jlbMsg;

    private GiaoVien giaoVien = null;
    private GiaoVienService giaoVienService = null;

    public GiaoVienController(JTextField jtfMaGV, JTextField jtfHoTen, JTextField jtfCMND,
                              JTextField jtfSDT, JTextField jtfEmail, JTextField jtfDiaChi,
                              JTextField jtfTrinhDo, JTextField jtfKinhNghiem, JTextField jtfLuongCoBan,
                              JDateChooser jdcNgaySinh, JDateChooser jdcNgayVaoLam,
                              JComboBox<String> cbGioiTinh, JButton jbtLuu, JLabel jlbMsg) {
        this.jtfMaGV = jtfMaGV;
        this.jtfHoTen = jtfHoTen;
        this.jtfCMND = jtfCMND;
        this.jtfSDT = jtfSDT;
        this.jtfEmail = jtfEmail;
        this.jtfDiaChi = jtfDiaChi;
        this.jtfTrinhDo = jtfTrinhDo;
        this.jtfKinhNghiem = jtfKinhNghiem;
        this.jtfLuongCoBan = jtfLuongCoBan;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jdcNgayVaoLam = jdcNgayVaoLam;
        this.cbGioiTinh = cbGioiTinh;
        this.jbtLuu = jbtLuu;
        this.jlbMsg = jlbMsg;

        this.giaoVienService = new GIaoVienServiceimpl();
    }

    public void setView(GiaoVien giaoVien) {
        this.giaoVien = giaoVien;
        jtfMaGV.setText(giaoVien.getMaGV());
        jtfHoTen.setText(giaoVien.getHoTen());
        jdcNgaySinh.setDate(giaoVien.getNgaySinh());
        cbGioiTinh.setSelectedItem(giaoVien.getGioiTinh());
        jtfCMND.setText(giaoVien.getCmnd());
        jtfSDT.setText(giaoVien.getSdt());
        jtfEmail.setText(giaoVien.getEmail());
        jtfDiaChi.setText(giaoVien.getDiaChi());
        jtfTrinhDo.setText(giaoVien.getTrinhDo());
        jtfKinhNghiem.setText(giaoVien.getKinhNghiem());
        jdcNgayVaoLam.setDate(giaoVien.getNgayVaoLam());
        jtfLuongCoBan.setText(String.valueOf(giaoVien.getLuongCoBan()));

        setEvent();
    }

    private void setEvent() {
        jbtLuu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!checkNotNull()) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        giaoVien.setMaGV(jtfMaGV.getText().trim());
                        giaoVien.setHoTen(jtfHoTen.getText().trim());
                        giaoVien.setNgaySinh(jdcNgaySinh.getDate());
                        giaoVien.setGioiTinh(cbGioiTinh.getSelectedItem().toString().equals("Male") ? "M" : "F");
                        giaoVien.setCmnd(jtfCMND.getText().trim());
                        giaoVien.setSdt(jtfSDT.getText().trim());
                        giaoVien.setEmail(jtfEmail.getText().trim());
                        giaoVien.setDiaChi(jtfDiaChi.getText().trim());
                        giaoVien.setTrinhDo(jtfTrinhDo.getText().trim());
                        giaoVien.setKinhNghiem(jtfKinhNghiem.getText().trim());
                        giaoVien.setNgayVaoLam(jdcNgayVaoLam.getDate());
                        giaoVien.setLuongCoBan(Double.parseDouble(jtfLuongCoBan.getText().trim()));

                        if (showDialog(giaoVien)) {
                            int result = giaoVienService.createOrUpdate(giaoVien);
                            if (result != 0) {
                                jlbMsg.setText("Cập nhật dữ liệu thành công!");
                            } else {
                                jlbMsg.setText("Có lỗi xảy ra, vui lòng thử lại!");
                            }
                        }
                    }
                } catch (Exception ex) {
                    jlbMsg.setText("Lỗi: " + ex.getMessage());
                }
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
        return jtfHoTen.getText() != null && !jtfHoTen.getText().trim().isEmpty();
    }

    public static boolean showDialog(GiaoVien giaoVien) {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn muốn cập nhật giáo viên mã: " + giaoVien.getMaGV() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}