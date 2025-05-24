/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Doi_Tuong.GiaoVien;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public interface GiaoVienService {
    public List<GiaoVien> getList();
    public int createOrUpdate(GiaoVien gv);
    public int delete(GiaoVien gv);
}
