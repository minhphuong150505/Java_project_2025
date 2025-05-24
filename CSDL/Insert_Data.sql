-- 1. GIAOVIEN
INSERT INTO GIAOVIEN VALUES ('GV001', N'Nguyễn Văn A', TO_DATE('01-01-1980','DD-MM-YYYY'), 'M', '123456789012', '0909123456', 'a@gmail.com', N'Hà Nội', N'Cử nhân', N'5 năm', TO_DATE('01-01-2020','DD-MM-YYYY'), 10000000);
INSERT INTO GIAOVIEN VALUES ('GV002', N'Lê Thị B', TO_DATE('15-05-1985','DD-MM-YYYY'), 'F', '123456789013', '0909234567', 'b@gmail.com', N'Hồ Chí Minh', N'Thạc sĩ', N'10 năm', TO_DATE('01-02-2018','DD-MM-YYYY'), 15000000);

-- 2. NHANVIEN
INSERT INTO NHANVIEN VALUES ('NV001', N'Phạm Văn C', TO_DATE('20-02-1990','DD-MM-YYYY'), 'M', '123456789014', '0912123456', 'nv1@cty.com', N'Đà Nẵng', N'2 năm', N'Tư vấn', TO_DATE('01-03-2022','DD-MM-YYYY'), 8000000);
INSERT INTO NHANVIEN VALUES ('NV002', N'Trần Thị D', TO_DATE('10-07-1992','DD-MM-YYYY'), 'F', '123456789015', '0912345678', 'nv2@cty.com', N'Cần Thơ', N'3 năm', N'CSKH', TO_DATE('01-04-2021','DD-MM-YYYY'), 8500000);

-- 3. HOCVIEN
INSERT INTO HOCVIEN VALUES ('HV001', N'Ngô Minh E', TO_DATE('01-01-2000','DD-MM-YYYY'), 'M', '0912456789', 'hv1@gmail.com', N'Hà Nội', 'A1', N'Sinh viên', N'Giao tiếp');
INSERT INTO HOCVIEN VALUES ('HV002', N'Lê Thị F', TO_DATE('05-05-1998','DD-MM-YYYY'), 'F', '0912678945', 'hv2@gmail.com', N'Hồ Chí Minh', 'B1', N'Kế toán', N'Thi IELTS');
INSERT INTO HOCVIEN VALUES ('HV003', N'Nguyễn Văn Hùng', TO_DATE('15-07-1999','DD-MM-YYYY'), 'M', '0912345678', 'hung.nguyen99@gmail.com', N'Đà Nẵng', 'C1', N'Kỹ sư', N'TOEIC');
INSERT INTO HOCVIEN VALUES ('HV004', N'Phạm Minh Quân', TO_DATE('22-11-2001','DD-MM-YYYY'), 'M', '0918765432', 'quan.pham2001@yahoo.com', N'Cần Thơ', 'B2', N'Sinh viên', N'Giao tiếp');
INSERT INTO HOCVIEN VALUES ('HV005', N'Lê Thị Lan', TO_DATE('30-03-2000','DD-MM-YYYY'), 'F', '0903456789', 'lan.le00@gmail.com', N'Hồ Chí Minh', 'A2', N'Giáo viên', N'TOEFL');
INSERT INTO HOCVIEN VALUES ('HV006', N'Trần Thị Mai', TO_DATE('11-12-1998','DD-MM-YYYY'), 'F', '0901234567', 'mai.tran98@hotmail.com', N'Hải Phòng', 'B1', N'Nhân viên', N'Giao tiếp');
INSERT INTO HOCVIEN VALUES ('HV007', N'Trần Quốc Hùng', TO_DATE('12-09-1999','DD-MM-YYYY'), 'M', '0935123456', 'hung.tran99@gmail.com', N'Đà Nẵng', 'B2', N'Kỹ sư', N'Thi TOEIC');
INSERT INTO HOCVIEN VALUES ('HV008', N'Phạm Thị Mai Linh', TO_DATE('23-03-2001','DD-MM-YYYY'), 'F', '0906987452', 'linh.pham01@yahoo.com', N'Cần Thơ', 'A2', N'Sinh viên', N'Giao tiếp');
INSERT INTO HOCVIEN VALUES ('HV009', N'Nguyễn Văn Duy', TO_DATE('17-11-1997','DD-MM-YYYY'), 'M', '0987234561', 'duy.nguyen97@hotmail.com', N'Hà Nội', 'C1', N'Kinh doanh', N'Thi IELTS');
INSERT INTO HOCVIEN VALUES ('HV010', N'Lê Thị Ngọc Hân', TO_DATE('06-06-2002','DD-MM-YYYY'), 'F', '0912345670', 'han.le02@gmail.com', N'Hồ Chí Minh', 'B1', N'Giáo viên', N'TOEFL');

