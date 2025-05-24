package controller;

import Doi_Tuong.NhanVien;
import Service.NhanVienService;
import Service.NhanVienServiceimpl;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class NhanVienController {
    private JTextField jtfMaNV;
    private JTextField jtfHoTen;
    private JTextField jtfCMND;
    private JTextField jtfSDT;
    private JTextField jtfEmail;
    private JTextField jtfDiaChi;
    private JTextField jtfKinhNghiem;
    private JTextField jtfViTri;
    private JTextField jtfLuongCoBan;
    private JDateChooser jdcNgaySinh;
    private JDateChooser jdcNgayVaoLam;
    private JComboBox<String> cbGioiTinh;
    private JButton jbtLuu;
    private JLabel jlbMsg;

    private NhanVien nhanVien = null;
    private NhanVienService nhanVienService = null;

    public NhanVienController(JTextField jtfMaNV, JTextField jtfHoTen, JTextField jtfCMND,
                              JTextField jtfSDT, JTextField jtfEmail, JTextField jtfDiaChi,
                              JTextField jtfKinhNghiem, JTextField jtfViTri, JTextField jtfLuongCoBan,
                              JDateChooser jdcNgaySinh, JDateChooser jdcNgayVaoLam,
                              JComboBox<String> cbGioiTinh, JButton jbtLuu, JLabel jlbMsg) {
        this.jtfMaNV = jtfMaNV;
        this.jtfHoTen = jtfHoTen;
        this.jtfCMND = jtfCMND;
        this.jtfSDT = jtfSDT;
        this.jtfEmail = jtfEmail;
        this.jtfDiaChi = jtfDiaChi;
        this.jtfKinhNghiem = jtfKinhNghiem;
        this.jtfViTri = jtfViTri;
        this.jtfLuongCoBan = jtfLuongCoBan;
        this.jdcNgaySinh = jdcNgaySinh;
        this.jdcNgayVaoLam = jdcNgayVaoLam;
        this.cbGioiTinh = cbGioiTinh;
        this.jbtLuu = jbtLuu;
        this.jlbMsg = jlbMsg;

        this.nhanVienService = new NhanVienServiceimpl();
    }

    public void setView(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        jtfMaNV.setText(nhanVien.getMaNV());
        jtfHoTen.setText(nhanVien.getHoTen());
        jdcNgaySinh.setDate(nhanVien.getNgaySinh());
        cbGioiTinh.setSelectedItem(nhanVien.getGioiTinh());
        jtfCMND.setText(nhanVien.getCMND());
        jtfSDT.setText(nhanVien.getSDT());
        jtfEmail.setText(nhanVien.getEmail());
        jtfDiaChi.setText(nhanVien.getDiaChi());
        jtfKinhNghiem.setText(nhanVien.getKinhNghiem());
        jtfViTri.setText(nhanVien.getViTri());
        jdcNgayVaoLam.setDate(nhanVien.getNgayVaoLam());
        jtfLuongCoBan.setText(String.valueOf(nhanVien.getLuongCoBan()));

        setEvent();
    }

    private void setEvent() {
        jbtLuu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    if (!checkNotNull()) {
                        jlbMsg.setText("Vui lòng nhập đầy đủ thông tin bắt buộc!");
                    } else {
                        nhanVien.setMaNV(jtfMaNV.getText().trim());
                        nhanVien.setHoTen(jtfHoTen.getText().trim());
                        nhanVien.setNgaySinh(jdcNgaySinh.getDate());
                        nhanVien.setGioiTinh(cbGioiTinh.getSelectedItem().toString().equals("Male") ? "M" : "F");
                        nhanVien.setCMND(jtfCMND.getText().trim());
                        nhanVien.setSDT(jtfSDT.getText().trim());
                        nhanVien.setEmail(jtfEmail.getText().trim());
                        nhanVien.setDiaChi(jtfDiaChi.getText().trim());
                        nhanVien.setKinhNghiem(jtfKinhNghiem.getText().trim());
                        nhanVien.setViTri(jtfViTri.getText().trim());
                        nhanVien.setNgayVaoLam(jdcNgayVaoLam.getDate());
                        nhanVien.setLuongCoBan(Double.parseDouble(jtfLuongCoBan.getText().trim()));

                        if (showDialog(nhanVien)) {
                            int result = nhanVienService.createOrUpdate(nhanVien);
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

    public static boolean showDialog(NhanVien nhanVien) {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Bạn có chắc muốn cập nhật nhân viên mã: " + nhanVien.getMaNV() + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        return dialogResult == JOptionPane.YES_OPTION;
    }
}