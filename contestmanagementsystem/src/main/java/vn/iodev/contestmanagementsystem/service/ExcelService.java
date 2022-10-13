package vn.iodev.contestmanagementsystem.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;
import vn.iodev.contestmanagementsystem.helper.ExcelHelper;
import vn.iodev.contestmanagementsystem.model.CuocThi;
import vn.iodev.contestmanagementsystem.model.DanhSachThi;
import vn.iodev.contestmanagementsystem.model.DoanThi;
import vn.iodev.contestmanagementsystem.model.DoiThi;
import vn.iodev.contestmanagementsystem.model.HuanLuyenVien;
import vn.iodev.contestmanagementsystem.model.KhoiThi;
import vn.iodev.contestmanagementsystem.model.ThiSinh;
import vn.iodev.contestmanagementsystem.payload.ToChucResponse;
import vn.iodev.contestmanagementsystem.repository.CuocThiRepository;
import vn.iodev.contestmanagementsystem.repository.DanhSachThiRepository;
import vn.iodev.contestmanagementsystem.repository.DoanThiRepository;
import vn.iodev.contestmanagementsystem.repository.DoiThiRepository;
import vn.iodev.contestmanagementsystem.repository.HuanLuyenVienRepository;
import vn.iodev.contestmanagementsystem.repository.KhoiThiRepository;
import vn.iodev.contestmanagementsystem.repository.ThiSinhRepository;
import vn.iodev.contestmanagementsystem.utils.RandomUtil;

@Service
@Slf4j
public class ExcelService {
    @Autowired
    DoanThiRepository doanThiRepository;

    @Autowired
    ThiSinhRepository thiSinhRepository;

    @Autowired
    HuanLuyenVienRepository huanLuyenVienRepository;

    @Autowired
    CuocThiRepository cuocThiRepository;

    @Autowired
    ToChucService toChucService;

    @Autowired
    KhoiThiRepository khoiThiRepository;

    @Autowired
    DanhSachThiRepository danhSachThiRepository;

    @Autowired
    DoiThiRepository doiThiRepository;

