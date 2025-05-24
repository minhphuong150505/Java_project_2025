

 --1: Nhập mã lớp in ra DS học sinh + số buổi vắng tương ứng
    CREATE OR REPLACE PROCEDURE pr_dsach_vang
    (
    	p_MaLop IN LOP.MaLop%TYPE
    )
    IS
      v_MaHV HOCVIEN.MaHV%TYPE;
      v_TenHV HOCVIEN.HOTEN%TYPE;
      v_count NUMBER := 0;
      CURSOR cursor_maHV IS
        SELECT DK.MAHV
        FROM DANGKYLOP DK
        WHERE DK.MALOP = p_MaLop;
    BEGIN
      OPEN cursor_maHV;
      FETCH cursor_maHV INTO v_MaHV;
      DBMS_OUTPUT.PUT_LINE('Danh sách học sinh và thông tin điểm danh của lớp ' || p_MaLop || ':');
      DBMS_OUTPUT.PUT_LINE('==============================================');

      WHILE cursor_maHV%FOUND LOOP
          v_count := v_count + 1;

          SELECT h.HOTEN INTO v_TenHV
          FROM HOCVIEN h
          WHERE h.MAHV = v_MaHV;
          
          DBMS_OUTPUT.PUT_LINE(v_count ||':'||'Số buổi vắng của học viên ' || v_TenHV || ' là ' || TINH_SO_BUOI_VANG(v_MaHV));
          FETCH cursor_maHV INTO v_MaHV;
      END LOOP;
      DBMS_OUTPUT.PUT_LINE('==============================================');
      CLOSE cursor_maHV;
    EXCEPTION
         WHEN NO_DATA_FOUND THEN
          DBMS_OUTPUT.PUT_LINE('Không tìm thấy lớp với mã: ' || p_MaLop);
    END;
--DEMO:
   BEGIN
    pr_dsach_vang('LP003');
   END;

--2.Nhập mã lớp, hiển thị slot còn trống(kèm ràng buộc lớp phải nằm trong khoảng thời gian được đăng kí) (Phương)
  CREATE OR REPLACE PROCEDURE dsach_Lop (
    p_maLop in LOP.MALOP%TYPE
  )
  IS
    v_maHV HOCVIEN.MAHV%TYPE;
    v_count NUMBER := 1;
    v_tenHV HOCVIEN.HOTEN%TYPE;
    v_siSo NUMBER;
    v_ngayBD DATE;
    v_ngayKT DATE;
  
    CURSOR cs_dsach IS
      SELECT d.MAHV
      FROM LOP L JOIN DANGKYLOP d ON L.MALOP = d.MALOP
      WHERE L.MALOP = p_maLop;
  
  BEGIN
    -- Kiểm tra lớp có tồn tại và nằm trong thời gian được phép đăng ký không
    SELECT TGBATDAU,TGKETTHUC, SISO INTO v_ngayBD, v_ngayKT, v_siSo
    FROM LOP
    WHERE MALOP = p_maLop;
  
    IF SYSDATE NOT BETWEEN v_ngayBD AND v_ngayKT THEN
      DBMS_OUTPUT.PUT_LINE('Lớp ' || p_maLop || ' không nằm trong thời gian đăng ký!');
      RETURN;
    END IF;
  
    DBMS_OUTPUT.PUT_LINE('DANH SÁCH HỌC VIÊN CỦA LỚP ' || p_maLop || ':');
    DBMS_OUTPUT.PUT_LINE('==============================================');
  
    OPEN cs_dsach;
    LOOP
      FETCH cs_dsach INTO v_maHV;
      EXIT WHEN cs_dsach%NOTFOUND;
  
      -- Lấy tên học viên
      SELECT HOTEN INTO v_tenHV
      FROM HOCVIEN
      WHERE MAHV = v_maHV;
  
      DBMS_OUTPUT.PUT_LINE(v_count || '. ' || v_maHV || ' - ' || v_tenHV);
      v_count := v_count + 1;
    END LOOP;
    CLOSE cs_dsach;
  
    DBMS_OUTPUT.PUT_LINE('==============================================');
    DBMS_OUTPUT.PUT_LINE('Số slot còn trống của lớp ' || p_maLop || ' là: ' || (v_siSo - v_count + 1));
  
  EXCEPTION
    WHEN NO_DATA_FOUND THEN
      DBMS_OUTPUT.PUT_LINE('Không tìm thấy lớp với mã: ' || p_maLop);
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Lỗi: ' || SQLERRM);
  END;