-- 4. PHONGHOC
INSERT INTO PHONGHOC VALUES ('PH001', N'Phòng A1', 30, N'Thường');
INSERT INTO PHONGHOC VALUES ('PH002', N'Phòng B2', 40, N'Máy chiếu');
INSERT INTO PHONGHOC VALUES ('PH003', N'Phòng A101', 40, N'Phòng học lý thuyết');
INSERT INTO PHONGHOC VALUES ('PH004', N'Phòng B202', 60, N'Phòng máy tính');
INSERT INTO PHONGHOC VALUES ('PH005', N'Phòng C303', 50, N'Phòng học nhóm');
INSERT INTO PHONGHOC VALUES ('PH006', N'Phòng D404', 80, N'Phòng hội thảo');

-- 5. LOP
INSERT INTO LOP VALUES ('LP001', 'TOEIC', TO_DATE('01-06-2025','DD-MM-YYYY'), TO_DATE('01-09-2025','DD-MM-YYYY'), N'T?i 2, 4, 6', '700+', 24, 20, 'GV001', 4000000);
INSERT INTO LOP VALUES ('LP002', 'IELTS', TO_DATE('15-06-2025','DD-MM-YYYY'), TO_DATE('15-09-2025','DD-MM-YYYY'), N'S�ng 3, 5, 7', '6.5', 30, 20, 'GV002', 8000000);
INSERT INTO LOP VALUES ('LP003', 'IELTS', TO_DATE('07-05-2025','DD-MM-YYYY'), TO_DATE('15-05-2025','DD-MM-YYYY'), N'S�ng 3, 5, 7', '5.5', 30, 20, 'GV002', 6000000);

-- 6. DangKyLop
INSERT INTO DangKyLop VALUES ('HV001', 'LP001', TO_DATE('25-05-2025','DD-MM-YYYY'), NULL, NULL, 3000000, 'Ti?n m?t');
INSERT INTO DangKyLop VALUES ('HV002', 'LP002', TO_DATE('28-05-2025','DD-MM-YYYY'), NULL, NULL, 5000000, 'Chuy?n kho?n');
INSERT INTO DangKyLop VALUES ('HV001', 'LP003', TO_DATE('05-05-2025','DD-MM-YYYY'), NULL, NULL, 3000000, 'Ti?n m?t');
INSERT INTO DangKyLop VALUES ('HV002', 'LP003', TO_DATE('04-05-2025','DD-MM-YYYY'), NULL, NULL, 5000000, 'Chuy?n kho?n');

-- 7. LICHHOC
INSERT INTO LICHHOC VALUES ('LH001', N'TH? 3', N'CA 1', 'PH001');
INSERT INTO LICHHOC VALUES ('LH002', N'TH? 4', N'CA 2', 'PH002');
INSERT INTO LICHHOC VALUES ('LH003', N'TH? 5', N'CA 4', 'PH001');
INSERT INTO LICHHOC VALUES ('LH004', N'TH? 6', N'CA 3', 'PH002');
INSERT INTO LICHHOC VALUES ('LH005', N'TH? 3', N'CA 1', 'PH002');
INSERT INTO LICHHOC VALUES ('LH006', N'TH? 5', N'CA 2', 'PH001');
INSERT INTO LICHHOC VALUES ('LH007', N'TH? 4', N'CA 3', 'PH002');
INSERT INTO LICHHOC VALUES ('LH008', N'TH? 7', N'CA 2', 'PH001');
INSERT INTO LICHHOC VALUES ('LH009', N'TH? 2', N'CA 3', 'PH002');

-- 8. DiemDanh
INSERT INTO DiemDanh VALUES ('HV001', 'LP001', 'LH001', TO_DATE('03-06-2025','DD-MM-YYYY'), 'C� m?t', NULL);
INSERT INTO DiemDanh VALUES ('HV002', 'LP002', 'LH002', TO_DATE('04-06-2025','DD-MM-YYYY'), 'V?ng', N'B?n vi?c');

-- 9. KHUYENMAI
INSERT INTO KHUYENMAI VALUES ('KM001', 'KMNHOM', 'Giam cho nhom hoc sinh', 'Uu dai nhom', '100000', TO_DATE('01-05-2025','DD-MM-YYYY'), TO_DATE('30-06-2025','DD-MM-YYYY'), 'NV001');
INSERT INTO KHUYENMAI VALUES ('KM002', 'KMNHOM', 'Giam cho nhom hoc sinh', 'Hoc mien phi cho hoc vien xuat sac', '100000', TO_DATE('01-05-2025','DD-MM-YYYY'), TO_DATE('30-06-2025','DD-MM-YYYY'), 'NV001');
INSERT INTO KHUYENMAI VALUES ('KM003', 'KMNHOM', 'Giam cho nhom hoc sinh', 'Hoc mien phi cho hoc vien xuat sac', '100000', TO_DATE('01-05-2025','DD-MM-YYYY'), TO_DATE('30-06-2025','DD-MM-YYYY'), 'NV001');

