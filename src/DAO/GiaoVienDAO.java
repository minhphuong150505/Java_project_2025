/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

/**
 *
 * @author nhuyc
 */
import Doi_Tuong.GiaoVien;
import java.util.List;

public interface GiaoVienDAO {
    public List<GiaoVien> getList();
    public int createOrUpdate(GiaoVien gv);
    public int delete(GiaoVien gv);
}