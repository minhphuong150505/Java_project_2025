--1. Ràng buộc sỉ số cho 1 lớp (compound)  
--4.3 Số lượng học viên tối đa của từng lớp: 
--Cơ bản: 30 học viên/lớp. 
--TOEIC: 
--	Band 500+ và 600+: 30 học viên/lớp.
 --	Band 700+ và 800+: 20 học viên/lớp. 
--	Band 900+: 10 học viên/lớp. 
-- IELTS: 
--	Band 4.0+ và 5.0+: 30 học viên/lớp.
 --	Band 6.0+ và 7.0+: 20 học viên/lớp. 
--	Band 8.0+: 10 học viên/lớp.
    CREATE OR REPLACE TRIGGER sohocvien_toida
    BEFORE INSERT OR UPDATE ON LOP
    FOR EACH ROW
    DECLARE
      v_max_hocvien INT;
    BEGIN
      IF :NEW.TENLOP = 'Cơ bản' THEN
        v_max_hocvien := 30;
    
      ELSIF :NEW.TENLOP = 'TOEIC' THEN
        IF :NEW.BAND IN ('500+', '600+') THEN
          v_max_hocvien := 30;
        ELSIF :NEW.BAND IN ('700+', '800+') THEN
          v_max_hocvien := 20;
        ELSIF :NEW.BAND = '900+' THEN
          v_max_hocvien := 10;
        ELSE
          RAISE_APPLICATION_ERROR(-20018, 'BAND TOEIC không hợp lệ');
        END IF;
    
      ELSIF :NEW.TENLOP = 'IELTS' THEN
        IF :NEW.BAND IN ('4.0', '4.5', '5.0', '5.5') THEN
          v_max_hocvien := 30;
        ELSIF :NEW.BAND IN ('6.0', '6.5', '7.0', '7.5') THEN
          v_max_hocvien := 20;
        ELSIF :NEW.BAND = '8.0' THEN
          v_max_hocvien := 10;
        ELSE
          RAISE_APPLICATION_ERROR(-20018, 'BAND IELTS không hợp lệ');
        END IF;
    
      ELSE
        RAISE_APPLICATION_ERROR(-20018, 'Tên lớp không hợp lệ');
      END IF;
    
      -- Kiểm tra số lượng học viên vượt quá mức quy định
      IF :NEW.SISO > v_max_hocvien THEN
        RAISE_APPLICATION_ERROR(-20018, 'Số lượng học viên vượt mức cho phép: ' || :NEW.SISO || ', tối đa: ' || v_max_hocvien);
      END IF;
    END;
