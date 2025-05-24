/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Doi_Tuong;

import java.util.Date;

public class Lop {
       private String maLop;
        private String tenLop;
        private Date tgBatDau;
        private Date tgKetThuc;
        private String tgHoc;
        private int soBuoiHoc;
        private int siSo;
        private String maGV;
        private double hocPhi;
        private String band;

    public Lop() {
    }

    public Lop(String maLop, String tenLop, Date tgBatDau, Date tgKetThuc, String tgHoc, int soBuoiHoc, int siSo, String maGV, double hocPhi, String band) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.tgBatDau = tgBatDau;
        this.tgKetThuc = tgKetThuc;
        this.tgHoc = tgHoc;
        this.soBuoiHoc = soBuoiHoc;
        this.siSo = siSo;
        this.maGV = maGV;
        this.hocPhi = hocPhi;
        this.band = band;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public Date getTgBatDau() {
        return tgBatDau;
    }

    public void setTgBatDau(Date tgBatDau) {
        this.tgBatDau = tgBatDau;
    }

    public Date getTgKetThuc() {
        return tgKetThuc;
    }

    public void setTgKetThuc(Date tgKetThuc) {
        this.tgKetThuc = tgKetThuc;
    }

    public String getTgHoc() {
        return tgHoc;
    }

    public void setTgHoc(String tgHoc) {
        this.tgHoc = tgHoc;
    }

    public int getSoBuoiHoc() {
        return soBuoiHoc;
    }

    public void setSoBuoiHoc(int soBuoiHoc) {
        this.soBuoiHoc = soBuoiHoc;
    }

    public int getSiSo() {
        return siSo;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }
        
}
