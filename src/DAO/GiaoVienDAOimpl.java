package DAO;

import Doi_Tuong.GiaoVien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GiaoVienDAOimpl implements GiaoVienDAO {

    @Override
    public List<GiaoVien> getList() {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            String sql = "SELECT * FROM GIAOVIEN";
            List<GiaoVien> list = new ArrayList<>();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                GiaoVien gv = new GiaoVien();
                gv.setMaGV(rs.getString("MAGV"));
                gv.setHoTen(rs.getString("HOTEN"));
                gv.setNgaySinh(rs.getDate("NGAYSINH"));
                gv.setGioiTinh(rs.getString("GIOITINH"));
                gv.setCmnd(rs.getString("CMND"));
                gv.setSdt(rs.getString("SDT"));
                gv.setEmail(rs.getString("EMAIL"));
                gv.setDiaChi(rs.getString("DIACHI"));
                gv.setTrinhDo(rs.getString("TRINHDO"));
                gv.setKinhNghiem(rs.getString("KINHNGHIEM"));
                gv.setNgayVaoLam(rs.getDate("NGAYVAOLAM"));
                gv.setLuongCoBan(rs.getDouble("LUONGCOBAN"));
                list.add(gv);
            }
            conn.commit();
            rs.close();
            return list;

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
        return null;
    }

    @Override
    public int createOrUpdate(GiaoVien gv) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            String sql = "{call PROC_INSERT_OR_UPDATE_GIAOVIEN(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement cs = conn.prepareCall(sql);

            cs.setString(1, gv.getMaGV());
            cs.setString(2, gv.getHoTen());
            cs.setDate(3, new java.sql.Date(gv.getNgaySinh().getTime()));
            cs.setString(4, gv.getGioiTinh());
            cs.setString(5, gv.getCmnd());
            cs.setString(6, gv.getSdt());
            cs.setString(7, gv.getEmail());
            cs.setString(8, gv.getDiaChi());
            cs.setString(10, gv.getTrinhDo());
            cs.setString(9, gv.getKinhNghiem());
            cs.setDate(11, new java.sql.Date(gv.getNgayVaoLam().getTime()));
            cs.setDouble(12, gv.getLuongCoBan());
            cs.execute();
             conn.commit();
            return 1;

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
        return 0;
    }

    @Override
    public int delete(GiaoVien gv) {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = "{call PROC_DELETE_GIAOVIEN(?)}";

        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setAutoCommit(false);

            cs = conn.prepareCall(sql);
            cs.setString(1, gv.getMaGV());
            cs.execute();
            conn.commit();
            return 1;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                if (cs != null) cs.close();
                if (conn != null) conn.close();
            } catch (SQLException closeEx) {
                closeEx.printStackTrace();
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        GiaoVienDAO dao = new GiaoVienDAOimpl();
        System.out.println(dao.getList());
    }
}