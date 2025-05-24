/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Doi_Tuong.NhanVien;
import java.util.List;
import DAO.NhanVienDAO;
import DAO.NhanVienDAOimpl;
/**
 *
 * @author nhuyc
 */
public class NhanVienServiceimpl implements NhanVienService{
    private NhanVienDAO nhanvienDAO = null;

    public NhanVienServiceimpl() {
        nhanvienDAO = new NhanVienDAOimpl();
    }
    
    @Override
    public List<NhanVien> getList() {
        return nhanvienDAO.getList();
    }
    @Override
    public int createOrUpdate(NhanVien nhanVien) {
        return nhanvienDAO.createOrUpdate(nhanVien);
    }

    @Override
    public int delete(NhanVien nhanVien) {
        return nhanvienDAO.delete(nhanVien);
    }
}
