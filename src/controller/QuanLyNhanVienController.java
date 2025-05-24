package controller;

import Doi_Tuong.NhanVien;
import Service.NhanVienService;
import Service.NhanVienServiceimpl;
import View.NhanVienJFrame;
import static controller.NhanVienController.showDialog;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;

import utilt.ClassTableModel;

public class QuanLyNhanVienController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnXoa;
    private JTextField jtfSearch;
    private String kindSelected = "";
    private NhanVienService nhanVienService = null;
    private String[] listColumn = {
        "Mã nhân viên",
        "STT",
        "Họ tên",
        "Ngày sinh",
        "Giới tính(M: Nam, F: Nữ)",
        "CMND",
        "Số điện thoại",
        "Email",
        "Địa chỉ",
        "Kinh Nghiệm",
        "Vị Trí",
        "Ngày Vào Làm",
        "Lương Cơ Bản"
    };

    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyNhanVienController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnXoa,String kindSelected) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnXoa = btnXoa;
        this.kindSelected = kindSelected;
        nhanVienService = new NhanVienServiceimpl();
    }

    public void setDataToTable() {
        List<NhanVien> listItem = nhanVienService.getList();

        DefaultTableModel model = new ClassTableModel().setTableNhanVien(listItem, listColumn);
        JTable table = new JTable(model);
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {}

            private void filter() {
                String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    NhanVien nhanVien = new NhanVien();
                    nhanVien.setMaNV(model.getValueAt(selectedRowIndex, 0).toString());
                    nhanVien.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
                    String dateStr = model.getValueAt(selectedRowIndex, 3).toString();

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date date = sdf.parse(dateStr);
                        nhanVien.setNgaySinh(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    nhanVien.setGioiTinh(model.getValueAt(selectedRowIndex, 4).toString());
                    nhanVien.setCMND(model.getValueAt(selectedRowIndex, 5).toString());
                    nhanVien.setSDT(model.getValueAt(selectedRowIndex, 6).toString());
                    nhanVien.setEmail(model.getValueAt(selectedRowIndex, 7).toString());
                    nhanVien.setDiaChi(model.getValueAt(selectedRowIndex, 8).toString());
                    nhanVien.setKinhNghiem(model.getValueAt(selectedRowIndex, 9).toString());
                    nhanVien.setViTri(model.getValueAt(selectedRowIndex, 10).toString());
                    dateStr = model.getValueAt(selectedRowIndex, 11).toString();
                    try {
                        Date date = sdf.parse(dateStr);
                        nhanVien.setNgayVaoLam(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }

                    //nhanVien.setLuongCoBan(Double.parseDouble(model.getValueAt(selectedRowIndex, 12).toString()));
                    String luongStr = model.getValueAt(selectedRowIndex, 12).toString();
                    BigDecimal bd = new BigDecimal(luongStr);
                    nhanVien.setLuongCoBan(bd.doubleValue());
                    NhanVienJFrame frame = new NhanVienJFrame(nhanVien);
                    frame.setTitle("Thông tin nhân viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                }
            }
        });

        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                NhanVien nhanVien = new NhanVien();
                NhanVienJFrame frame = new NhanVienJFrame(nhanVien);
                frame.setTitle("Thông tin nhân viên");
                frame.setResizable(false);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAdd.setBackground(new Color(0, 200, 83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAdd.setBackground(new Color(100, 221, 23));
            }
        });

        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent ev) {
                try {
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();

                    if (selectedRowIndex != -1) {
                        NhanVien nhanVien = new NhanVien();
                        nhanVien.setMaNV(model.getValueAt(selectedRowIndex, 0).toString());

                        if (showDialog(nhanVien)) {
                            nhanVienService.delete(nhanVien);
                            model.removeRow(selectedRowIndex);
                        }
                    } else {
                        System.out.println("Chưa chọn dòng nào để xóa.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Table design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();

        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }
}