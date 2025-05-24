package DAO;

import Doi_Tuong.Lop;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LopDAOimpl implements LopDAO {

    @Override
    public List<Lop> getList() {
        Connection conn = null;
        List<Lop> list = new ArrayList<>();
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            String sql = "SELECT * FROM LOP";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Lop lop = new Lop();
                lop.setMaLop(rs.getString("MALOP"));
                lop.setTenLop(rs.getString("TENLOP"));
                lop.setTgBatDau(rs.getDate("TGBATDAU"));
                lop.setTgKetThuc(rs.getDate("TGKETTHUC"));
                lop.setTgHoc(rs.getString("TGHOC"));
                lop.setSoBuoiHoc(rs.getInt("SOBUOIHOC"));
                lop.setSiSo(rs.getInt("SISO"));
                lop.setMaGV(rs.getString("MAGV"));
                lop.setHocPhi(rs.getDouble("HOCPHI"));
                lop.setBand(rs.getString("BAND"));
                list.add(lop);
            }

            conn.commit();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public int createOrUpdate(Lop lop) {
        Connection conn = null;
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            String sql = "{call PROC_INSERT_OR_UPDATE_LOP(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setString(1, lop.getMaLop());
            cs.setString(2, lop.getTenLop());
            cs.setDate(3, new java.sql.Date(lop.getTgBatDau().getTime()));
            cs.setDate(4, new java.sql.Date(lop.getTgKetThuc().getTime()));
            cs.setString(5, lop.getTgHoc());
            cs.setInt(6, lop.getSoBuoiHoc());
            cs.setInt(7, lop.getSiSo());
            cs.setString(8, lop.getMaGV());
            cs.setDouble(9, lop.getHocPhi());
            cs.setString(10, lop.getBand());

            cs.execute();
            conn.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int delete(Lop lop) {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = "{call PROC_DELETE_LOP(?)}";
        try {
            conn = ConnectionUtils.getMyConnection();
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            conn.setAutoCommit(false);

            cs = conn.prepareCall(sql);
            cs.setString(1, lop.getMaLop());
            cs.execute();
            conn.commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } finally {
            try {
                if (cs != null) cs.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return 0;
    }
}