--Demo 
BEGIN
   DSACH_LOP('LP003');
END;

--3. in ra tất cả phòng học và tất cả thiết bị trong đó và tình trạng
    CREATE OR REPLACE PROCEDURE PR_DSACH_THIETBI IS
      V_MATB        THIETBI.MATB%TYPE;
      V_TENTB       THIETBI.TENTB%TYPE;
      V_TINHTRANG   THIETBI.TINHTRANG%TYPE;
      V_MAPHONG_TB  THIETBI.MAPHONG%TYPE;
      V_MAPHONG     PHONGHOC.MAPHONG%TYPE;
    
      CURSOR CS_PH IS
        SELECT MAPHONG FROM PHONGHOC;
    
      CURSOR CS_TB IS
        SELECT MATB, TENTB, TINHTRANG, MAPHONG FROM THIETBI;
    
    BEGIN
      OPEN CS_PH;
      LOOP
        FETCH CS_PH INTO V_MAPHONG;
        EXIT WHEN CS_PH%NOTFOUND;
    
        DBMS_OUTPUT.PUT_LINE('DANH SÁCH THIẾT BỊ CỦA PHÒNG HỌC ' || V_MAPHONG || ':');
        DBMS_OUTPUT.PUT_LINE('==============================================');
    
        OPEN CS_TB;
        LOOP
          FETCH CS_TB INTO V_MATB, V_TENTB, V_TINHTRANG, V_MAPHONG_TB;
          EXIT WHEN CS_TB%NOTFOUND;
    
          IF V_MAPHONG_TB = V_MAPHONG THEN
            DBMS_OUTPUT.PUT_LINE('Thiết bị ' || V_TENTB || '      Tình trạng: ' || V_TINHTRANG);
          END IF;
        END LOOP;
        CLOSE CS_TB;
    
        DBMS_OUTPUT.PUT_LINE('==============================================');
      END LOOP;
      CLOSE CS_PH;
    END;

--DEMO
 BEGIN
  PR_DSACH_THIETBI();
 END;

--4. Nhập vào mã học viên in ra bài tập cần làm trong thời gian tới
    CREATE OR REPLACE PROCEDURE PR_DSACH_BAITAP_HOCVIEN (
      P_MAHV IN HOCVIEN.MAHV%TYPE
    )
    IS
      V_MALOP LOP.MALOP%TYPE;
      V_MABT BAITAP.MABT%TYPE;
      V_MALOP_BT LOP.MALOP%TYPE;
      V_DEADLINE DATE;
      V_COUNT NUMBER;
    
      CURSOR CS_MALOP IS
        SELECT DISTINCT D.MALOP
        FROM DANGKYLOP D
        WHERE D.MAHV = P_MAHV;
    
      CURSOR CS_MABT IS
        SELECT G.MABAITAP, G.MALOP, G.DEADLINE
        FROM GIAOBAITAP G;
    BEGIN
      OPEN CS_MALOP;
      DBMS_OUTPUT.PUT_LINE('DANH SÁCH BÀI TẬP SẮP TỚI CỦA HỌC VIÊN ' || P_MAHV || ':');
      DBMS_OUTPUT.PUT_LINE('==============================================');
    
      LOOP
        FETCH CS_MALOP INTO V_MALOP;
        EXIT WHEN CS_MALOP%NOTFOUND;
    
        DBMS_OUTPUT.PUT_LINE('>> LỚP: ' || V_MALOP);
        V_COUNT := 0;
    
        OPEN CS_MABT;
        LOOP
          FETCH CS_MABT INTO V_MABT, V_MALOP_BT, V_DEADLINE;
          EXIT WHEN CS_MABT%NOTFOUND;
    
          IF V_MALOP_BT = V_MALOP AND V_DEADLINE >= SYSDATE THEN
            V_COUNT := V_COUNT + 1;
            DBMS_OUTPUT.PUT_LINE('  ' || V_COUNT || '. MÃ BÀI TẬP: ' || V_MABT || ', HẠN NỘP: ' || TO_CHAR(V_DEADLINE, 'DD-MM-YYYY'));
          END IF;
        END LOOP;
        CLOSE CS_MABT;
      END LOOP;
    
      CLOSE CS_MALOP;
      DBMS_OUTPUT.PUT_LINE('==============================================');
    END;