/
/*2. Học phí và thanh toán
Thực hiện ràng buộc toàn vẹn cho học phí từng khóa học như sau:
 Cơ bản: 3 triệu VNĐ.

 o TOEIC:


Band 500-699: 3 triệu VNĐ.
Band 700-899: 4 triệu VNĐ.
Band 900+: 5 triệu VNĐ.
o IELTS:


Band 4.0-5.5: 6 triệu VNĐ.
Band 6.0-7.5: 8 triệu VNĐ.
Band 8.0+: 10 triệu VNĐ.*/
    CREATE OR REPLACE TRIGGER TRG_RANG_BUOC_HOCPHI
    BEFORE INSERT OR UPDATE ON LOP
    FOR EACH ROW
    BEGIN
        -- Kiểm tra học phí cho lớp Cơ bản
        IF :NEW.TenLop = 'Cơ bản' THEN
            IF :NEW.HocPhi <> 3000000 THEN
                RAISE_APPLICATION_ERROR(-20001, 'Học phí khóa Cơ bản phải là 3,000,000 VNĐ.');
            END IF;
    
        -- Kiểm tra học phí cho lớp TOEIC
        ELSIF :NEW.TenLop = 'TOEIC' THEN
            IF :NEW.BAND IN ('500+', '600+') THEN
                IF :NEW.HocPhi <> 3000000 THEN
                    RAISE_APPLICATION_ERROR(-20002, 'Học phí khóa TOEIC 500-699 phải là 3,000,000 VNĐ.');
                END IF;
            ELSIF :NEW.BAND IN ('700+', '800+') THEN
                IF :NEW.HocPhi <> 4000000 THEN
                    RAISE_APPLICATION_ERROR(-20003, 'Học phí khóa TOEIC 700-899 phải là 4,000,000 VNĐ.');
                END IF;
            ELSIF :NEW.BAND = '900+' THEN
                IF :NEW.HocPhi <> 5000000 THEN
                    RAISE_APPLICATION_ERROR(-20004, 'Học phí khóa TOEIC 900+ phải là 5,000,000 VNĐ.');
                END IF;
            ELSE
                RAISE_APPLICATION_ERROR(-20005, 'Band khóa TOEIC không hợp lệ. Phải là 500-699, 700-899 hoặc 900+.');
            END IF;
    
        -- Kiểm tra học phí cho lớp IELTS
        ELSIF :NEW.TenLop = 'IELTS' THEN
            IF :NEW.BAND IN ('4.0', '4.5', '5.0', '5.5') THEN
                IF :NEW.HocPhi <> 6000000 THEN
                    RAISE_APPLICATION_ERROR(-20006, 'Học phí khóa IELTS 4.0-5.5 phải là 6,000,000 VNĐ.');
                END IF;
            ELSIF :NEW.BAND IN ('6.0', '6.5', '7.0', '7.5') THEN
                IF :NEW.HocPhi <> 8000000 THEN
                    RAISE_APPLICATION_ERROR(-20007, 'Học phí khóa IELTS 6.0-7.5 phải là 8,000,000 VNĐ.');
                END IF;
            ELSIF :NEW.Band = '8.0' THEN
                IF :NEW.HocPhi <> 10000000 THEN
                    RAISE_APPLICATION_ERROR(-20008, 'Học phí khóa IELTS 8.0+ phải là 10,000,000 VNĐ.');
                END IF;
            ELSE
                RAISE_APPLICATION_ERROR(-20009, 'Band khóa IELTS không hợp lệ. Phải là 4.0-5.5, 6.0-7.5 hoặc 8.0+.');
            END IF;
    
        -- Tên lớp không hợp lệ
        ELSE
            RAISE_APPLICATION_ERROR(-20010, 'Tên lớp không hợp lệ. Phải là Cơ bản, TOEIC hoặc IELTS.');
        END IF;
    END;
/
--3. Lớp Cơ bản:  tối thiểu 2 buổi/tuần, tối đa 3 buổi/tuần. 
--Lớp TOEIC & IELTS: thiểu 3 buổi/tuần, tối đa 5 buổi/tuần.
 -- Viết lại
    CREATE OR REPLACE TRIGGER sobuoihoc
    BEFORE INSERT OR DELETE ON PHANCONGGIANGDAY
    FOR EACH ROW
    DECLARE
        v_min NUMBER;
        v_max NUMBER;
        v_sobuoiphancong NUMBER;
        v_tenlop LOP.TENLOP%TYPE;
    BEGIN
        IF INSERTING THEN
            SELECT COUNT(DISTINCT MALICHHOC) + 1
            INTO v_sobuoiphancong
            FROM PHANCONGGIANGDAY
            WHERE MALOP = :NEW.MALOP;
    
            SELECT TENLOP INTO v_tenlop
            FROM LOP
            WHERE MALOP = :NEW.MALOP;
    
        ELSIF DELETING THEN
            SELECT COUNT(DISTINCT MALICHHOC) - 1
            INTO v_sobuoiphancong
            FROM PHANCONGGIANGDAY
            WHERE MALOP = :OLD.MALOP;
    
            SELECT TENLOP INTO v_tenlop
            FROM LOP
            WHERE MALOP = :OLD.MALOP;
        END IF;
    
        -- Thiết lập giới hạn số buổi học tùy theo loại lớp
        IF v_tenlop = 'Cơ bản' THEN
            v_min := 2;
            v_max := 3;
        ELSIF v_tenlop IN ('IELTS', 'TOEIC') THEN
            v_min := 3;
            v_max := 5;
        ELSE
            RETURN;
        END IF;
    
        -- Kiểm tra điều kiện số buổi học
        IF v_sobuoiphancong < v_min OR v_sobuoiphancong > v_max THEN
            RAISE_APPLICATION_ERROR(-20017, 'Số buổi học không hợp lệ: ' || v_sobuoiphancong);
        END IF;
    END;
