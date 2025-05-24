/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BeAn;

import java.util.Date;

public class LopHocBean {
    private String tenLopHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    public LopHocBean(String tenLopHoc, Date ngayBatDau, Date ngayKetThuc) {
        this.tenLopHoc = tenLopHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public LopHocBean() {
    }

    public String getTenLopHoc() {
        return tenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }
   
    
}
