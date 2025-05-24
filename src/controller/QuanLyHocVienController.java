/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import Doi_Tuong.HocVien;
import Service.HocVienService;
import Service.HocVienServiceimpl;
import View.HocVienJFrame;
import static controller.HocVienController.showDialog;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter  ;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;
import javax.swing.*;
import utilt.ClassTableModel;
/**
 *
 * @author nhuyc
 */
public class QuanLyHocVienController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnXoa;
    private JTextField jtfSearch;
    private HocVienService hocVienService = null;
    private String[] listColumn = {
    "Mã học viên",
    "STT",
    "Họ tên",
    "Ngày sinh",
    "Giới tính(M: Nam, F: Nữ)",
    "Số điện thoại",
    "Email",
    "Địa chỉ",
    "Trình độ",
    "Nghề nghiệp",
    "Mục tiêu"};
    
private TableRowSorter<TableModel> rowSorter = null;
 
 
public QuanLyHocVienController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch,JButton btnXoa) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnXoa = btnXoa;
        
        hocVienService = new HocVienServiceimpl();
    }

public void setDataToTable()
{
    List <HocVien> listItem  = hocVienService.getList();
    
    DefaultTableModel model = new ClassTableModel().setTableHocVien(listItem, listColumn);
    JTable table = new JTable(model);

    rowSorter = new TableRowSorter<>(table.getModel());
    table.setRowSorter(rowSorter);
    
    jtfSearch.getDocument().addDocumentListener(new DocumentListener(){
        @Override
        public void insertUpdate(DocumentEvent e) {
            String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String text = jtfSearch.getText();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
        }
    });
    
    table.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if(e.getClickCount() == 2 && table.getSelectedRow() != -1)
          {
              DefaultTableModel model = (DefaultTableModel) table.getModel();
              int selectedRowIndex = table.getSelectedRow();
              
              selectedRowIndex = table.convertColumnIndexToModel(selectedRowIndex);
             
              
              HocVien hocVien = new HocVien();
                hocVien.setMaHV(model.getValueAt(selectedRowIndex, 0).toString());
                hocVien.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
                String dateStr = model.getValueAt(selectedRowIndex, 3).toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // hoặc "yyyy-MM-dd" tùy format bạn dùng
                    try {
                        Date date = sdf.parse(dateStr);
                        hocVien.setNgaySinh(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        // Bạn có thể thêm thông báo lỗi nếu cần
                    }
                hocVien.setGioiTinh(model.getValueAt(selectedRowIndex, 4).toString());
                hocVien.setSDT(model.getValueAt(selectedRowIndex, 5).toString());
                hocVien.setEmail(model.getValueAt(selectedRowIndex, 6).toString());
                hocVien.setDiaChi(model.getValueAt(selectedRowIndex, 7).toString());
                hocVien.setTrinhDo(model.getValueAt(selectedRowIndex, 8).toString());
                hocVien.setNgheNghiep(model.getValueAt(selectedRowIndex, 9).toString());
                hocVien.setMucTieu(model.getValueAt(selectedRowIndex, 10).toString());

              
              
              HocVienJFrame frame = new HocVienJFrame(hocVien);
              frame.setTitle("Thông tin học viên");
              frame.setResizable(false);
              frame.setLocationRelativeTo(null);
              frame.setVisible(true);
          }
        }
        
        
    });
  
     btnAdd.addMouseListener(new MouseAdapter(){
         
         @Override
            public void mouseClicked(MouseEvent e) {
              HocVien hocVien = new HocVien();
              HocVienJFrame frame = new HocVienJFrame(hocVien);
              frame.setTitle("Thông tin học viên");
              frame.setResizable(false);
              frame.setLocationRelativeTo(null);
              frame.setVisible(true);
            }
         @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
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
     
     btnXoa.addMouseListener(new MouseAdapter(){
         
         @Override
            public void mouseClicked(MouseEvent ev) {
              try{
                  DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    System.out.println(selectedRowIndex);
                    
                    HocVien hocVien = new HocVien();
                    hocVien.setMaHV(model.getValueAt(selectedRowIndex, 0).toString());
                    System.out.println(hocVien.getMaHV());
                    if (HocVienController.showDialog(hocVien)) {
                            hocVienService.delete(hocVien);
                            if (selectedRowIndex != -1) { // Kiểm tra đã chọn dòng nào chưa
                                model.removeRow(selectedRowIndex);
                            } else {
                                System.out.println("Chưa chọn dòng nào để xóa.");
                    }
                    }
    
              }  catch (Exception e){
                    e.printStackTrace();
                }
            }
         @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            
            
      });
     
     // design
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        
        

        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new CardLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
}
};
   