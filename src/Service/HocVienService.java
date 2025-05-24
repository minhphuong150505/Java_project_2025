/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Doi_Tuong.HocVien;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public interface HocVienService {
    public List<HocVien> getList();
    public int createOrUpdate(HocVien hocVien);
    public int delete(HocVien hocVien);
}
