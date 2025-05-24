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
public class HocVien {
    private String maHV;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String SDT;
    private String Email;
    private String diaChi;
    private String trinhDo;
    private String ngheNghiep;
    private String mucTieu;

    public HocVien() {
    }

    public HocVien(String maHV, String hoTen, Date ngaySinh, String gioiTinh, String SDT, String Email, String diaChi, String trinhDo, String ngheNghiep, String mucTieu) {
        this.maHV = maHV;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.SDT = SDT;
        this.Email = Email;
        this.diaChi = diaChi;
        this.trinhDo = trinhDo;
        this.ngheNghiep = ngheNghiep;
        this.mucTieu = mucTieu;
    }

    public String getMaHV() {
        return maHV;
    }

    public void setMaHV(String maHV) {
        this.maHV = maHV;
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

    public String getTrinhDo() {
        return trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getMucTieu() {
        return mucTieu;
    }

    public void setMucTieu(String mucTieu) {
        this.mucTieu = mucTieu;
    }

    @Override
    public String toString() {
        return maHV + "- " + hoTen;
    }
    
}
