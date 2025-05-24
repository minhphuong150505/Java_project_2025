--1. Tính số buổi vắng
    CREATE OR REPLACE FUNCTION TINH_SO_BUOI_VANG (
        MAHV_IN IN HOCVIEN.MaHV%TYPE
    ) 
    RETURN NUMBER 
    IS
        v_sobuoivang       NUMBER := 0;
        v_sobuoitre        NUMBER := 0;
        v_tongsobuoivang   NUMBER := 0;
    BEGIN
        -- Đếm số buổi vắng (TrangThai = '1')
        SELECT COUNT(*) INTO v_sobuoivang
        FROM DiemDanh
        WHERE MaHV = MAHV_IN 
        AND TrangThai = '1';
    
        -- Đếm số buổi đi trễ (TrangThai = '2')
        SELECT COUNT(*) INTO v_sobuoitre
        FROM DiemDanh
        WHERE MaHV = MAHV_IN 
        AND TrangThai = '2';
    
        -- Tính tổng buổi vắng (cứ 3 buổi trễ tính như 1 buổi vắng)
        v_tongsobuoivang := v_sobuoivang + FLOOR(v_sobuoitre / 3);
    
        RETURN v_tongsobuoivang;
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN 0;
        WHEN OTHERS THEN
            RETURN 0;
    END;
/
-- DEMO 
SELECT TINH_SO_BUOI_VANG('HV001') FROM dual;


    
--DEMO 
  SELECT TINH_SO_BUOI_VANG('HV001')
  FROM dual
--2. 2. Thống kê số lượng học viên trung bình của 1 lớp
  
    CREATE OR REPLACE FUNCTION TINH_SOHOCVIEN_TRUNGBINH (
    P_TENLOP LOP.TENLOP%TYPE,
    P_BAND   LOP.BAND%TYPE
)
RETURN FLOAT
IS
    V_SOHOCVIEN NUMBER := 0;
    V_SOLOPMO   NUMBER := 0;
BEGIN
    -- Lấy số lượng học viên đang đăng ký lớp
    SELECT COUNT(D.MAHV)
    INTO V_SOHOCVIEN
    FROM DANGKYLOP D
    JOIN LOP L ON D.MALOP = L.MALOP
    WHERE L.TENLOP = P_TENLOP AND L.BAND = P_BAND;

    -- Lấy số lớp đang mở
    SELECT COUNT(L.MALOP)
    INTO V_SOLOPMO
    FROM LOP L
    WHERE L.TENLOP = P_TENLOP AND L.BAND = P_BAND;

    -- Tránh chia cho 0
    IF V_SOLOPMO = 0 THEN
        RETURN 0;
    ELSE
        RETURN ROUND(V_SOHOCVIEN / V_SOLOPMO, 2);
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 0;
    WHEN OTHERS THEN
        RETURN 0;
END;
/
--DEMO
SELECT TINH_SOHOCVIEN_TRUNGBINH('IELTS','6.5') FROM DUAL;
--3. Tính học phí sau khi áp dụng khuyến mãi
    CREATE OR REPLACE FUNCTION TINH_HOC_PHI_SAU_KHUYEN_MAI (
    P_MA_LOP IN LOP.MALOP%TYPE,
    P_MA_HV IN HOCVIEN.MAHV%TYPE
)
RETURN NUMBER
IS
    V_HOC_PHI LOP.HOCPHI%TYPE;
    V_GIA_TRI_KM KHUYENMAI.GIATRIKM%TYPE;
    V_LOAI_KM KHUYENMAI.LOAIKM%TYPE;
    V_NGAY_BD KHUYENMAI.NGAYBD%TYPE;
    V_NGAY_KT KHUYENMAI.NGAYKT%TYPE;
    V_HOC_PHI_SAU_KM NUMBER;
    V_MA_KM SUDUNGKHUYENMAI.MAKM%TYPE;
    V_NGAY_SU_DUNG SUDUNGKHUYENMAI.NGAYSUDUNG%TYPE;
BEGIN
    -- 1. Lay hoc phi goc cua lop
    SELECT HOCPHI
    INTO V_HOC_PHI
    FROM LOP
    WHERE MALOP = P_MA_LOP;

    V_HOC_PHI_SAU_KM := V_HOC_PHI;

    -- 2. Lay ma khuyen mai va ngay su dung
    BEGIN
        SELECT MAKM, NGAYSUDUNG
        INTO V_MA_KM, V_NGAY_SU_DUNG
        FROM SUDUNGKHUYENMAI
        WHERE MAHV = P_MA_HV AND MALOP = P_MA_LOP;

        -- 3. Lay thong tin khuyen mai
        SELECT GIATRIKM, LOAIKM, NGAYBD, NGAYKT
        INTO V_GIA_TRI_KM, V_LOAI_KM, V_NGAY_BD, V_NGAY_KT
        FROM KHUYENMAI
        WHERE MAKM = V_MA_KM;

        -- 4. Kiem tra ngay va loai khuyen mai hop le
        IF V_NGAY_SU_DUNG BETWEEN V_NGAY_BD AND V_NGAY_KT AND 
           V_LOAI_KM IN ('Uu dai nhom', 'Hoc mien phi cho hoc vien xuat sac', 'Ngay Vang Giam Gia') THEN

            -- 5. Ap dung muc giam
            IF V_LOAI_KM = 'Uu dai nhom' OR V_LOAI_KM = 'Ngay Vang Giam Gia' THEN
                V_HOC_PHI_SAU_KM := V_HOC_PHI * (1 - TO_NUMBER(V_GIA_TRI_KM) / 100);
            ELSIF V_LOAI_KM = 'Hoc mien phi cho hoc vien xuat sac' THEN
                V_HOC_PHI_SAU_KM := 0;
            END IF;
        END IF;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Khong co khuyen mai nao duoc ap dung
            NULL;
    END;

    RETURN V_HOC_PHI_SAU_KM;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN NULL; -- Hoac RETURN V_HOC_PHI;
    WHEN OTHERS THEN
        RETURN NULL;
END;

/


--4. Hiển thị số slot còn trống nếu lớp còn trong thời gian đăng ký
    CREATE OR REPLACE FUNCTION getEmptySlot (
        p_maLop IN LOP.MALOP%TYPE
    )
    RETURN NUMBER
    IS
      v_dkHoc LOP.SISO%TYPE;
      v_siSo  LOP.SISO%TYPE;
      v_bd DATE;
    BEGIN
      SELECT l.TGBATDAU INTO v_bd
      FROM LOP l
      WHERE l.MALOP = p_maLop; 
    
      IF v_bd > SYSDATE THEN 
          -- Lấy sĩ số lớp từ bảng LOP
          SELECT l.SISO INTO v_siSo
          FROM LOP l
          WHERE l.MALOP = p_maLop;
        
          -- Đếm số lượt đăng ký lớp từ bảng DANGKYLOP
          SELECT COUNT(*) INTO v_dkHoc
          FROM DANGKYLOP dk
          WHERE dk.MALOP = p_maLop;
        
          -- Trả về số chỗ trống còn lại
          RETURN v_siSo - v_dkHoc;
      ELSE
          DBMS_OUTPUT.PUT_LINE('Lớp học đã hết thời hạn đăng ký');
          RETURN NULL;
      END IF; 
    
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            -- Trả về NULL nếu không tìm thấy lớp
            RETURN NULL;
    END;
    /
