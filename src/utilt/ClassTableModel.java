/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilt;

import Doi_Tuong.GiaoVien;
import Doi_Tuong.HocVien;
import Doi_Tuong.NhanVien;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author nhuyc
 */
public class ClassTableModel {
    public DefaultTableModel setTableHocVien(List<HocVien> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        int num = listItem.size();
        HocVien hocVien = null;
        for (int i = 0; i < num; i++) {
            hocVien = listItem.get(i);
            obj = new Object[columns];
            obj[0] = hocVien.getMaHV();
            obj[1] = (i + 1);
            obj[2] = hocVien.getHoTen();
            Date ngaySinh = hocVien.getNgaySinh();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            obj[3] = ngaySinh != null ? sdf.format(ngaySinh) : "";
            //obj[3] = hocVien.getNgaySinh();
            obj[4] = hocVien.getGioiTinh();
            obj[5] = hocVien.getSDT();
            obj[6] = hocVien.getEmail();
            obj[7] = hocVien.getDiaChi();
            obj[8] = hocVien.getTrinhDo();
            obj[9] = hocVien.getNgheNghiep();
            obj[10] = hocVien.getMucTieu();
            
            dtm.addRow(obj);
        }
        return dtm;
    }
    public DefaultTableModel setTableNhanVien(List<NhanVien> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        int num = listItem.size();
        NhanVien nhanVien = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < num; i++) {
            nhanVien = listItem.get(i);
            obj = new Object[columns];
            obj[0] = nhanVien.getMaNV();
            obj[1] = (i + 1);
            obj[2] = nhanVien.getHoTen();
            obj[3] = nhanVien.getNgaySinh() != null ? sdf.format(nhanVien.getNgaySinh()) : "";
            obj[4] = nhanVien.getGioiTinh();
            obj[5] = nhanVien.getCMND();
            obj[6] = nhanVien.getSDT();
            obj[7] = nhanVien.getEmail();
            obj[8] = nhanVien.getDiaChi();
            obj[9] = nhanVien.getKinhNghiem();
            obj[10] = nhanVien.getViTri();
            obj[11] = nhanVien.getNgayVaoLam() != null ? sdf.format(nhanVien.getNgayVaoLam()) : "";
            obj[12] = String.valueOf(nhanVien.getLuongCoBan());
            dtm.addRow(obj);
        }
        return dtm;
    }

    public DefaultTableModel setTableGiaoVien(List<GiaoVien> listItem, String[] listColumn) {
        int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        int num = listItem.size();
        GiaoVien giaoVien = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < num; i++) {
            giaoVien = listItem.get(i);
            obj = new Object[columns];
            obj[0] = giaoVien.getMaGV();
            obj[1] = (i + 1);
            obj[2] = giaoVien.getHoTen();
            obj[3] = giaoVien.getNgaySinh() != null ? sdf.format(giaoVien.getNgaySinh()) : "";
            obj[4] = giaoVien.getGioiTinh();
            obj[5] = giaoVien.getCmnd();
            obj[6] = giaoVien.getSdt();
            obj[7] = giaoVien.getEmail();
            obj[8] = giaoVien.getDiaChi();
            obj[9] = giaoVien.getTrinhDo();
            obj[10] = giaoVien.getKinhNghiem();
            obj[11] = giaoVien.getNgayVaoLam() != null ? sdf.format(giaoVien.getNgayVaoLam()) : "";
            obj[12] = String.valueOf(giaoVien.getLuongCoBan());
            dtm.addRow(obj);
        }
        return dtm;
    }
}