/
--DEMO
  BEGIN
     PR_DSACH_BAITAP_HOCVIEN('HV002');
  END;


--5.Thêm một học viên mới

CREATE OR REPLACE PROCEDURE them_hocvien (
    p_maHV       IN HOCVIEN.MAHV%TYPE,
    p_hoTen      IN HOCVIEN.HOTEN%TYPE,
    p_ngaySinh   IN HOCVIEN.NGAYSINH%TYPE,
    p_gioiTinh   IN HOCVIEN.GIOITINH%TYPE,
    p_sdt        IN HOCVIEN.SDT%TYPE,
    p_email      IN HOCVIEN.EMAIL%TYPE,
    p_diaChi     IN HOCVIEN.DIACHI%TYPE,
    p_trinhDo    IN HOCVIEN.TRINHDO%TYPE,
    p_ngheNghiep IN HOCVIEN.NGHENGHIEP%TYPE,
    p_mucTieu    IN HOCVIEN.MUCTIEU%TYPE
)
IS
BEGIN
    INSERT INTO HOCVIEN (
        MAHV, HOTEN, NGAYSINH, GIOITINH, SDT,
        EMAIL, DIACHI, TRINHDO, NGHENGHIEP, MUCTIEU
    )
    VALUES (
        p_maHV, p_hoTen, p_ngaySinh, p_gioiTinh, p_sdt,
        p_email, p_diaChi, p_trinhDo, p_ngheNghiep, p_mucTieu
    );

    DBMS_OUTPUT.PUT_LINE('Thêm học viên thành công: ' || p_maHV);
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Lỗi: ' || SQLERRM);
END;