/
CREATE OR REPLACE TRIGGER sobuoihoc
FOR INSERT OR DELETE ON PHANCONGGIANGDAY
COMPOUND TRIGGER

    TYPE T_LopIDSet IS TABLE OF VARCHAR2(20) INDEX BY PLS_INTEGER;
    v_malop_set T_LopIDSet;
    v_index     PLS_INTEGER := 0;

    BEFORE STATEMENT IS
    BEGIN
        -- Reset danh sách các lớp bị ảnh hưởng
        v_malop_set.DELETE;
        v_index := 0;
    END BEFORE STATEMENT;

    AFTER EACH ROW IS
    BEGIN
        IF INSERTING THEN
            v_index := v_index + 1;
            v_malop_set(v_index) := :NEW.MALOP;
        ELSIF DELETING THEN
            v_index := v_index + 1;
            v_malop_set(v_index) := :OLD.MALOP;
        END IF;
    END AFTER EACH ROW;

    AFTER STATEMENT IS
        v_sobuoiphancong NUMBER;
        v_min NUMBER;
        v_max NUMBER;
        v_tenlop LOP.TENLOP%TYPE;
    BEGIN
        FOR i IN v_malop_set.FIRST .. v_malop_set.LAST LOOP
            IF v_malop_set.EXISTS(i) THEN
                BEGIN
                    -- Lấy tên lớp
                    SELECT TENLOP INTO v_tenlop
                    FROM LOP
                    WHERE MALOP = v_malop_set(i);

                    -- Đếm số buổi đã phân công
                    SELECT COUNT(DISTINCT MALICHHOC)
                    INTO v_sobuoiphancong
                    FROM PHANCONGGIANGDAY
                    WHERE MALOP = v_malop_set(i);

                    -- Thiết lập giới hạn số buổi học
                    IF v_tenlop = 'Cơ bản' THEN
                        v_min := 2;
                        v_max := 3;
                    ELSIF v_tenlop IN ('IELTS', 'TOEIC') THEN
                        v_min := 3;
                        v_max := 5;
                    ELSE
                        CONTINUE;
                    END IF;

                    -- Kiểm tra giới hạn
                    IF v_sobuoiphancong < v_min OR v_sobuoiphancong > v_max THEN
                        RAISE_APPLICATION_ERROR(
                            -20017,
                            'Số buổi học không hợp lệ cho lớp ' || v_malop_set(i) ||
                            ': ' || v_sobuoiphancong
                        );
                    END IF;
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                        NULL; -- Không tìm thấy lớp thì bỏ qua
                END;
            END IF;
        END LOOP;
    END AFTER STATEMENT;

END sobuoihoc;
/
--4. Học sinh chỉ được điểm danh lớp học mà học viên đã đăng ký học
    CREATE OR REPLACE TRIGGER tg_diemdanh
    BEFORE INSERT ON DIEMDANH
    FOR EACH ROW
    DECLARE
      v_count NUMBER;
    BEGIN
      SELECT COUNT(*) INTO v_count
      FROM DANGKYLOP d
      WHERE d.MAHV = :NEW.MAHV AND d.MALOP = :NEW.MALOP;
    
      IF v_count = 0 THEN
        RAISE_APPLICATION_ERROR(-20000, 'Học viên không được điểm danh lớp mà mình không đăng ký');
      END IF;
    END;
/
--5.Học sinh chỉ được nộp bài tập  mà học viên đã đăng ký học
    CREATE OR REPLACE TRIGGER tg_lambaitap
    BEFORE INSERT ON LAMBAITAP
    FOR EACH ROW
    DECLARE
      v_exists NUMBER;
    BEGIN
      SELECT COUNT(*) INTO v_exists
      FROM DANGKYLOP d
      WHERE d.MAHV = :NEW.MAHV AND d.MALOP = :NEW.MALOP;
    
      IF v_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Học viên không được nộp bài tập cho lớp mà mình không đăng ký');
      END IF;
    END;
/
--6. Loại khuyến mãi chỉ có thể thuộc một trong các loại sau: 
--Ưu đãi nhóm, Học miễn phí cho học viên xuất sắc, Ngày Vàng Giảm Giá

    CREATE OR REPLACE TRIGGER TG_LOAI_KHUYENMAI 
    BEFORE INSERT OR UPDATE ON KHUYENMAI 
    FOR EACH ROW 
    DECLARE
     loai_km VARCHAR2(50); 
    BEGIN 
    loai_km := :NEW.LoaiKM; 
    IF loai_km NOT IN ('Ưu đãi nhóm', 'Học miễn phí cho học viên xuất sắc', 'Ngày Vàng Giảm Giá') THEN 
    RAISE_APPLICATION_ERROR(-20001, 'Loại khuyến mãi không hợp lệ. Chỉ chấp nhận: Ưu đãi nhóm, Học miễn phí cho học viên xuất sắc, Ngày Vàng Giảm Giá'); 
    END IF; 
    END;