-- 10. SuDungKhuyenMai
INSERT INTO SUDUNGKHUYENMAI VALUES ('KM001', 'HV001', 'LP001', TO_DATE('25-05-2025','DD-MM-YYYY'));

-- 11. BAITAP
INSERT INTO BAITAP VALUES ('BT001', 'Viet doan van 100 tu', 'LP001', 10);
INSERT INTO BAITAP VALUES ('BT002', 'Grammar Bai 2', 'LP002', 10);

-- 12. GiaoBaiTap
INSERT INTO GiaoBaiTap VALUES ('GV001', 'LP001', 'BT001', TO_DATE('08-05-2025','DD-MM-YYYY'), TO_DATE('13-05-2025','DD-MM-YYYY'));
INSERT INTO GiaoBaiTap VALUES ('GV002', 'LP002', 'BT002', TO_DATE('07-05-2025','DD-MM-YYYY'), TO_DATE('12-05-2025','DD-MM-YYYY'));

-- 13. LamBaiTap
INSERT INTO LamBaiTap VALUES ('HV001', 'LP001', TO_DATE('11-06-2025','DD-MM-YYYY'), 'BT001');
INSERT INTO LamBaiTap VALUES ('HV002', 'LP002', TO_DATE('13-06-2025','DD-MM-YYYY'), 'BT001');

-- 14. LICHHENTUVAN
INSERT INTO LICHHENTUVAN VALUES ('LH001', 'NV001', 'HV001', TO_DATE('05-06-2025','DD-MM-YYYY'), N'S�ng', N'Tu v?n ch?n l?p', 'G?p r?i', NULL);
INSERT INTO LICHHENTUVAN VALUES ('LH002', 'NV002', 'HV002', TO_DATE('06-06-2025','DD-MM-YYYY'), N'Chi?u', N'Tu v?n h?c ph�', 'Chua g?p', NULL);

-- 15. THONGBAO
INSERT INTO THONGBAO VALUES ('TB001', 'Lich nghi le', 'Trung tam nghi 2/9', 'Thong bao nghi', TO_DATE('25-08-2025','DD-MM-YYYY'), 'NV001');
INSERT INTO THONGBAO VALUES ('TB002', 'Khai giang lop moi', 'Lop IELTS bat dau 15/6', 'Thong bao mo lop', TO_DATE('10-06-2025','DD-MM-YYYY'), 'NV002');

-- 16. PhanCongGiangDay
INSERT ALL
  INTO PhanCongGiangDay (MaGV, MaLop, MaLichHoc, NgayPhanCong, TinhTrang)
    VALUES ('GV001', 'LP001', 'LH001', TO_DATE('16-05-2025','DD-MM-YYYY'), 'Da phan cong')
  INTO PhanCongGiangDay (MaGV, MaLop, MaLichHoc, NgayPhanCong, TinhTrang)
    VALUES ('GV002', 'LP002', 'LH008', TO_DATE('18-06-2025','DD-MM-YYYY'), 'Cho xac nhan')
  INTO PhanCongGiangDay (MaGV, MaLop, MaLichHoc, NgayPhanCong, TinhTrang)
    VALUES ('GV001', 'LP001', 'LH002', TO_DATE('20-05-2025','DD-MM-YYYY'), 'Da phan cong')
  INTO PhanCongGiangDay (MaGV, MaLop, MaLichHoc, NgayPhanCong, TinhTrang)
    VALUES ('GV002', 'LP002', 'LH006', TO_DATE('22-05-2025','DD-MM-YYYY'), 'Huy phan cong')
  INTO PhanCongGiangDay (MaGV, MaLop, MaLichHoc, NgayPhanCong, TinhTrang)
    VALUES ('GV001', 'LP001', 'LH003', TO_DATE('24-05-2025','DD-MM-YYYY'), 'Da phan cong')
  INTO PhanCongGiangDay (MaGV, MaLop, MaLichHoc, NgayPhanCong, TinhTrang)
    VALUES ('GV002', 'LP002', 'LH004', TO_DATE('26-05-2025','DD-MM-YYYY'), 'Cho xac nhan')
SELECT * FROM dual;

-- 17. THIETBI
INSERT INTO THIETBI VALUES ('TB001', 'May chieu Epson', 'Hoat dong tot', 'PH001');
INSERT INTO THIETBI VALUES ('TB002', 'Micro khong day', 'Can bao tri', 'PH002');
INSERT INTO THIETBI VALUES ('TB003', 'Tivi Samsung 65"', 'Hoat dong tot', 'PH002');
INSERT INTO THIETBI VALUES ('TB004', 'Loa bluetooth', 'Da hong', 'PH001');