--5.Thêm một lớp mới
CREATE OR REPLACE PROCEDURE them_lop (
    p_maLop     IN LOP.MALOP%TYPE,
    p_tenLop    IN LOP.TENLOP%TYPE,
    p_tgBD      IN LOP.TGBATDAU%TYPE,
    p_tgKT      IN LOP.TGKETTHUC%TYPE,
    p_tgHoc     IN LOP.TGHOC%TYPE,
    p_soBuoi    IN LOP.SOBUOIHOC%TYPE,
    p_siSo      IN LOP.SISO%TYPE,
    p_maGV      IN LOP.MAGV%TYPE,
    p_hocPhi    IN LOP.HOCPHI%TYPE,
    p_band      IN LOP.BAND%TYPE
)
IS
BEGIN
    INSERT INTO LOP (
        MALOP, TENLOP, TGBATDAU, TGKETTHUC, TGHOC,
        SOBUOIHOC, SISO, MAGV, HOCPHI, BAND
    ) VALUES (
        p_maLop, p_tenLop, p_tgBD, p_tgKT, p_tgHoc,
        p_soBuoi, p_siSo, p_maGV, p_hocPhi, p_band
    );

    DBMS_OUTPUT.PUT_LINE('Đã thêm lớp ' || p_maLop || ' thành công.');
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Lỗi: ' || SQLERRM);
END;
/
--6 procedu để thêm một học viên nếu trùng thì sửa lại thông tin cho giống thông tin
CREATE OR REPLACE PROCEDURE PROC_INSERT_OR_UPDATE_HOCVIEN (
    p_mahv        IN HOCVIEN.MAHV%TYPE,
    p_hoten       IN HOCVIEN.HOTEN%TYPE,
    p_ngaysinh    IN HOCVIEN.NGAYSINH%TYPE,
    p_gioitinh    IN HOCVIEN.GIOITINH%TYPE,
    p_sdt         IN HOCVIEN.SDT%TYPE,
    p_email       IN HOCVIEN.EMAIL%TYPE,
    p_diachi      IN HOCVIEN.DIACHI%TYPE,
    p_trinhdo     IN HOCVIEN.TRINHDO%TYPE,
    p_nghenghiep  IN HOCVIEN.NGHENGHIEP%TYPE,
    p_muctieu     IN HOCVIEN.MUCTIEU%TYPE
) AS
BEGIN
    MERGE INTO HOCVIEN hv
    USING (
        SELECT p_mahv AS MAHV FROM dual
    ) input
    ON (hv.MAHV = input.MAHV)
    WHEN MATCHED THEN
        UPDATE SET
            hv.HOTEN       = p_hoten,
            hv.NGAYSINH    = p_ngaysinh,
            hv.GIOITINH    = p_gioitinh,
            hv.SDT         = p_sdt,
            hv.EMAIL       = p_email,
            hv.DIACHI      = p_diachi,
            hv.TRINHDO     = p_trinhdo,
            hv.NGHENGHIEP  = p_nghenghiep,
            hv.MUCTIEU     = p_muctieu
    WHEN NOT MATCHED THEN
        INSERT (MAHV, HOTEN, NGAYSINH, GIOITINH, SDT, EMAIL, DIACHI, TRINHDO, NGHENGHIEP, MUCTIEU)
        VALUES (p_mahv, p_hoten, p_ngaysinh, p_gioitinh, p_sdt, p_email, p_diachi, p_trinhdo, p_nghenghiep, p_muctieu);
END;
/
BEGIN
  PROC_INSERT_OR_UPDATE_HOCVIEN(
    'HV001', 'Nguyen Van A', TO_DATE('2000-01-01','YYYY-MM-DD'), 'M', 
    '0123456789', 'a@gmail.com', 'Hà Nội', 'ĐH', 'Sinh viên', 'Lập trình'
  );
END;
/
SELECT * FROM HOCVIEN h

--7. Xóa hoc viên
CREATE OR REPLACE PROCEDURE PROC_DELETE_HOCVIEN
(p_mahv HOCVIEN.MAHV%TYPE)
IS
BEGIN
    DELETE FROM HocVien
    WHERE MaHV = p_mahv;
END;

--8: thêm hoặc sửa nhân viên
CREATE OR REPLACE PROCEDURE PROC_INSERT_OR_UPDATE_NHANVIEN (
    p_manv        IN NHANVIEN.MANV%TYPE,
    p_hoten       IN NHANVIEN.HOTEN%TYPE,
    p_ngaysinh    IN NHANVIEN.NGAYSINH%TYPE,
    p_gioitinh    IN NHANVIEN.GIOITINH%TYPE,
    p_cmnd        IN NHANVIEN.CMND%TYPE,
    p_sdt         IN NHANVIEN.SDT%TYPE,
    p_email       IN NHANVIEN.EMAIL%TYPE,
    p_diachi      IN NHANVIEN.DIACHI%TYPE,
    p_kinhnghiem  IN NHANVIEN.KINHNGHIEM%TYPE,
    p_vitri       IN NHANVIEN.VITRI%TYPE,
    p_ngayvaolam  IN NHANVIEN.NGAYVAOLAM%TYPE,
    p_luongcoban  IN NHANVIEN.LUONGCOBAN%TYPE
) AS
BEGIN
    MERGE INTO NHANVIEN nv
    USING (SELECT p_manv AS MANV FROM dual) input
    ON (nv.MANV = input.MANV)
    WHEN MATCHED THEN
        UPDATE SET
            nv.HOTEN       = p_hoten,
            nv.NGAYSINH    = p_ngaysinh,
            nv.GIOITINH    = p_gioitinh,
            nv.CMND        = p_cmnd,
            nv.SDT         = p_sdt,
            nv.EMAIL       = p_email,
            nv.DIACHI      = p_diachi,
            nv.KINHNGHIEM  = p_kinhnghiem,
            nv.VITRI       = p_vitri,
            nv.NGAYVAOLAM  = p_ngayvaolam,
            nv.LUONGCOBAN  = p_luongcoban
    WHEN NOT MATCHED THEN
        INSERT (
            MANV, HOTEN, NGAYSINH, GIOITINH, CMND, SDT, EMAIL, DIACHI,
            KINHNGHIEM, VITRI, NGAYVAOLAM, LUONGCOBAN
        )
        VALUES (
            p_manv, p_hoten, p_ngaysinh, p_gioitinh, p_cmnd, p_sdt, p_email, p_diachi,
            p_kinhnghiem, p_vitri, p_ngayvaolam, p_luongcoban
        );
