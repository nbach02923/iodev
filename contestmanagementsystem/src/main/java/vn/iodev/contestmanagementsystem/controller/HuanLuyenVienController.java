package vn.iodev.contestmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.config.validation.ValidationException;

import lombok.extern.slf4j.Slf4j;
import vn.iodev.contestmanagementsystem.exception.ResourceNotFoundException;
import vn.iodev.contestmanagementsystem.model.CuocThi;
import vn.iodev.contestmanagementsystem.model.DoanThi;
import vn.iodev.contestmanagementsystem.model.HuanLuyenVien;
import vn.iodev.contestmanagementsystem.model.KhoiThi;
import vn.iodev.contestmanagementsystem.repository.CuocThiRepository;
import vn.iodev.contestmanagementsystem.repository.DoanThiRepository;
import vn.iodev.contestmanagementsystem.repository.HuanLuyenVienRepository;
import vn.iodev.contestmanagementsystem.repository.KhoiThiRepository;
import vn.iodev.contestmanagementsystem.security.VaiTroChecker;
import vn.iodev.contestmanagementsystem.service.CaNhanService;
import vn.iodev.contestmanagementsystem.service.ExcelService;

@RestController
@RequestMapping("/api")
@Slf4j
public class HuanLuyenVienController {
    @Autowired
    HuanLuyenVienRepository huanLuyenVienRespository;

    @Autowired
    CuocThiRepository cuocThiRepository;

    @Autowired
    DoanThiRepository doanThiRepository;

    @Autowired
    KhoiThiRepository khoiThiRepository;

    @Autowired
    ExcelService fileService;

    @Autowired
    CaNhanService caNhanService;

    @GetMapping("/cuocthis/{cuocThiId}/huanluyenviens")
    public ResponseEntity<List<HuanLuyenVien>> getAllHuanLuyenViensByCuocThiId(@PathVariable(value = "cuocThiId") String cuocThiId, @RequestHeader("id") String taiKhoanId, @RequestHeader("loaiTaiKhoan") Integer loaiTaiKhoan, @RequestHeader("vaiTros") String vaiTros) {
        log.info("Inside find huanluyenvien of cuocthi");
        if (!VaiTroChecker.hasVaiTroQuanTriToChuc(vaiTros) && !VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        List<HuanLuyenVien> lstHuanLuyenVien = new ArrayList<>();

        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(lstHuanLuyenVien, HttpStatus.OK);
        }
        if (VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            List<HuanLuyenVien> huanLuyenViens = huanLuyenVienRespository.findByCuocThiId(cuocThiId);
            lstHuanLuyenVien.addAll(huanLuyenViens);
        }
        else if (taiKhoanId != null && !taiKhoanId.isEmpty()) {
            List<DoanThi> lstDoanThi = doanThiRepository.findByToChucIdAndCuocThiId(taiKhoanId, cuocThiId);
            for (DoanThi dt : lstDoanThi) {
                lstHuanLuyenVien.addAll(huanLuyenVienRespository.findByCuocThiIdAndDoanThiId(cuocThiId, dt.getId()));
            }
        }
        return new ResponseEntity<>(lstHuanLuyenVien, HttpStatus.OK);
    }

    @GetMapping("/huanluyenviens")
    public List<HuanLuyenVien> getAllHuanLuyenViens(@RequestParam(defaultValue = "1") int page, @RequestParam(name = "tuKhoa", defaultValue = "") String tuKhoa, @RequestParam(defaultValue = "15") int size, @RequestParam(required = false) String cuocThiId, @RequestParam(required = false) String khoiThiId, @RequestParam(required = false) String doanThiId) {
        Pageable paging = PageRequest.of(page - 1, size);
        Optional<CuocThi> cuocThiData = cuocThiRepository.findById(cuocThiId);
        if (cuocThiData.isPresent()) {
            return huanLuyenVienRespository.findHuanLuyenVienByMultipleConditions(tuKhoa, cuocThiData.get(), khoiThiId, doanThiId, paging);
        }
        else {
            return huanLuyenVienRespository.findHuanLuyenVienByMultipleConditions(tuKhoa, null, khoiThiId, doanThiId, paging);
        }
    }

    @GetMapping("/huanluyenviens/{id}")
    public ResponseEntity<HuanLuyenVien> getHuanLuyenVienById(@PathVariable(value = "id") String huanLuyenVienId)
        throws ResourceNotFoundException {
        HuanLuyenVien huanLuyenVien = huanLuyenVienRespository.findById(huanLuyenVienId)
          .orElseThrow(() -> new ResourceNotFoundException("HuanLuyenVien not found for this id :: " + huanLuyenVienId));
        return ResponseEntity.ok().body(huanLuyenVien);
    }

