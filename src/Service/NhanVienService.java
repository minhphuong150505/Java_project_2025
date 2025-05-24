/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Doi_Tuong.NhanVien;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public interface NhanVienService {
    public List<NhanVien> getList();
    public int createOrUpdate(NhanVien nhanVien);
    public int delete(NhanVien nhanVien);
}