END;
/
--9. xóa nhân viên
CREATE OR REPLACE PROCEDURE PROC_DELETE_NHANVIEN (
    p_manv IN NHANVIEN.MANV%TYPE
) AS
BEGIN
    DELETE FROM NHANVIEN
    WHERE MANV = p_manv;
END;
/
BEGIN
  PROC_INSERT_OR_UPDATE_NHANVIEN(
    'NV001', 'Nguyen Van B', TO_DATE('1990-05-20', 'YYYY-MM-DD'), 'M',
    '123456789012', '0987654321', 'nvb@gmail.com', 'Hồ Chí Minh',
    '5 năm', 'Giảng viên', TO_DATE('2020-06-01', 'YYYY-MM-DD'), 12000000
  );
END;
/
SELECT * FROM NHANVIEN

--10: thêm hoặc sửa giáo viên
CREATE OR REPLACE PROCEDURE PROC_INSERT_OR_UPDATE_GIAOVIEN (
    p_magv         IN GIAOVIEN.MAGV%TYPE,
    p_hoten        IN GIAOVIEN.HOTEN%TYPE,
    p_ngaysinh     IN GIAOVIEN.NGAYSINH%TYPE,
    p_gioitinh     IN GIAOVIEN.GIOITINH%TYPE,
    p_cmnd         IN GIAOVIEN.CMND%TYPE,
    p_sdt          IN GIAOVIEN.SDT%TYPE,
    p_email        IN GIAOVIEN.EMAIL%TYPE,
    p_diachi       IN GIAOVIEN.DIACHI%TYPE,
    p_trinhdo      IN GIAOVIEN.TRINHDO%TYPE,
    p_kinhnghiem   IN GIAOVIEN.KINHNGHIEM%TYPE,
    p_ngayvaolam   IN GIAOVIEN.NGAYVAOLAM%TYPE,
    p_luongcoban   IN GIAOVIEN.LUONGCOBAN%TYPE
)
AS
BEGIN
    MERGE INTO GIAOVIEN gv
    USING (
        SELECT p_magv AS MAGV FROM dual
    ) input
    ON (gv.MAGV = input.MAGV)
    WHEN MATCHED THEN
        UPDATE SET
            gv.HOTEN        = p_hoten,
            gv.NGAYSINH     = p_ngaysinh,
            gv.GIOITINH     = p_gioitinh,
            gv.CMND         = p_cmnd,
            gv.SDT          = p_sdt,
            gv.EMAIL        = p_email,
            gv.DIACHI       = p_diachi,
            gv.TRINHDO      = p_trinhdo,
            gv.KINHNGHIEM   = p_kinhnghiem,
            gv.NGAYVAOLAM   = p_ngayvaolam,
            gv.LUONGCOBAN   = p_luongcoban
    WHEN NOT MATCHED THEN
        INSERT (
            MAGV, HOTEN, NGAYSINH, GIOITINH, CMND, SDT, EMAIL, DIACHI, TRINHDO, KINHNGHIEM, NGAYVAOLAM, LUONGCOBAN
        )
        VALUES (
            p_magv, p_hoten, p_ngaysinh, p_gioitinh, p_cmnd, p_sdt, p_email, p_diachi, p_trinhdo, p_kinhnghiem, p_ngayvaolam, p_luongcoban
        );