    public void importDoanThi(MultipartFile file) {
        try {
            List<DoanThi> doanthis = ExcelHelper.excelXlsxToDoanThis(file.getInputStream());
            for (DoanThi dt : doanthis) {
                log.info("Doan Thi: " + dt.getTenGoi());
                Optional<DoanThi> doanThiData = doanThiRepository.findByTenGoiOrTiengAnh(dt.getTenGoi(), dt.getTiengAnh());
                if (doanThiData.isPresent()) {
                    dt.setId(doanThiData.get().getId());
                }
                else {
                    dt.setThoiGianTao(System.currentTimeMillis());
                }
                dt.setThoiGianCapNhat(System.currentTimeMillis());
            }
            doanThiRepository.saveAll(doanthis);
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }  
    
    public void importThiSinh(MultipartFile file, CuocThi cuocThi) {
        try {
            List<ThiSinh> thisinhs = ExcelHelper.excelXlsxToThiSinhs(file.getInputStream());
            for (ThiSinh ts : thisinhs) {
                ts.setCuocThi(cuocThi);
                log.info("Thi Sinh: " + ts.getHoTen());
                Optional<ThiSinh> thiSinhData = thiSinhRepository.findByHoTenAndGioiTinhAndNgaySinhAndNganhDaoTao(ts.getHoTen(), ts.getGioiTinh(), ts.getNgaySinh(), ts.getNganhDaoTao());
                if (thiSinhData.isPresent()) {
                    ts.setId(thiSinhData.get().getId());
                }
                else {
                    ts.setThoiGianTao(System.currentTimeMillis());
                }
                ts.setThoiGianCapNhat(System.currentTimeMillis());
            }
            thiSinhRepository.saveAll(thisinhs);
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }  

    public void importHuanLuyenVien(MultipartFile file, CuocThi cuocThi) {
        try {
            List<HuanLuyenVien> huanluyenviens = ExcelHelper.excelXlsxToHuanLuyenViens(file.getInputStream());
            for (HuanLuyenVien hlv : huanluyenviens) {
                hlv.setCuocThi(cuocThi);
                log.info("Huan Luyen Vien: " + hlv.getHoTen());
                Optional<HuanLuyenVien> huanLuyenVienData = huanLuyenVienRepository.findByHoTenAndEmailAndSoDienThoai(hlv.getHoTen(), hlv.getEmail(), hlv.getSoDienThoai());
                if (huanLuyenVienData.isPresent()) {
                    hlv.setId(huanLuyenVienData.get().getId());
                }
                else {
                    hlv.setThoiGianTao(System.currentTimeMillis());
                }
                hlv.setThoiGianCapNhat(System.currentTimeMillis());
            }
            huanLuyenVienRepository.saveAll(huanluyenviens);
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    } 
    
    public List<ToChucResponse> importToChuc(MultipartFile file) {
        List<ToChucResponse> lstToChucs = new ArrayList<>();

        try {
            List<ToChucResponse> tochucs = ExcelHelper.excelXlsxToToChucs(file.getInputStream());
            for (ToChucResponse tcr : tochucs) {
                ToChucResponse result = toChucService.createToChuc(tcr);
                lstToChucs.add(result);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }

        return lstToChucs;
    }

    public List<CuocThi> importCuocThi(MultipartFile file) {
        try {
            List<CuocThi> cuocthis = ExcelHelper.excelXlsxToCuocThis(file.getInputStream());

            for (CuocThi cuocThi : cuocthis) {
                CuocThi oldCuocThi = cuocThiRepository.findByTenGoiAndSerieCuocThiAndLanToChucAndDonViToChuc(cuocThi.getTenGoi(), cuocThi.getSerieCuocThi(), cuocThi.getLanToChuc(), cuocThi.getDonViToChuc());
                if (oldCuocThi != null) {
                    cuocThi.setId(oldCuocThi.getId());
                }
                if (cuocThi.getDonViToChuc() != null && !cuocThi.getDonViToChuc().isEmpty()) {
                    ToChucResponse[] toChucs = toChucService.getToChucByTenGoi(cuocThi.getDonViToChuc());
                    if (toChucs.length > 0) {
                        cuocThi.setToChucId(toChucs[0].getId());
                    }
                }
            }

            return cuocThiRepository.saveAll(cuocthis);
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<KhoiThi> importKhoiThi(MultipartFile file, List<CuocThi> cuocthis) {
        try {
            List<KhoiThi> khoithis = ExcelHelper.excelXlsxToKhoiThis(file.getInputStream());
            for (KhoiThi khoiThi : khoithis) {
                if (khoiThi.getTenCuocThi() != null && !khoiThi.getTenCuocThi().isEmpty()) {
                    for (CuocThi ct : cuocthis) {
                        if (ct.getTenGoi().equals(khoiThi.getTenCuocThi())) {
                            khoiThi.setCuocThi(ct);
                            Optional<KhoiThi> oldKhoiThi = khoiThiRepository.findByTenGoiAndCuocThiId(khoiThi.getTenGoi(), khoiThi.getCuocThi().getId());
                            if (oldKhoiThi.isPresent()) {
                                khoiThi.setId(oldKhoiThi.get().getId());
                            }
                        }
                    }
                }
            }

            return khoiThiRepository.saveAll(khoithis);
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public List<HuanLuyenVien> importHuanLuyenVien(MultipartFile file, List<ToChucResponse> tochucs, List<KhoiThi> khoithis, List<DoanThi> doanthis, CuocThi cuocThi) {
        try {
            List<HuanLuyenVien> huanLuyenViens = ExcelHelper.excelXlsxToHuanLuyenViens(file.getInputStream());
            StringBuilder khoiThiId = new StringBuilder();
            for (KhoiThi khoiThi : khoithis) {
                if (!khoiThiId.toString().equals("")) {
                    khoiThiId.append(",");
                }
                khoiThiId.append(khoiThi.getId());
            }
            for (HuanLuyenVien huanLuyenVien : huanLuyenViens) {
                huanLuyenVien.setKhoiThiId(khoiThiId.toString());
                if (huanLuyenVien.getTenToChuc() != null && !huanLuyenVien.getTenToChuc().isEmpty()) {
                    for (ToChucResponse tc : tochucs) {
                        if (tc.getTenGoi().equals(huanLuyenVien.getTenToChuc())) {
                            DoanThi doanThi = new DoanThi(huanLuyenVien.getTenDoanThi(), "", tc.getDiaChiHoatDong(), tc.getEmail(), tc.getId(), null);
                            Optional<DoanThi> oldDoanThi = doanThiRepository.findByTenGoiAndToChucId(doanThi.getTenGoi(), doanThi.getToChucId());
                            if (oldDoanThi.isPresent()) {
                                doanThi.setId(oldDoanThi.get().getId());
                            }
                            DoanThi dtKQ = doanThiRepository.save(doanThi);
                            doanthis.add(dtKQ);

                            huanLuyenVien.setDoanThiId(dtKQ.getId());
                            log.info("Find huan luyen vien: " + huanLuyenVien.getHoTen() + ", " + cuocThi.getId() + ", " + huanLuyenVien.getDoanThiId() + ", " + huanLuyenVien.getEmail() + ", " + huanLuyenVien.getSoDienThoai());
                            Optional<HuanLuyenVien> oldHuanLuyenVien = huanLuyenVienRepository.findByHoTenAndCuocThiIdAndDoanThiIdAndEmailAndSoDienThoai(huanLuyenVien.getHoTen(), cuocThi.getId(), huanLuyenVien.getDoanThiId(), huanLuyenVien.getEmail(), huanLuyenVien.getSoDienThoai());
                            
                            if (oldHuanLuyenVien.isPresent()) {
                                huanLuyenVien.setId(oldHuanLuyenVien.get().getId());
                            }
            
                            break;
                        }
                    }
                }
                huanLuyenVien.setCuocThi(cuocThi);
            }

            return huanLuyenVienRepository.saveAll(huanLuyenViens);
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    }

    public void importDanhSachThi(MultipartFile file, List<CuocThi> cuocthis, List<KhoiThi> khoithis, List<DoanThi> doanthis) {
        try {
            List<ThiSinh> thisinhs = ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_OLPCHUYENTIN);
            thisinhs.addAll(ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_OLPKHONGCHUYENTIN));
            thisinhs.addAll(ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_ICPCCHUYENTIN));
            thisinhs.addAll(ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_ICPCKHONGCHUYENTIN));
            thisinhs.addAll(ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_OLPCAODANG));
            thisinhs.addAll(ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_OLPPROCON));
            thisinhs.addAll(ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_OLPPMNM));
            thisinhs.addAll(ExcelHelper.excelXlsxToDanhSachThis(file.getInputStream(), ExcelHelper.SHEET_DANHSACHTHI_OLPSIEUCUP));

            for (ThiSinh ts : thisinhs) {
                for (CuocThi cuocThi : cuocthis) {
                    if (cuocThi.getTenGoi().equals(ts.getTenCuocThi())) {
                        ts.setCuocThi(cuocThi);
                        break;
                    }
                }
                KhoiThi khoiThi = null;
                for (KhoiThi kt : khoithis) {
                    if (kt.getTenGoi().equals(ts.getTenKhoiThi())) {
                        khoiThi = kt;
                        break;
                    }
                }
                if (khoiThi != null && !khoiThi.getThiTapThe()) {
                    // DoanThi doanThi = null;
                    // for (DoanThi dt : doanthis) {
                    //     if (dt.getTenGoi().equals(ts.getTenDoanThi())) {
                    //         doanThi = dt;
                    //         break;
                    //     }
                    // }                    
                    log.info("Thi Sinh: " + ts.getHoTen());
                    Optional<ThiSinh> thiSinhData = thiSinhRepository.findByHoTenAndGioiTinhAndNgaySinhAndNganhDaoTao(ts.getHoTen(), ts.getGioiTinh(), ts.getNgaySinh(), ts.getNganhDaoTao());
                    if (thiSinhData.isPresent()) {
                        ts.setId(thiSinhData.get().getId());
                    }
                    else {
                        ts.setThoiGianTao(System.currentTimeMillis());
                    }
                    ts.setThoiGianCapNhat(System.currentTimeMillis());

                    ThiSinh thiSinhMoi = thiSinhRepository.save(ts);
                    if (ts.isThamGia()) {
                        DanhSachThi danhSachThi = new DanhSachThi(thiSinhMoi.getId(), ts.getCuocThi(), khoiThi != null ? khoiThi.getId() : null, null, null, null, null, null, null, null);
                        Optional<DanhSachThi> oldDstData = danhSachThiRepository.findByThiSinhIdAndCuocThiIdAndKhoiThiIdAndDoiThiId(thiSinhMoi.getId(), thiSinhMoi.getCuocThi().getId(), khoiThi != null ? khoiThi.getId() : null, null);
                        if (oldDstData.isPresent()) {
                            danhSachThi.setDanhSachThiId(oldDstData.get().getDanhSachThiId());
                        }

                        danhSachThiRepository.save(danhSachThi);
                    }
                }
                else if (khoiThi != null && khoiThi.getThiTapThe()) {
                    DoanThi doanThi = null;
                    for (DoanThi dt : doanthis) {
                        if (dt.getTenGoi().equals(ts.getTenDoanThi())) {
                            doanThi = dt;
                            break;
                        }
                    }
                    Optional<DoiThi> doiThiData = doiThiRepository.findByTenGoiAndCuocThiIdAndDoanThiIdAndKhoiThiId(ts.getTenDoiThi(), ts.getCuocThi() != null ? ts.getCuocThi().getId() : null, doanThi != null ? doanThi.getId() : null, khoiThi != null ? khoiThi.getId() : null);
                    log.info("Thi Sinh: " + ts.getHoTen());
                    Optional<ThiSinh> thiSinhData = thiSinhRepository.findByHoTenAndGioiTinhAndNgaySinhAndNganhDaoTao(ts.getHoTen(), ts.getGioiTinh(), ts.getNgaySinh(), ts.getNganhDaoTao());
                    if (thiSinhData.isPresent()) {
                        ts.setId(thiSinhData.get().getId());
                    }
                    else {
                        ts.setThoiGianTao(System.currentTimeMillis());
                    }
                    ts.setThoiGianCapNhat(System.currentTimeMillis());

                    ThiSinh thiSinhMoi = thiSinhRepository.save(ts);
                    if (ts.isThamGia()) {
                        DoiThi doiThi = new DoiThi(ts.getTenDoiThi(), ts.getCuocThi(), doanThi != null ? doanThi.getId() : null, khoiThi != null ? khoiThi.getId() : null, null, null, null, null, null, null);
                        if (doiThiData.isPresent()) {
                            doiThi.setId(doiThiData.get().getId());
                        }
                        if (doiThi.getTenGoi() == null || doiThi.getTenGoi().isEmpty()) {
                            doiThi.setTenGoi(RandomUtil.generateRandomAlphanumeric(16));
                        }
                        DoiThi doiThiMoi = doiThiRepository.save(doiThi);

                        DanhSachThi danhSachThi = new DanhSachThi(thiSinhMoi.getId(), ts.getCuocThi(), khoiThi != null ? khoiThi.getId() : null, doiThiMoi.getId(), null, null, null, null, null, null);
                        Optional<DanhSachThi> oldDstData = danhSachThiRepository.findByThiSinhIdAndCuocThiIdAndKhoiThiIdAndDoiThiId(thiSinhMoi.getId(), thiSinhMoi.getCuocThi().getId(), khoiThi != null ? khoiThi.getId() : null, doiThiData.isPresent() ? doiThiData.get().getId() : null);
                        if (oldDstData.isPresent()) {
                            danhSachThi.setDanhSachThiId(oldDstData.get().getDanhSachThiId());
                        }

                        danhSachThiRepository.save(danhSachThi);
                    }
                }
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
            
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
    } 
}