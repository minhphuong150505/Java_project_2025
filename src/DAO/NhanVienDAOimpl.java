  package DAO;

import Doi_Tuong.NhanVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NhanVienDAOimpl implements NhanVienDAO {

    @Override
    public List<NhanVien> getList() {
        Connection conn = null;
        List<NhanVien> list = new ArrayList<>();
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            String sql = "SELECT * FROM NHANVIEN";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MANV"));
                nv.setHoTen(rs.getString("HOTEN"));
                nv.setNgaySinh(rs.getDate("NGAYSINH"));
                nv.setGioiTinh(rs.getString("GIOITINH"));
                nv.setCMND(rs.getString("CMND"));
                nv.setSDT(rs.getString("SDT"));
                nv.setEmail(rs.getString("EMAIL"));
                nv.setDiaChi(rs.getString("DIACHI"));
                nv.setKinhNghiem(rs.getString("KINHNGHIEM"));
                nv.setViTri(rs.getString("VITRI"));
                nv.setNgayVaoLam(rs.getDate("NGAYVAOLAM"));
                nv.setLuongCoBan(rs.getDouble("LUONGCOBAN"));
                list.add(nv);
            }
           conn.commit();
            rs.close();
            ps.close();
        } catch (SQLException | ClassNotFoundException ex) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @Override
    public int createOrUpdate(NhanVien nv) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            String sql = "{call PROC_INSERT_OR_UPDATE_NHANVIEN(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, nv.getMaNV());
            cs.setString(2, nv.getHoTen());
            cs.setDate(3, new java.sql.Date(nv.getNgaySinh().getTime()));
            cs.setString(4, nv.getGioiTinh());
            cs.setString(5, nv.getCMND());
            cs.setString(6, nv.getSDT());
            cs.setString(7, nv.getEmail());
            cs.setString(8, nv.getDiaChi());
            cs.setString(9, nv.getKinhNghiem());
            cs.setString(10, nv.getViTri());
            cs.setDate(11, new java.sql.Date(nv.getNgayVaoLam().getTime()));
            cs.setDouble(12, nv.getLuongCoBan());
            cs.execute();
            cs.close();
            conn.commit();
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
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
    public int delete(NhanVien nv) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setAutoCommit(false);

            String sql = "{call PROC_DELETE_NHANVIEN(?)}";
            cs = conn.prepareCall(sql);
            cs.setString(1, nv.getMaNV());
            cs.execute();
            cs.close();
            conn.commit();
            return 1;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (cs != null) cs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    
}