/
--7. Số lượng đăng kí không được nhiều hơn sĩ số lớp
CREATE OR REPLACE TRIGGER tg_siso_lop
BEFORE INSERT ON DANGKYLOP
FOR EACH ROW
DECLARE
   v_so_dangky_hientai NUMBER;
   v_siSo NUMBER;
BEGIN
   SELECT COUNT(*) INTO v_so_dangky_hientai
   FROM DANGKYLOP
   WHERE MALOP = :NEW.MALOP;

   SELECT SISO INTO v_siSo
   FROM LOP
   WHERE MALOP = :NEW.MALOP;

   IF v_so_dangky_hientai + 1 > v_siSo THEN
      RAISE_APPLICATION_ERROR(-20001,'Không thể đăng ký vì lớp hiện tại đã hết chỗ');
   END IF;
END;
/

/
CREATE OR REPLACE TRIGGER tg_siso_lop
FOR INSERT ON DANGKYLOP
COMPOUND TRIGGER

   -- Tập hợp để lưu MALOP được insert trong cùng câu lệnh
   TYPE Malop_Set IS TABLE OF VARCHAR2(20) INDEX BY VARCHAR2(20);
   v_malop_set Malop_Set;

   -- Tổng số đăng ký tạm thời theo MALOP
   TYPE Malop_Count IS TABLE OF NUMBER INDEX BY VARCHAR2(20);
   v_dangky_moi Malop_Count;

BEFORE EACH ROW IS
BEGIN
   -- Ghi nhận MALOP đang insert
   v_malop_set(:NEW.MALOP) := :NEW.MALOP;

   -- Tăng số đăng ký mới theo MALOP
   IF v_dangky_moi.EXISTS(:NEW.MALOP) THEN
      v_dangky_moi(:NEW.MALOP) := v_dangky_moi(:NEW.MALOP) + 1;
   ELSE
      v_dangky_moi(:NEW.MALOP) := 1;
   END IF;
END BEFORE EACH ROW;

AFTER STATEMENT IS
   v_siso_lop     NUMBER;
   v_dangky_hientai NUMBER;
   l_malop_key       VARCHAR2(20);
BEGIN
  l_malop_key := v_malop_set.FIRST;
   WHILE l_malop_key IS NOT NULL LOOP
      -- Lấy sĩ số lớp
      SELECT SISO INTO v_siso_lop FROM LOP WHERE MALOP = l_malop_key;

      -- Đếm số học viên đã đăng ký lớp đó
      SELECT COUNT(*) INTO v_dangky_hientai FROM DANGKYLOP WHERE MALOP = l_malop_key;

      -- Kiểm tra vượt sĩ số
      IF v_dangky_hientai > v_siso_lop THEN
         RAISE_APPLICATION_ERROR(-20001,
            'Lớp ' || l_malop_key || ' đã đủ sĩ số (' || v_siso_lop || '). Không thể đăng ký thêm.');
      END IF;

      l_malop_key := v_malop_set.NEXT(l_malop_key);
   END LOOP;
END AFTER STATEMENT;
END ;

--8. Chỉ được sử dụng khuyễn mãi trong thời gian còn sử dụng được
CREATE OR REPLACE TRIGGER tg_sdkm
BEFORE INSERT ON SUDUNGKHUYENMAI
FOR EACH ROW
DECLARE
  v_date_bd DATE;
  v_date_kt DATE;
BEGIN

  SELECT NGAYBD, NGAYKT
  INTO v_date_bd, v_date_kt
  FROM KHUYENMAI
  WHERE MAKM = :NEW.MAKM;

  IF :NEW.NGAYSUDUNG < v_date_bd OR :NEW.NGAYSUDUNG > v_date_kt THEN
    RAISE_APPLICATION_ERROR(-20002, 'Khuyến mãi này chưa đến hạn sử dụng hoặc đã quá hạn sử dụng!');
  END IF;
END;
