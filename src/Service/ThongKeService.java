/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import BeAn.DangKyBean;
import BeAn.LopHocBean;
import java.util.List;

public interface ThongKeService {
    
    public List<LopHocBean> getListByLopHoc();
    
    public List<DangKyBean> getListByDangKy();
    
}