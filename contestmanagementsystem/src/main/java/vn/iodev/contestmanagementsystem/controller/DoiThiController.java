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

import vn.iodev.contestmanagementsystem.exception.ResourceNotFoundException;
import vn.iodev.contestmanagementsystem.model.CuocThi;
import vn.iodev.contestmanagementsystem.model.DoiThi;
import vn.iodev.contestmanagementsystem.repository.CuocThiRepository;
import vn.iodev.contestmanagementsystem.repository.DoiThiRepository;
import vn.iodev.contestmanagementsystem.security.VaiTroChecker;

@RestController
@RequestMapping("/api")
public class DoiThiController {
    @Autowired
    DoiThiRepository doiThiRepository;

    @Autowired
    CuocThiRepository cuocThiRepository;

    @GetMapping("/doithis")
    public List<DoiThi> getAllDoiThis(@RequestParam(defaultValue = "1") int page,@RequestParam(name = "tuKhoa", defaultValue="") String tuKhoa, @RequestParam(defaultValue = "15") int size, @RequestParam(required = false) String cuocThiId, @RequestParam(required = false) String khoiThiId, @RequestParam(required = false) String doanThiId, @RequestHeader("id") String taiKhoanId, @RequestHeader("loaiTaiKhoan") Integer loaiTaiKhoan, @RequestHeader("vaiTros") String vaiTros) {
        List<DoiThi> lstDoiThi = new ArrayList<>();

        if (!VaiTroChecker.hasVaiTroQuanTriToChuc(vaiTros) && !VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return lstDoiThi;
        }

        Pageable paging = PageRequest.of(page - 1, size);
        Optional<CuocThi> cuocThiData = cuocThiRepository.findById(cuocThiId);
        return doiThiRepository.findDoiThiByMultipleConditions(tuKhoa, cuocThiData.get(), khoiThiId, doanThiId, paging);
    }

    @GetMapping("/cuocthis/{cuocThiId}/doithis")
    public ResponseEntity<List<DoiThi>> getAllDoiThisByCuocThiId(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int size, @PathVariable(value = "cuocThiId") String cuocThiId) {
        List<DoiThi> lstDoiThi = new ArrayList<>();

        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(lstDoiThi, HttpStatus.OK);
        }

        Pageable paging = PageRequest.of(page - 1, size);

        List<DoiThi> doiThis = doiThiRepository.findByCuocThiId(cuocThiId, paging);
        lstDoiThi.addAll(doiThis);

        return new ResponseEntity<>(lstDoiThi, HttpStatus.OK);
    }

    @GetMapping("/doithis/{id}")
    public ResponseEntity<DoiThi> getDoiThiById(@PathVariable(value = "id") String doiThiId)
        throws ResourceNotFoundException {
        DoiThi doiThi = doiThiRepository.findById(doiThiId)
          .orElseThrow(() -> new ResourceNotFoundException("DoiThi not found for this id :: " + doiThiId));
        return ResponseEntity.ok().body(doiThi);
    }

    @PostMapping("/cuocthis/{cuocThiId}/doithis")
    public ResponseEntity<DoiThi> createThiSinhOfCuocThi(@PathVariable(value = "cuocThiId") String cuocThiId, @Valid @RequestBody DoiThi doiThi, @RequestHeader("vaiTros") String vaiTros) {
        try {
            DoiThi doiThiMoi = cuocThiRepository.findById(cuocThiId).map(cuocThi -> {
                doiThi.setCuocThi(cuocThi);
                return doiThiRepository.save(doiThi);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found CuocThi with id = " + cuocThiId));

            return new ResponseEntity<>(doiThiMoi, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

    @PostMapping("/doithis")
    public ResponseEntity<DoiThi> createDoiThi(@RequestBody DoiThi doiThi) {
        try {
            DoiThi _doiThi = doiThiRepository.save(new DoiThi(doiThi.getTenGoi(), doiThi.getCuocThi(), doiThi.getDoanThiId(), doiThi.getKhoiThiId(), doiThi.getChuDeSangTao(), doiThi.getHuanLuyenVienId(), doiThi.getKetQuaSoLoai(), doiThi.getThuTuXepHang(), doiThi.getBangDiemThi(), doiThi.getHangGiaiThuong()));
            return new ResponseEntity<>(_doiThi, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/doithis/{id}")
    public ResponseEntity<DoiThi> updateDoiThi(@PathVariable("id") String id, @RequestBody DoiThi doiThi) {
        Optional<DoiThi> doiThiData = doiThiRepository.findById(id);
        if (doiThiData.isPresent()) {
            DoiThi _doiThi = doiThiData.get();
            if (doiThi.getTenGoi() != null) {
                _doiThi.setTenGoi(doiThi.getTenGoi());
            }
            if (doiThi.getDoanThiId() != null) {
                _doiThi.setDoanThiId(doiThi.getDoanThiId());
            }
            if (doiThi.getKhoiThiId() != null) {
                _doiThi.setKhoiThiId(doiThi.getKhoiThiId());
            }
            if (doiThi.getChuDeSangTao() != null) {
                _doiThi.setChuDeSangTao(doiThi.getChuDeSangTao());
            }
            if (doiThi.getHuanLuyenVienId() != null) {
                _doiThi.setHuanLuyenVienId(doiThi.getHuanLuyenVienId());
            }
            if (doiThi.getKetQuaSoLoai() != null) {
                _doiThi.setKetQuaSoLoai(doiThi.getKetQuaSoLoai());
            }
            if (doiThi.getThuTuXepHang() != null) {
                _doiThi.setThuTuXepHang(doiThi.getThuTuXepHang());
            }
            if (doiThi.getBangDiemThi() != null) {
                _doiThi.setBangDiemThi(doiThi.getBangDiemThi());
            }
            if (doiThi.getHangGiaiThuong() != null) {
                _doiThi.setHangGiaiThuong(doiThi.getHangGiaiThuong());
            }
            _doiThi.setThoiGianCapNhat(System.currentTimeMillis());
            
            return new ResponseEntity<>(doiThiRepository.save(_doiThi), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/doithis/{id}")
    public ResponseEntity<HttpStatus> deleteDoiThi(@PathVariable("id") String id) {
        try {
            doiThiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cuocthis/{cuocThiId}/doithis")
    public ResponseEntity<List<DoiThi>> deleteAllDoiThisOfCuocThi(@PathVariable(value = "cuocThiId") String cuocThiId, @RequestHeader("vaiTros") String vaiTros) {
        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        doiThiRepository.deleteByCuocThiId(cuocThiId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
