package vn.iodev.contestmanagementsystem.helper;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.iodev.contestmanagementsystem.model.CuocThi;
import vn.iodev.contestmanagementsystem.model.DanhSachThi;
import vn.iodev.contestmanagementsystem.model.DoanThi;
import vn.iodev.contestmanagementsystem.model.HuanLuyenVien;
import vn.iodev.contestmanagementsystem.model.KhoiThi;
import vn.iodev.contestmanagementsystem.model.LoaiChucVu;
import vn.iodev.contestmanagementsystem.model.LoaiGioiTinh;
import vn.iodev.contestmanagementsystem.model.LoaiTinhTrangCuocThi;
import vn.iodev.contestmanagementsystem.model.LoaiTinhTrangToChuc;
import vn.iodev.contestmanagementsystem.model.ThiSinh;
import vn.iodev.contestmanagementsystem.payload.ToChucResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

@Slf4j
public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] DOANTHI_HEADERs = { "Tên gọi", "Tiếng Anh", "Địa chỉ hoạt động", "Email", "ToChuc_ID", "CuocThi_ID", "Thứ tự xếp hạng" };
    static String SHEET_DOANTHI = "Đoàn thi";

    static String[] THISINH_HEADERs = { "Họ tên", "Giới tính", "Ngày sinh", "Email", "Số điện thoại", "Đối tượng thi", "Ngành đào tạo", "Đạt giải thưởng", "CaNhan_ID", "DoanThi_ID" };
    static String SHEET_THISINH = "Thí sinh";

    static String[] HUANLUYENVIEN_HEADERs = { "Họ tên", "Email", "Số điện thoại", "Tổ chức", "Đoàn thi", "Trưởng phó đoàn" };
    static String SHEET_HUANLUYENVIEN = "Huấn luyện viên";
    
    static String[] HEADERs_TOCHUC = { "Tên gọi", "Tiếng Anh", "Tên viết tắt", "Loại tổ chức", "Địa chỉ hoạt động", "Vị trí địa lý", "Email", "Địa chỉ trang web" };
    static String SHEET_TOCHUC = "Tổ chức";

    static String[] HEADERs_CUOCTHI = { "Tên gọi", "Tiếng Anh", "Serie cuộc thi", "Lần tổ chức", "Đơn vị tổ chức", "Ngày bắt đầu", "Ngày kết thúc", "Thông tin mô tả", "Website", "Tình trạng" };
    static String SHEET_CUOCTHI = "Cuộc thi";

    static String[] HEADERs_KHOITHI = { "Tên gọi", "Cuộc thi", "Nội dung thi", "Vòng sơ loại", "Thi tập thể", "Thi sáng tạo", "Số đăng ký tối đa", "Số thí sinh tối đa" };
    static String SHEET_KHOITHI = "Khối thi";

    static String SHEET_DANHSACHTHI = "Danh sách thi";

    static String[] DANHSACHTHI_HEADERs = { "Họ tên", "Giới tính", "Ngày sinh", "Email", "Số điện thoại", "Đối tượng thi", "Ngành đào tạo", "Đạt giải thưởng", "Đoàn thi", "Cuộc thi", "Tham gia", "Đội thi" };
    public static final String SHEET_DANHSACHTHI_OLPCHUYENTIN = "OLP chuyên tin";
    public static final String SHEET_DANHSACHTHI_OLPKHONGCHUYENTIN = "OLP không chuyên tin";
    public static final String SHEET_DANHSACHTHI_ICPCCHUYENTIN = "ICPC chuyên tin";
    public static final String SHEET_DANHSACHTHI_ICPCKHONGCHUYENTIN = "ICPC không chuyên tin";
    public static final String SHEET_DANHSACHTHI_OLPCAODANG = "OLP cao đẳng";
    public static final String SHEET_DANHSACHTHI_OLPPROCON = "OLP PROCON";
    public static final String SHEET_DANHSACHTHI_OLPPMNM = "OLP PMNM";
    public static final String SHEET_DANHSACHTHI_OLPSIEUCUP = "OLP siêu cúp";

    private static final String GIOITINH_NAM = "Nam";
    private static final String GIOITINH_NU = "Nữ";

    private static final String CHUC_VU_TRUONG_DOAN = "Trưởng đoàn";
    private static final String CHUC_VU_PHO_DOAN = "Phó đoàn";

    private static final String TINHTRANG_CUOCTHI_MODANGKI = "Mở đăng ký";
    private static final String TINHTRANG_CUOCTHI_DONGDANGKI = "Đóng đăng ký";
    private static final String TINHTRANG_CUOCTHI_DAKETTHUC = "Đã kết thúc";

    private static final String TRUE_STR = "Có";
    private static final String FALSE_STR = "Không";

    private static final String VN_DATE_FORMAT = "dd/MM/yyyy";

    public static final String SHEET_KETQUATHI_OLPCHUYENTIN = "OLP chuyên tin";
    public static final String SHEET_KETQUATHI_OLPKHONGCHUYENTIN = "OLP không chuyên tin";
    public static final String SHEET_KETQUATHI_ICPCCHUYENTIN = "ICPC chuyên tin";
    public static final String SHEET_KETQUATHI_ICPCKHONGCHUYENTIN = "ICPC không chuyên tin";
    public static final String SHEET_KETQUATHI_OLPCAODANG = "OLP cao đẳng";
    public static final String SHEET_KETQUATHI_OLPPROCON = "OLP PROCON";
    public static final String SHEET_KETQUATHI_OLPPMNM = "OLP PMNM";
    public static final String SHEET_KETQUATHI_OLPSIEUCUP = "OLP siêu cúp";

    public static boolean hasExcelFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<ToChucResponse> excelXlsxToToChucs(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(SHEET_TOCHUC);
            Iterator<Row> rows = sheet.iterator();
    
            List<ToChucResponse> tochucs = new ArrayList<ToChucResponse>();
    
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                ToChucResponse toChuc = new ToChucResponse();
                toChuc.setTinhTrang(LoaiTinhTrangToChuc.DA_CHINH_THUC);

                int cellIdx = 0;
                for (int i = 0; i <= 7; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setTenGoi(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setTiengAnh(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setTenVietTat(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setLoaiToChuc(currentCell.getStringCellValue());
                            }
                            break;
                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setDiaChiHoatDong(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 5:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setViTriDiaLy(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 6:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setEmail(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 7:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                toChuc.setWeb(currentCell.getStringCellValue());
                            }
                            break;

                        default:
                            break;
                    }
                }
    
                tochucs.add(toChuc);
            }
    
            workbook.close();
    
            return tochucs;
        } catch (IOException e) {
            log.debug("excelXlsxToToChucs", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<DoanThi> excelXlsxToDoanThis(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(SHEET_DOANTHI);
            Iterator<Row> rows = sheet.iterator();
    
            List<DoanThi> doanthis = new ArrayList<DoanThi>();
    
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                DoanThi doanThi = new DoanThi();

                int cellIdx = 0;
                for (int i = 0; i <= 6; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                doanThi.setTenGoi(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                doanThi.setTiengAnh(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                doanThi.setDiaChiHoatDong(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                doanThi.setEmail(currentCell.getStringCellValue());
                            }
                            break;
                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                doanThi.setToChucId(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 5:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                doanThi.setCuocThiId(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 6:
                            if (currentCell != null && currentCell.getCellType() == CellType.NUMERIC) {
                                doanThi.setThuTuXepHang((int)currentCell.getNumericCellValue());
                            }
                            break;

                        default:
                            break;
                    }
                }
    
                doanthis.add(doanThi);
            }
    
            workbook.close();
    
            return doanthis;
        } catch (IOException e) {
            log.debug("excelXlsxToDoanThis", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    } 
    
    public static List<ThiSinh> excelXlsxToThiSinhs(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(SHEET_THISINH);
            Iterator<Row> rows = sheet.iterator();
    
            List<ThiSinh> thisinhs = new ArrayList<ThiSinh>();
            SimpleDateFormat format = new SimpleDateFormat(VN_DATE_FORMAT);

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                ThiSinh thiSinh = new ThiSinh();

                int cellIdx = 0;
                for (int i = 0; i <= 6; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setHoTen(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String gioiTinhStr = currentCell.getStringCellValue();
                                if (GIOITINH_NAM.equals(gioiTinhStr.trim())) {
                                    thiSinh.setGioiTinh(LoaiGioiTinh.NAM);
                                }
                                else if (GIOITINH_NU.equals(gioiTinhStr.trim())) {
                                    thiSinh.setGioiTinh(LoaiGioiTinh.NU);
                                }
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String ngaySinhStr = currentCell.getStringCellValue();
                                Date parsed;
                                try {
                                    parsed = format.parse(ngaySinhStr);
                                    thiSinh.setNgaySinh(new java.sql.Date(parsed.getTime()));
                                } catch (ParseException e) {
                                    log.debug("excelXlsxToThiSinhs", e);
                                }
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String email = currentCell.getStringCellValue();
                                if (email != null && !email.isEmpty()) {
                                    thiSinh.setEmail(email);
                                }
                            }
                            break;
                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setSoDienThoai(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 5:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setDoiTuongThi(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 6:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setNganhDaoTao(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 7:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setDatGiaiThuong(currentCell.getStringCellValue());
                            }
                            break;

                        default:
                            break;
                    }
                }
    
                thisinhs.add(thiSinh);
            }
    
            workbook.close();
    
            return thisinhs;
        } catch (IOException e) {
            log.debug("excelXlsxToThiSinhs", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    } 

    public static List<HuanLuyenVien> excelXlsxToHuanLuyenViens(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(SHEET_HUANLUYENVIEN);
            Iterator<Row> rows = sheet.iterator();
    
            List<HuanLuyenVien> huanluyenviens = new ArrayList<HuanLuyenVien>();
    
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                HuanLuyenVien huanLuyenVien = new HuanLuyenVien();

                int cellIdx = 0;
                for (int i = 0; i <= 6; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                huanLuyenVien.setHoTen(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                huanLuyenVien.setEmail(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                huanLuyenVien.setSoDienThoai(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                huanLuyenVien.setTenToChuc(currentCell.getStringCellValue());
                            }
                            break;
                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                huanLuyenVien.setTenDoanThi(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 5:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String truongPhoDoanStr = currentCell.getStringCellValue();
                                if (CHUC_VU_TRUONG_DOAN.equals(truongPhoDoanStr.trim())) {
                                    huanLuyenVien.setTruongPhoDoan(LoaiChucVu.TRUONG_DOAN);
                                }
                                else if (CHUC_VU_PHO_DOAN.equals(truongPhoDoanStr.trim())) {
                                    huanLuyenVien.setTruongPhoDoan(LoaiChucVu.PHO_DOAN);
                                }
                            }
                            break;

                        default:
                            break;
                    }
                }
    
                huanluyenviens.add(huanLuyenVien);
            }
    
            workbook.close();
    
            return huanluyenviens;
        } catch (IOException e) {
            log.debug("excelXlsxToHuanLuyenViens", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<CuocThi> excelXlsxToCuocThis(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(SHEET_CUOCTHI);
            Iterator<Row> rows = sheet.iterator();
    
            List<CuocThi> cuocthis = new ArrayList<CuocThi>();
            SimpleDateFormat format = new SimpleDateFormat(VN_DATE_FORMAT);

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                CuocThi cuocThi = new CuocThi();

                int cellIdx = 0;
                for (int i = 0; i <= 11; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                cuocThi.setId(currentCell.getStringCellValue());
                            }
                            break;

                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                cuocThi.setTenGoi(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                cuocThi.setTiengAnh(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                cuocThi.setSerieCuocThi(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.NUMERIC) {
                                cuocThi.setLanToChuc((int)currentCell.getNumericCellValue());
                            }
                            break;
                        case 5:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String tenDonViToChuc = currentCell.getStringCellValue();
                                cuocThi.setDonViToChuc(tenDonViToChuc);
                            }
                            break;

                        case 6:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String diaDiemToChuc = currentCell.getStringCellValue();
                                cuocThi.setDiaDiemToChuc(diaDiemToChuc);
                            }
                            break;

                        case 7:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {  
                                String ngayBatDauStr = currentCell.getStringCellValue();
                                Date parsed;
                                try {
                                    parsed = format.parse(ngayBatDauStr);
                                    cuocThi.setNgayBatDau(new java.sql.Date(parsed.getTime()));
                                } catch (ParseException e) {
                                    log.debug("excelXlsxToCuocThis", e);
                                }
                            }
                            break;
                        
                        case 8:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String ngayKetThucStr = currentCell.getStringCellValue();
                                Date parsed;
                                try {
                                    parsed = format.parse(ngayKetThucStr);
                                    cuocThi.setNgayKetThuc(new java.sql.Date(parsed.getTime()));
                                } catch (ParseException e) {
                                    log.debug("excelXlsxToCuocThis", e);
                                }
                            }
                            break;
                        
                        case 9:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                cuocThi.setThongTinMoTa(currentCell.getStringCellValue());
                            }
                            break;

                        case 10:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                cuocThi.setWebsite(currentCell.getStringCellValue());
                            }
                            break;

                        case 11:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String tinhTrangStr = currentCell.getStringCellValue();
                                if (TINHTRANG_CUOCTHI_MODANGKI.equals(tinhTrangStr.trim())) {
                                    cuocThi.setTinhTrang(LoaiTinhTrangCuocThi.MODANGKY);
                                }
                                else if (TINHTRANG_CUOCTHI_DONGDANGKI.equals(tinhTrangStr.trim())) {
                                    cuocThi.setTinhTrang(LoaiTinhTrangCuocThi.DONGDANGKY);
                                }
                                else if (TINHTRANG_CUOCTHI_DAKETTHUC.equals(tinhTrangStr.trim())) {
                                    cuocThi.setTinhTrang(LoaiTinhTrangCuocThi.DAKETTHUC);
                                }
                                else {
                                    cuocThi.setTinhTrang(-1);
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
    
                cuocthis.add(cuocThi);
            }
    
            workbook.close();
    
            return cuocthis;
        } catch (IOException e) {
            log.debug("excelXlsxToCuocThis", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<KhoiThi> excelXlsxToKhoiThis(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(SHEET_KHOITHI);
            Iterator<Row> rows = sheet.iterator();
    
            List<KhoiThi> khoithis = new ArrayList<KhoiThi>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                KhoiThi khoiThi = new KhoiThi();

                int cellIdx = 0;
                for (int i = 0; i <= 7; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                khoiThi.setTenGoi(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String tenCuocThi = currentCell.getStringCellValue();
                                khoiThi.setTenCuocThi(tenCuocThi);
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                khoiThi.setNoiDungThi(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String vongSoLoaiStr = currentCell.getStringCellValue();
                                if (TRUE_STR.equals(vongSoLoaiStr.trim())) {
                                    khoiThi.setVongSoLoai(true);
                                }
                                else if (FALSE_STR.equals(vongSoLoaiStr.trim())) {
                                    khoiThi.setVongSoLoai(false);
                                }
                            }
                            break;
                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String thiTapTheStr = currentCell.getStringCellValue();
                                if (TRUE_STR.equals(thiTapTheStr.trim())) {
                                    khoiThi.setThiTapThe(true);
                                }
                                else if (FALSE_STR.equals(thiTapTheStr.trim())) {
                                    khoiThi.setThiTapThe(false);
                                }
                            }
                            break;
                        
                        case 5:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {  
                                String thiSangTaoStr = currentCell.getStringCellValue();
                                if (TRUE_STR.equals(thiSangTaoStr.trim())) {
                                    khoiThi.setThiSangTao(true);
                                }
                                else if (FALSE_STR.equals(thiSangTaoStr.trim())) {
                                    khoiThi.setThiSangTao(false);
                                }
                            }
                            break;
                        
                        case 6:
                            if (currentCell != null && currentCell.getCellType() == CellType.NUMERIC) {
                                khoiThi.setMaxDangKi((int)currentCell.getNumericCellValue());
                            }
                            break;
                        
                        case 7:
                            if (currentCell != null && currentCell.getCellType() == CellType.NUMERIC) {
                                khoiThi.setMaxThiSinh((int)currentCell.getNumericCellValue());
                            }
                            break;
                        default:
                            break;
                    }
                }
    
                khoithis.add(khoiThi);
            }
    
            workbook.close();
    
            return khoithis;
        } catch (IOException e) {
            log.debug("excelXlsxToKhoiThis", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static List<ThiSinh> excelXlsxToDanhSachThis(InputStream is, String sheetName) {
        try {
            List<ThiSinh> thisinhs = new ArrayList<ThiSinh>();

            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                workbook.close();
                return thisinhs;
            }
            Iterator<Row> rows = sheet.iterator();
    
            SimpleDateFormat format = new SimpleDateFormat(VN_DATE_FORMAT);

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                ThiSinh thiSinh = new ThiSinh();

                int cellIdx = 0;
                for (int i = 0; i <= 12; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setHoTen(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String gioiTinhStr = currentCell.getStringCellValue();
                                if (GIOITINH_NAM.equals(gioiTinhStr.trim())) {
                                    thiSinh.setGioiTinh(LoaiGioiTinh.NAM);
                                }
                                else if (GIOITINH_NU.equals(gioiTinhStr.trim())) {
                                    thiSinh.setGioiTinh(LoaiGioiTinh.NU);
                                }
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String ngaySinhStr = currentCell.getStringCellValue();
                                Date parsed;
                                try {
                                    parsed = format.parse(ngaySinhStr);
                                    thiSinh.setNgaySinh(new java.sql.Date(parsed.getTime()));
                                } catch (ParseException e) {
                                    log.debug("excelXlsxToDanhSachThis", e);
                                }
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                String email = currentCell.getStringCellValue();
                                if (email != null && !email.isEmpty()) {
                                    thiSinh.setEmail(email);
                                }
                            }
                            break;
                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setSoDienThoai(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 5:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setDoiTuongThi(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 6:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setNganhDaoTao(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 7:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setDatGiaiThuong(currentCell.getStringCellValue());
                            }
                            break;
                        
                        case 8:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setTenDoanThi(currentCell.getStringCellValue());
                            }
                            break;

                        case 9:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setTenCuocThi(currentCell.getStringCellValue());
                            }
                            break;

                        case 10:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setTenKhoiThi(currentCell.getStringCellValue());
                            }
                            break;

                        case 11:
                            if (currentCell != null && currentCell.getCellType() == CellType.NUMERIC) {
                                int thamGia = (int)currentCell.getNumericCellValue();
                                if (thamGia == 1) {
                                    thiSinh.setThamGia(true);
                                }
                                else {
                                    thiSinh.setThamGia(false);
                                }
                            }
                            else {
                                thiSinh.setThamGia(false);
                            }
                            break;
                        case 12:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                thiSinh.setTenDoiThi(currentCell.getStringCellValue());
                            }
                            break;
                        default:
                            break;
                    }
                }
    
                if (thiSinh.isThamGia()) {
                    thisinhs.add(thiSinh);
                }
            }
    
            workbook.close();
    
            return thisinhs;
        } catch (IOException e) {
            log.debug("excelXlsxToDanhSachThis", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    } 

    public static List<ThiSinh> excelXlsxToTongHopThiSinh(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(SHEET_DANHSACHTHI);
            Iterator<Row> rows = sheet.iterator();
    
            List<ThiSinh> thisinhs = new ArrayList<ThiSinh>();
            SimpleDateFormat format = new SimpleDateFormat(VN_DATE_FORMAT);

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber < 9) {
                    rowNumber++;
                    continue;
                }
    
                ThiSinh thiSinh = new ThiSinh();

                int cellIdx = 0;

                Cell thuTuCell = currentRow.getCell(3);
                if (thuTuCell != null && thuTuCell.getCellType() == CellType.NUMERIC) {
                    log.info("Cell: " + thuTuCell.getNumericCellValue());
                }
    
                thisinhs.add(thiSinh);
            }
    
            workbook.close();
    
            return thisinhs;
        } catch (IOException e) {
            log.debug("excelXlsxToTongHopThiSinh", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    } 

    public static List<KetQuaThi> excelToKetQuaDanhSachThis(InputStream is, String sheetName) {
        try {
            List<KetQuaThi> ketQuaThis = new ArrayList<KetQuaThi>();

            Workbook workbook = new XSSFWorkbook(is);
    
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                workbook.close();
                return ketQuaThis;
            }
            Iterator<Row> rows = sheet.iterator();
    
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
    
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
    
                KetQuaThi ketQuaThi = new KetQuaThi();

                int cellIdx = 0;
                for (int i = 0; i <= 4; i++) {
                    Cell currentCell = currentRow.getCell(i);
                    cellIdx = i;
                    
                    switch (cellIdx) {
                        case 0:
                            if (currentCell != null && currentCell.getCellType() == CellType.NUMERIC) {
                                ketQuaThi.setSTT((int)currentCell.getNumericCellValue());
                            }
                            break;
    
                        case 1:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                ketQuaThi.setDoiTuong(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 2:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                ketQuaThi.setTenTruong(currentCell.getStringCellValue());
                            }
                            break;
    
                        case 3:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                ketQuaThi.setGiaiThuong(currentCell.getStringCellValue());
                            }
                            break;

                        case 4:
                            if (currentCell != null && currentCell.getCellType() == CellType.STRING) {
                                ketQuaThi.setKhoiThi(currentCell.getStringCellValue());
                            }
                            break;
                        default:
                            break;
                    }
                }
    
                ketQuaThis.add(ketQuaThi);
            }
    
            workbook.close();
    
            return ketQuaThis;
        } catch (IOException e) {
            log.debug("excelToKetQuaDanhSachThis", e);
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
