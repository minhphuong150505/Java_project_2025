/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.HocVienDAO;
import DAO.HocVienDAOimpl;
import Doi_Tuong.HocVien;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public class HocVienServiceimpl implements HocVienService {

    private HocVienDAO hocvienDAO = null;

    public HocVienServiceimpl() {
        hocvienDAO = new HocVienDAOimpl();
    }
    
    
    
    @Override
    public List<HocVien> getList() {
        return hocvienDAO.getList();
    }
    @Override
    public int createOrUpdate(HocVien hocVien) {
        return hocvienDAO.createOrUpdate(hocVien);
    }

    @Override
    public int delete(HocVien hocVien) {
        return hocvienDAO.delete(hocVien);
    }
}
