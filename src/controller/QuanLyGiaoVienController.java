/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import Doi_Tuong.GiaoVien;
import Service.GiaoVienService;
import Service.GIaoVienServiceimpl;
import View.GiaoVienJFrame;
import static controller.GiaoVienController.showDialog;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter  ;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
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
public class QuanLyGiaoVienController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JButton btnXoa;
    private JTextField jtfSearch;
    private String kindSelected = "";
    private GiaoVienService giaovienService = null;
    private String[] listColumn = {
    "Mã giaovien",
    "STT",
    "Họ tên",
    "Ngày sinh",
    "Giới tính(M: Nam, F: Nữ)",
    "CMND",
    "Số điện thoại",
    "Email",
    "Địa chỉ",
    "Trình độ",
    "Kinh Nghiệm",
    "Ngày Vào Làm",
    "Lương Cơ Bản"};
    
private TableRowSorter<TableModel> rowSorter = null;
 
 
public QuanLyGiaoVienController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch,JButton btnXoa,String kindSelected) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnXoa = btnXoa;
        this.kindSelected = kindSelected;
        giaovienService = new GIaoVienServiceimpl();
    }

public void setDataToTable()
{
    List <GiaoVien> listItem  = giaovienService.getList();
    
    DefaultTableModel model = new ClassTableModel().setTableGiaoVien(listItem, listColumn);
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
             
              
              GiaoVien giaoVien = new GiaoVien();
                giaoVien.setMaGV(model.getValueAt(selectedRowIndex, 0).toString());
                giaoVien.setHoTen(model.getValueAt(selectedRowIndex, 2).toString());
                String dateStr = model.getValueAt(selectedRowIndex, 3).toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // hoặc "yyyy-MM-dd" tùy format bạn dùng
                    try {
                        Date date = sdf.parse(dateStr);
                        giaoVien.setNgaySinh(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        // Bạn có thể thêm thông báo lỗi nếu cần
                    }
                giaoVien.setGioiTinh(model.getValueAt(selectedRowIndex, 4).toString());
                giaoVien.setCmnd(model.getValueAt(selectedRowIndex,5).toString());
                giaoVien.setSdt(model.getValueAt(selectedRowIndex, 6).toString());
                giaoVien.setEmail(model.getValueAt(selectedRowIndex, 7).toString());
                giaoVien.setDiaChi(model.getValueAt(selectedRowIndex, 8).toString());
                giaoVien.setTrinhDo(model.getValueAt(selectedRowIndex, 9).toString());
                giaoVien.setKinhNghiem(model.getValueAt(selectedRowIndex, 10).toString());
                dateStr = model.getValueAt(selectedRowIndex, 11).toString();
                    try {
                        Date date = sdf.parse(dateStr);
                        giaoVien.setNgayVaoLam(date);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                        // Bạn có thể thêm thông báo lỗi nếu cần
                    }
                //giaoVien.setLuongCoBan(Double.parseDouble(model.getValueAt(selectedRowIndex, 12).toString()));
                String luongStr = model.getValueAt(selectedRowIndex, 12).toString();
                BigDecimal luongDecimal = new BigDecimal(luongStr);
                giaoVien.setLuongCoBan(luongDecimal.doubleValue());

              
              
              GiaoVienJFrame frame = new GiaoVienJFrame(giaoVien);
              frame.setTitle("Thông tin giáo viên");
              frame.setResizable(false);
              frame.setLocationRelativeTo(null);
              frame.setVisible(true);
          }
        }
        
        
    });
  
     btnAdd.addMouseListener(new MouseAdapter(){
         
         @Override
            public void mouseClicked(MouseEvent e) {
                if(kindSelected.equals("Giao Vien")){
                    
                    GiaoVien giaoVien = new GiaoVien();
                    GiaoVienJFrame frame = new GiaoVienJFrame(giaoVien);
                    frame.setTitle("Thông tin giáo viên");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
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
                if(kindSelected.equals("Giao Vien")){
                 
                    try{
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                          int selectedRowIndex = table.getSelectedRow();
                          System.out.println(selectedRowIndex);

                          GiaoVien giaoVien = new GiaoVien();
                          giaoVien.setMaGV(model.getValueAt(selectedRowIndex, 0).toString());

                          if (GiaoVienController.showDialog(giaoVien)) {
                                  giaovienService.delete(giaoVien);
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
   