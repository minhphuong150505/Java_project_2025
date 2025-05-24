/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DAO.HocVienDAO;
import DAO.LopDAO;
import DAO.LopDAOimpl;
import Doi_Tuong.Lop;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public class LopServiceimpl implements LopService{
    private LopDAO lopDAO = null;
    
    public LopServiceimpl() {
        lopDAO = new LopDAOimpl();
    }
    @Override
    public List<Lop> getList() {
        return lopDAO.getList();
     }

    @Override
    public int createOrUpdate(Lop lop) {
        return lopDAO.createOrUpdate(lop);
       }

    @Override
    public int delete(Lop lop) {
        return lopDAO.delete(lop);
         }
    
}
