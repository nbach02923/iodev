package vn.iodev.contestmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

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
import vn.iodev.contestmanagementsystem.repository.CuocThiRepository;
import vn.iodev.contestmanagementsystem.security.VaiTroChecker;

@RestController
@RequestMapping("/api")
public class CuocThiController {
    @Autowired
    CuocThiRepository cuocThiRepository;

    @GetMapping("/cuocthis")
    public List<CuocThi> getAllCuocThis(@RequestParam(defaultValue = "1") int page, 
            @RequestParam(defaultValue = "15") int size, 
            @RequestParam(defaultValue = "", required = false) String tuKhoa, 
            @RequestParam(required = false) String serieCuocThi, 
            @RequestParam(required = false) Integer lanToChuc, 
            @RequestParam(required = false) String toChucId) {
        Pageable paging = PageRequest.of(page - 1, size);
        List<CuocThi> cuocThis;
        cuocThis = cuocThiRepository.findToChucByMultipleConditions(tuKhoa, serieCuocThi, lanToChuc, toChucId, paging);
        return cuocThis;
    }

    @GetMapping("/cuocthis/{id}")
    public ResponseEntity<CuocThi> getCuocThiById(@PathVariable(value = "id") String cuocThiId)
        throws ResourceNotFoundException {
        CuocThi cuocThi = cuocThiRepository.findById(cuocThiId)
          .orElseThrow(() -> new ResourceNotFoundException("CuocThi not found for this id :: " + cuocThiId));
        return ResponseEntity.ok().body(cuocThi);
    }

    @PostMapping("/cuocthis")
    public ResponseEntity<CuocThi> createCuocThi(
        @RequestBody CuocThi cuocThi,
        @RequestHeader("id") String id,
        @RequestHeader("email") String email,
        @RequestHeader("vaiTros") String vaiTros) {
        try {
            if (VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
                CuocThi _cuocThi = cuocThiRepository.save(new CuocThi(cuocThi.getTenGoi(), 
                                                            cuocThi.getTiengAnh(), 
                                                            cuocThi.getSerieCuocThi(), 
                                                            cuocThi.getLanToChuc(), 
                                                            cuocThi.getDonViToChuc(), 
                                                            cuocThi.getToChucId(), 
                                                            cuocThi.getNgayBatDau(), 
                                                            cuocThi.getNgayKetThuc(), 
                                                            cuocThi.getThongTinMoTa(), 
                                                            cuocThi.getWebsite(), 
                                                            cuocThi.getHinhAnh()));
                return new ResponseEntity<>(_cuocThi, HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cuocthis/{id}")
    public ResponseEntity<CuocThi> updateCuocThi(@PathVariable("id") String id, @RequestBody CuocThi cuocThi,
        @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Optional<CuocThi> cuocThiData = cuocThiRepository.findById(id);
        if (cuocThiData.isPresent()) {
            CuocThi _cuocThi = cuocThiData.get();
            if (cuocThi.getTenGoi() != null) {
                _cuocThi.setTenGoi(cuocThi.getTenGoi());
            }
            if (cuocThi.getTiengAnh() != null) {
                _cuocThi.setTiengAnh(cuocThi.getTiengAnh());
            }
            if (cuocThi.getSerieCuocThi() != null) {
                _cuocThi.setSerieCuocThi(cuocThi.getSerieCuocThi());
            }
            if (cuocThi.getLanToChuc() != null) {
                _cuocThi.setLanToChuc(cuocThi.getLanToChuc());
            }
            if (cuocThi.getDonViToChuc() != null) {
                _cuocThi.setDonViToChuc(cuocThi.getDonViToChuc());
            }
            if (cuocThi.getToChucId() != null) {
                _cuocThi.setToChucId(cuocThi.getToChucId());
            }
            if (cuocThi.getNgayBatDau() != null) {
                _cuocThi.setNgayBatDau(cuocThi.getNgayBatDau());
            }
            if (cuocThi.getNgayKetThuc() != null) {
                _cuocThi.setNgayKetThuc(cuocThi.getNgayKetThuc());
            }
            if (cuocThi.getThongTinMoTa() != null) {
                _cuocThi.setThongTinMoTa(cuocThi.getThongTinMoTa());
            }
            if (cuocThi.getWebsite() != null) {
                _cuocThi.setWebsite(cuocThi.getWebsite());
            }
            if (cuocThi.getHinhAnh() != null) {
                _cuocThi.setHinhAnh(cuocThi.getHinhAnh());
            }
            if (cuocThi.getTinhTrang() != null) {
                _cuocThi.setTinhTrang(cuocThi.getTinhTrang());
            }
            
            return new ResponseEntity<>(cuocThiRepository.save(_cuocThi), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cuocthis/{id}")
    public ResponseEntity<HttpStatus> deleteCuocThi(@PathVariable("id") String id, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        try {
            cuocThiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
