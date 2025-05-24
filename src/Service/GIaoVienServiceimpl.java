/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.GiaoVienDAO;
import DAO.GiaoVienDAOimpl;
import Doi_Tuong.GiaoVien;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public class GIaoVienServiceimpl implements GiaoVienService{
    private GiaoVienDAO giaovienDAO = null;

    public GIaoVienServiceimpl() {
        giaovienDAO = new GiaoVienDAOimpl();
    }
    
    @Override
    public List<GiaoVien> getList() {
        return giaovienDAO.getList();
    }
    @Override
    public int createOrUpdate(GiaoVien giaoVien) {
        return giaovienDAO.createOrUpdate(giaoVien);
    }

    @Override
    public int delete(GiaoVien giaoVien) {
        return giaovienDAO.delete(giaoVien);
    }
}