END;
/

--11. xóa giáo viên

CREATE OR REPLACE PROCEDURE PROC_DELETE_GIAOVIEN (
    p_magv IN GIAOVIEN.MAGV%TYPE
)
AS
BEGIN
    DELETE FROM GIAOVIEN
    WHERE MAGV = p_magv;
END;
/
BEGIN
  PROC_INSERT_OR_UPDATE_GIAOVIEN(
    'GV001', 'Nguyen Thi B', TO_DATE('1980-05-15', 'YYYY-MM-DD'), 'F',
    '123456789014', '0912345678', 'c@gmail.com', 'Hồ Chí Minh',
    'Thạc sĩ', '10 năm', TO_DATE('2010-09-01', 'YYYY-MM-DD'), 15000000
  );
END;
/
SELECT * FROM GIAOVIEN g

--12. Thêm hoặc sửa Lớp
CREATE OR REPLACE PROCEDURE PROC_INSERT_OR_UPDATE_LOP (
    p_malop      IN LOP.MALOP%TYPE,
    p_tenlop     IN LOP.TENLOP%TYPE,
    p_tgbatdau   IN LOP.TGBATDAU%TYPE,
    p_tgketthuc  IN LOP.TGKETTHUC%TYPE,
    p_tghoc      IN LOP.TGHOC%TYPE,
    p_sobuoi     IN LOP.SOBUOIHOC%TYPE,
    p_siso       IN LOP.SISO%TYPE,
    p_magv       IN LOP.MAGV%TYPE,
    p_hocphi     IN LOP.HOCPHI%TYPE,
    p_band       IN LOP.BAND%TYPE
) AS
BEGIN
    MERGE INTO LOP l
    USING (SELECT p_malop AS MALOP FROM dual) input
    ON (l.MALOP = input.MALOP)
    WHEN MATCHED THEN
        UPDATE SET
            l.TENLOP     = p_tenlop,
            l.TGBATDAU   = p_tgbatdau,
            l.TGKETTHUC  = p_tgketthuc,
            l.TGHOC      = p_tghoc,
            l.SOBUOIHOC  = p_sobuoi,
            l.SISO       = p_siso,
            l.MAGV       = p_magv,
            l.HOCPHI     = p_hocphi,
            l.BAND       = p_band
    WHEN NOT MATCHED THEN
        INSERT (
            MALOP, TENLOP, TGBATDAU, TGKETTHUC, TGHOC, SOBUOIHOC, SISO, MAGV, HOCPHI, BAND
        )
        VALUES (
            p_malop, p_tenlop, p_tgbatdau, p_tgketthuc, p_tghoc, p_sobuoi, p_siso, p_magv, p_hocphi, p_band
        );
END;
/
--13. Xóa Lớp
CREATE OR REPLACE PROCEDURE PROC_DELETE_LOP (
    p_malop IN LOP.MALOP%TYPE
) AS
BEGIN
    DELETE FROM LOP
    WHERE MALOP = p_malop;
END;
/
BEGIN
  PROC_INSERT_OR_UPDATE_LOP(
    'L001', 'IELTS', TO_DATE('2024-06-01', 'YYYY-MM-DD'), TO_DATE('2024-08-31', 'YYYY-MM-DD'),
    'Thứ 2-4-6 18:00-20:00', 24, 20, 'GV001', 8000000,'6.0'
  );
END;
/

SELECT * FROM LOP;

BEGIN
  PROC_DELETE_LOP('L001');
END;
/
