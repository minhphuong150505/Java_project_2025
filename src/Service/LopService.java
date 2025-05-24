/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Doi_Tuong.Lop;
import java.util.List;


public interface LopService {
    public List<Lop> getList();
    public int createOrUpdate(Lop lop);
    public int delete(Lop lop);
}
