/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Doi_Tuong;

/**
 *
 * @author nhuyc
 */
import java.util.Date;

public class GiaoVien {
    private String maGV;          // CHAR(6)
    private String hoTen;         // NVARCHAR2
    private Date ngaySinh;        // DATE
    private String gioiTinh;      // CHAR(1)
    private String cmnd;          // CHAR(12)
    private String sdt;           // VARCHAR2(20)
    private String email;         // VARCHAR2(20)
    private String diaChi;        // NVARCHAR2
    private String trinhDo;       // NVARCHAR2
    private String kinhNghiem;    // NVARCHAR2
    private Date ngayVaoLam;      // DATE
    private double luongCoBan;    // NUMBER

    // Constructors
    public GiaoVien() {}

    public GiaoVien(String maGV, String hoTen, Date ngaySinh, String gioiTinh, String cmnd,
                    String sdt, String email, String diaChi, String trinhDo, String kinhNghiem,
                    Date ngayVaoLam, double luongCoBan) {
        this.maGV = maGV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.cmnd = cmnd;
        this.sdt = sdt;
        this.email = email;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.kinhNghiem = kinhNghiem;
        this.ngayVaoLam = ngayVaoLam;
        this.luongCoBan = luongCoBan;
    }

    // Getters and Setters
    public String getMaGV() { return maGV; }
    public void setMaGV(String maGV) { this.maGV = maGV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public Date getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(Date ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getCmnd() { return cmnd; }
    public void setCmnd(String cmnd) { this.cmnd = cmnd; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getTrinhDo() { return trinhDo; }
    public void setTrinhDo(String trinhDo) { this.trinhDo = trinhDo; }

    public String getKinhNghiem() { return kinhNghiem; }
    public void setKinhNghiem(String kinhNghiem) { this.kinhNghiem = kinhNghiem; }

    public Date getNgayVaoLam() { return ngayVaoLam; }
    public void setNgayVaoLam(Date ngayVaoLam) { this.ngayVaoLam = ngayVaoLam; }

    public double getLuongCoBan() { return luongCoBan; }
    public void setLuongCoBan(double luongCoBan) { this.luongCoBan = luongCoBan; }
}

