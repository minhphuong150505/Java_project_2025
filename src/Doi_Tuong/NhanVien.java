/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Doi_Tuong;

import java.util.Date;

/**
 *
 * @author nhuyc
 */
public class NhanVien {
    private String maNV;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String CMND;
    private String SDT;
    private String Email;
    private String diaChi;
    private String kinhNghiem;
    private String viTri;
    private Date ngayVaoLam;
    private double luongCoBan;
    
    public NhanVien(String maNV, String hoTen, Date ngaySinh, String gioiTinh, String CMND, String SDT, String Email, String diaChi, String kinhNghiem, String viTri, Date ngayVaoLam, double luongCoBan) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.CMND = CMND;
        this.SDT = SDT;
        this.Email = Email;
        this.diaChi = diaChi;
        this.kinhNghiem = kinhNghiem;
        this.viTri = viTri;
        this.ngayVaoLam = ngayVaoLam;
        this.luongCoBan = luongCoBan;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", hoTen=" + hoTen + ", ngaySinh=" + ngaySinh + ", gioiTinh=" + gioiTinh + ", CMND=" + CMND + ", SDT=" + SDT + ", Email=" + Email + ", diaChi=" + diaChi + ", kinhNghiem=" + kinhNghiem + ", viTri=" + viTri + ", ngayVaoLam=" + ngayVaoLam + ", luongCoBan=" + luongCoBan + '}';
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getKinhNghiem() {
        return kinhNghiem;
    }

    public void setKinhNghiem(String kinhNghiem) {
        this.kinhNghiem = kinhNghiem;
    }

    public String getViTri() {
        return viTri;
    }

    public void setViTri(String viTri) {
        this.viTri = viTri;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public NhanVien() {
    }
    
}
