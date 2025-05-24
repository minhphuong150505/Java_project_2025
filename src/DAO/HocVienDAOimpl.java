/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.HocVienDAO;
import Doi_Tuong.HocVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhuyc
 */
public class HocVienDAOimpl implements HocVienDAO{

    @Override
    public List<HocVien> getList() {
        Connection conn = null;
            try {
                conn = ConnectionUtils.getMyConnection();

                // Tắt chế độ auto commit
                conn.setAutoCommit(false);

                // Thiết lập mức cô lập giao dịch là SERIALIZABLE
                conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

                // Thực hiện các thao tác DB
                String sql = "SELECT * FROM HOCVIEN ";
                List<HocVien> list = new ArrayList<>();
                PreparedStatement ps = conn.prepareStatement(sql);
               
                ResultSet rs = ps.executeQuery();

                // Có thể thực hiện nhiều lệnh nữa...
                while(rs.next())
                    {
                        HocVien hocVien = new HocVien();
                        hocVien.setMaHV(rs.getString("MAHV"));
                        hocVien.setHoTen(rs.getString("HOTEN"));
                        hocVien.setNgaySinh(rs.getDate("NGAYSINH"));
                        hocVien.setGioiTinh(rs.getString("GIOITINH"));
                        hocVien.setSDT(rs.getString("SDT"));
                        hocVien.setEmail(rs.getString("EMAIL"));
                        hocVien.setDiaChi(rs.getString("DIACHI"));
                        hocVien.setTrinhDo(rs.getString("TRINHDO"));
                        hocVien.setNgheNghiep(rs.getString("NGHENGHIEP"));
                        hocVien.setMucTieu(rs.getString("MUCTIEU"));
                        list.add(hocVien);
                    }
                    rs.close();
                // Commit nếu mọi thứ OK
                conn.commit();
                return list;
            } catch (SQLException | ClassNotFoundException ex) {
                if (conn != null) {
                    try {
                        conn.rollback(); // rollback khi có lỗi
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                ex.printStackTrace();

            } finally {
                if (conn != null) {
                    try {
                       // conn.setAutoCommit(true); // đưa về trạng thái mặc định
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
//       try{
//           Connection con = ConnectionUtils.getMyConnection();
//           String sql = "SELECT * FROM HOCVIEN ";
//           List<HocVien> list = new ArrayList<>();
//           PreparedStatement ps = con.prepareCall(sql);
//      
//           ResultSet rs = ps.executeQuery();
//           while(rs.next())
//           {
//               HocVien hocVien = new HocVien();
//               hocVien.setMaHV(rs.getString("MAHV"));
//               hocVien.setHoTen(rs.getString("HOTEN"));
//               hocVien.setNgaySinh(rs.getDate("NGAYSINH"));
//               hocVien.setGioiTinh(rs.getString("GIOITINH"));
//               hocVien.setSDT(rs.getString("SDT"));
//               hocVien.setEmail(rs.getString("EMAIL"));
//               hocVien.setDiaChi(rs.getString("DIACHI"));
//               hocVien.setTrinhDo(rs.getString("TRINHDO"));
//               hocVien.setNgheNghiep(rs.getString("NGHENGHIEP"));
//               hocVien.setMucTieu(rs.getString("MUCTIEU"));
//               list.add(hocVien);
//           }
//           rs.close();
//           con.close();
//           return list;          
//       } catch (SQLException e){
//           e.printStackTrace();
//       }
//         catch (ClassNotFoundException ex) {
//            Logger.getLogger(HocVienDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//       return null;
        return null;
}
    @Override
    public int createOrUpdate(HocVien hocVien) {
        Connection conn = null;

        try {
            conn = ConnectionUtils.getMyConnection();

            // Tắt chế độ auto-commit để bắt đầu giao dịch
            conn.setAutoCommit(false);

            // Set transaction isolation level
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            // Thực hiện các thao tác với cơ sở dữ liệu
            // ...
              String sql = "{call PROC_INSERT_OR_UPDATE_HOCVIEN(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                CallableStatement cs = conn.prepareCall(sql);

                cs.setString(1, hocVien.getMaHV());              // p_mahv
                cs.setString(2, hocVien.getHoTen());             // p_hoten
                cs.setDate(3, new java.sql.Date(hocVien.getNgaySinh().getTime())); // p_ngaysinh
                cs.setString(4, hocVien.getGioiTinh());          // p_gioitinh
                cs.setString(5, hocVien.getSDT());               // p_sdt
                cs.setString(6, hocVien.getEmail());             // p_email
                cs.setString(7, hocVien.getDiaChi());            // p_diachi
                cs.setString(8, hocVien.getTrinhDo());           // p_trinhdo
                cs.setString(9, hocVien.getNgheNghiep());        // p_nghenghiep
                cs.setString(10, hocVien.getMucTieu());          // p_muctieu
                cs.execute();
            // Commit giao dịch
            conn.commit();
        
            return 1;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HocVienDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
}

    @Override
    public int delete(HocVien hocVien) {
    Connection cons = null;
    CallableStatement cs = null;
    String sql = "{call PROC_DELETE_HOCVIEN(?)}";

    try {
        cons = ConnectionUtils.getMyConnection();

        // Set mức cách ly giao dịch
        cons.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

        // Tắt auto-commit để bắt đầu giao dịch thủ công
        cons.setAutoCommit(false);

        cs = cons.prepareCall(sql);
        cs.setString(1, hocVien.getMaHV());
        cs.execute();
        
        cons.commit(); // Commit nếu không lỗi
        return 1;

    } catch (Exception ex) {
        ex.printStackTrace();
        try {
            if (cons != null) cons.rollback(); // Rollback nếu có lỗi
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
    } finally {
        try {
            if (cs != null) cs.close();
            if (cons != null) cons.close();
        } catch (SQLException closeEx) {
            closeEx.printStackTrace();
        }
    }

    return 0;
}
}
