/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BeAn.DangKyBean;
import BeAn.LopHocBean;
import com.sun.jdi.connect.spi.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public class ThongKeDAOimpl implements ThongKeDAO{

   
    @Override
    public List<DangKyBean> getListByDangKy(){
        java.sql.Connection conn = null;
        List<DangKyBean> list = new ArrayList<>();
        try {
             conn = ConnectionUtils.getMyConnection();
            String sql = "SELECT TGDANGKY, COUNT(*) as so_luong FROM DANGKYLOP GROUP BY TGDANGKY";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DangKyBean dangKyBean = new DangKyBean();
                dangKyBean.setNgayDangKy(rs.getString("TGDANGKY"));
                dangKyBean.setSoLuongHocVien(rs.getInt("so_luong"));
                list.add(dangKyBean);
            }
            ps.close();
            conn.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LopHocBean> getListByLopHoc() {
        java.sql.Connection conn = null;
         String sql = "SELECT TENLOP ||' ' ||BAND AS LOP,TGBATDAU,TGKETTHUC    "
                + "FROM  LOP "
                + "ORDER BY TGBATDAU ASC";
        List<LopHocBean> list = new ArrayList<>(); 
        try {
            conn = ConnectionUtils.getMyConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LopHocBean lopHocBean = new LopHocBean();
                lopHocBean.setTenLopHoc(rs.getString("LOP"));
                lopHocBean.setNgayBatDau(rs.getDate("TGBATDAU"));
                lopHocBean.setNgayKetThuc(rs.getDate("TGKETTHUC"));
                list.add(lopHocBean);
            }
            ps.close();
            conn.close();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }
}
