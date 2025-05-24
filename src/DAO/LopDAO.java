/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Doi_Tuong.Lop;
import java.util.List;

/**
 *
 * @author nhuyc
 */
public interface LopDAO {
    public List<Lop> getList();
    public int createOrUpdate(Lop lop);
    public int delete(Lop lop);
}
