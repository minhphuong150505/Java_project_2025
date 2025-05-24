/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BeAn.DangKyBean;
import BeAn.LopHocBean;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public interface ThongKeDAO {
    
    public List<LopHocBean> getListByLopHoc();
    public List<DangKyBean> getListByDangKy();
    
}
