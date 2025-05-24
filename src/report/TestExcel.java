/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package report;

import Doi_Tuong.HocVien;
import Service.HocVienService;
import Service.HocVienServiceimpl;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {

    public static void main(String[] args) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Học viên");

            XSSFRow row = null;
            Cell cell = null;

            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, Cell.CELL_TYPE_STRING);
            cell.setCellValue("DANH SÁCH HỌC VIÊN");

            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, Cell.CELL_TYPE_STRING);
            cell.setCellValue("STT");
            cell = row.createCell(1, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Họ và tên");
            cell = row.createCell(2, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Ngày sinh");
            cell = row.createCell(3, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Giới tính");
            cell = row.createCell(4, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Số điện thoại");
            cell = row.createCell(5, Cell.CELL_TYPE_STRING);
            cell.setCellValue("Địa chỉ");

            HocVienService hocVienService = new HocVienServiceimpl();

            List<HocVien> listItem = hocVienService.getList();

            for (int i = 0; i < listItem.size(); i++) {
                HocVien hocVien = listItem.get(i);
                row = spreadsheet.createRow((short) 4 + i);
                row.setHeight((short) 400);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(hocVien.getHoTen());
                row.createCell(2).setCellValue(hocVien.getNgaySinh().toString());
                row.createCell(3).setCellValue("M".equals(hocVien.getGioiTinh()) ? "Male" : "Female");
                row.createCell(4).setCellValue(hocVien.getSDT());
                row.createCell(5).setCellValue(hocVien.getDiaChi());
            }

            FileOutputStream out = new FileOutputStream(new File("C:/lib java/Exel_file_test/test.xlsx"));
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}