    private void validateRelationConstraint(HuanLuyenVien huanLuyenVien) throws Exception {
        if (huanLuyenVien.getCaNhanId() != null) {
            caNhanService.getCaNhanById(huanLuyenVien.getCaNhanId());
        }
        if (huanLuyenVien.getDoanThiId() != null) {
            Optional<DoanThi> doanThi = doanThiRepository.findById(huanLuyenVien.getDoanThiId());
            if (!doanThi.isPresent()) {
                throw new ValidationException("DoanThi is not exists!");
            }
        }
        if (huanLuyenVien.getKhoiThiId() != null) {
            Optional<KhoiThi> khoiThi = khoiThiRepository.findById(huanLuyenVien.getKhoiThiId());
            if (!khoiThi.isPresent()) {
                throw new ValidationException("KhoiThi is not exists!");
            }
        }
    }

    @PostMapping("/cuocthis/{cuocThiId}/huanluyenviens")
    public ResponseEntity<HuanLuyenVien> createHuanLuyenVienOfCuocThi(@PathVariable(value = "cuocThiId") String cuocThiId, @Valid @RequestBody HuanLuyenVien huanLuyenVien, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        try {
            validateRelationConstraint(huanLuyenVien);
            HuanLuyenVien huanLuyenVienMoi = cuocThiRepository.findById(cuocThiId).map(cuocThi -> {
                huanLuyenVien.setCuocThi(cuocThi);
                return huanLuyenVienRespository.save(huanLuyenVien);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found CuocThi with id = " + cuocThiId));

            return new ResponseEntity<>(huanLuyenVienMoi, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

    // @PostMapping("/huanluyenviens")
    // public ResponseEntity<HuanLuyenVien> createHuanLuyenVien(@RequestBody HuanLuyenVien huanLuyenVien) {
    //     try {
    //         HuanLuyenVien _huanLuyenVien = huanLuyenVienRespository.save(new HuanLuyenVien(huanLuyenVien.getHoTen(), huanLuyenVien.getEmail(), huanLuyenVien.getSoDienThoai(), huanLuyenVien.getCaNhanId(), huanLuyenVien.getCuocThi(), huanLuyenVien.getDoanThiId(), huanLuyenVien.getKhoiThiId(), huanLuyenVien.getTruongPhoDoan()));
    //         return new ResponseEntity<>(_huanLuyenVien, HttpStatus.CREATED);
    //     }
    //     catch (Exception e) {
    //         return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    //     }
    // }

    @PutMapping("/huanluyenviens/{id}")
    public ResponseEntity<HuanLuyenVien> updateHuanLuyenVien(@PathVariable("id") String id, @RequestBody HuanLuyenVien huanLuyenVien) {
        Optional<HuanLuyenVien> huanLuyenVienData = huanLuyenVienRespository.findById(id);
        if (huanLuyenVienData.isPresent()) {
            try {
                validateRelationConstraint(huanLuyenVien);
                
                HuanLuyenVien _huanLuyenVien = huanLuyenVienData.get();
                if (huanLuyenVien.getHoTen() != null) {
                    _huanLuyenVien.setHoTen(huanLuyenVien.getHoTen());
                }
                if (huanLuyenVien.getEmail() != null) {
                    _huanLuyenVien.setEmail(huanLuyenVien.getEmail());
                }
                if (huanLuyenVien.getSoDienThoai() != null) {
                    _huanLuyenVien.setSoDienThoai(huanLuyenVien.getSoDienThoai());
                }
                if (huanLuyenVien.getCaNhanId() != null) {
                    _huanLuyenVien.setCaNhanId(huanLuyenVien.getCaNhanId());
                }
                if (huanLuyenVien.getDoanThiId() != null) {
                    _huanLuyenVien.setDoanThiId(huanLuyenVien.getDoanThiId());
                }
                if (huanLuyenVien.getKhoiThiId() != null) {
                    _huanLuyenVien.setKhoiThiId(huanLuyenVien.getKhoiThiId());
                }
                if (huanLuyenVien.getTruongPhoDoan() != null) {
                    _huanLuyenVien.setTruongPhoDoan(huanLuyenVien.getTruongPhoDoan());
                }
                
                _huanLuyenVien.setThoiGianCapNhat(System.currentTimeMillis());

                return new ResponseEntity<>(huanLuyenVienRespository.save(_huanLuyenVien), HttpStatus.OK);
            }
            catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/huanluyenviens/{id}")
    public ResponseEntity<HttpStatus> deleteHuanLuyenVien(@PathVariable("id") String id) {
        try {
            huanLuyenVienRespository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cuocthis/{cuocThiId}/huanluyenviens")
    public ResponseEntity<List<HuanLuyenVien>> deleteAllHuanLuyenViensOfCuocThi(@PathVariable(value = "cuocThiId") String cuocThiId, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        huanLuyenVienRespository.deleteByCuocThiId(cuocThiId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
