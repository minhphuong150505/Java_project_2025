/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import BeAn.DangKyBean;
import BeAn.LopHocBean;
import DAO.ThongKeDAO;
import DAO.ThongKeDAOimpl;
import java.util.List;

public class ThongKeServiceimpl implements ThongKeService {

    private ThongKeDAO thongKeDAO = null;

    public ThongKeServiceimpl() {
        this.thongKeDAO = new ThongKeDAOimpl();
    }

    @Override
    public List<LopHocBean> getListByLopHoc() {
        return thongKeDAO.getListByLopHoc();
    }

    @Override
    public List<DangKyBean> getListByDangKy() {
        return thongKeDAO.getListByDangKy();
    }

}