package vn.iodev.contestmanagementsystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import vn.iodev.contestmanagementsystem.model.KhoiThi;
import vn.iodev.contestmanagementsystem.repository.CuocThiRepository;
import vn.iodev.contestmanagementsystem.repository.KhoiThiRepository;
import vn.iodev.contestmanagementsystem.security.VaiTroChecker;

@RestController
@RequestMapping("/api")
public class KhoiThiController {
    @Autowired
    KhoiThiRepository khoiThiRepository;

    @Autowired
    CuocThiRepository cuocThiRepository;

    @GetMapping("/cuocthis/{cuocThiId}/khoithis")
    public ResponseEntity<List<KhoiThi>> getAllKhoiThisByCuocThiId(@PathVariable(value = "cuocThiId") String cuocThiId) {
        List<KhoiThi> lstKhoiThi = new ArrayList<>();

        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(lstKhoiThi, HttpStatus.OK);
        }
        List<KhoiThi> khoiThis = khoiThiRepository.findByCuocThiId(cuocThiId);
        lstKhoiThi.addAll(khoiThis);

        return new ResponseEntity<>(lstKhoiThi, HttpStatus.OK);
    }

    @PostMapping("/cuocthis/{cuocThiId}/khoithis")
    public ResponseEntity<KhoiThi> createKhoiThiOfCuocThi(@PathVariable(value = "cuocThiId") String cuocThiId, @Valid @RequestBody KhoiThi khoiThi, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        try {
            KhoiThi khoiThiMoi = cuocThiRepository.findById(cuocThiId).map(cuocThi -> {
                khoiThi.setCuocThi(cuocThi);
                return khoiThiRepository.save(khoiThi);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found CuocThi with id = " + cuocThiId));

            return new ResponseEntity<>(khoiThiMoi, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

    @GetMapping("/khoithis")
    public List<KhoiThi> getAllKhoiThis(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int size, @RequestParam(required = false) String cuocThiId) {
        Pageable paging = PageRequest.of(page - 1, size);
        if (cuocThiId == null) {
            Page<KhoiThi> khoiThis;
            khoiThis = khoiThiRepository.findAll(paging);
            return khoiThis.getContent();
        }
        else {
            return khoiThiRepository.findByCuocThiId(cuocThiId, paging);
        }
    }

    @GetMapping("/khoithis/{id}")
    public ResponseEntity<KhoiThi> getKhoiThiById(@PathVariable(value = "id") String khoiThiId)
        throws ResourceNotFoundException {
        KhoiThi khoiThi = khoiThiRepository.findById(khoiThiId)
          .orElseThrow(() -> new ResourceNotFoundException("KhoiThi not found for this id :: " + khoiThiId));
        return ResponseEntity.ok().body(khoiThi);
    }

    @PostMapping("/khoithis")
    public ResponseEntity<KhoiThi> createKhoiThi(@Valid @RequestBody KhoiThi khoiThi, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        try {
            KhoiThi _khoiThi = khoiThiRepository.save(new KhoiThi(khoiThi.getTenGoi(), khoiThi.getCuocThi(), khoiThi.getNoiDungThi(), khoiThi.getVongSoLoai(), khoiThi.getThiTapThe(), khoiThi.getThiSangTao(), khoiThi.getMaxDangKi(), khoiThi.getMaxThiSinh()));
            return new ResponseEntity<>(_khoiThi, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/khoithis/{id}")
    public ResponseEntity<KhoiThi> updateKhoiThi(@PathVariable("id") String id, @Valid @RequestBody KhoiThi khoiThi, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<KhoiThi> khoiThiData = khoiThiRepository.findById(id);
        if (khoiThiData.isPresent()) {
            KhoiThi _khoiThi = khoiThiData.get();
            if (khoiThi.getTenGoi() != null) {
                _khoiThi.setTenGoi(khoiThi.getTenGoi());
            }
            if (khoiThi.getNoiDungThi() != null) {
                _khoiThi.setNoiDungThi(khoiThi.getNoiDungThi());
            }
            if (khoiThi.getVongSoLoai() != null) {
                _khoiThi.setVongSoLoai(khoiThi.getVongSoLoai());
            }
            if (khoiThi.getThiTapThe() != null) {
                _khoiThi.setThiTapThe(khoiThi.getThiTapThe());
            }
            if (khoiThi.getThiSangTao() != null) {
                _khoiThi.setThiSangTao(khoiThi.getThiSangTao());
            }
            if (khoiThi.getMaxDangKi() != null) {
                _khoiThi.setMaxDangKi(khoiThi.getMaxDangKi());
            }
            if (khoiThi.getMaxThiSinh() != null) {
                _khoiThi.setMaxThiSinh(khoiThi.getMaxThiSinh());
            }
            if (khoiThi.getTinhTrang() != null) {
                _khoiThi.setTinhTrang(khoiThi.getTinhTrang());
            }
            _khoiThi.setThoiGianCapNhat(System.currentTimeMillis());
            
            return new ResponseEntity<>(khoiThiRepository.save(_khoiThi), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/khoithis/{id}")
    public ResponseEntity<HttpStatus> deleteKhoiThi(@PathVariable("id") String id, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<HttpStatus>(HttpStatus.FORBIDDEN);
        }
        try {
            khoiThiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cuocthis/{cuocThiId}/khoithis")
    public ResponseEntity<List<KhoiThi>> deleteAllKhoiThisOfCuocThi(@PathVariable(value = "cuocThiId") String cuocThiId, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        khoiThiRepository.deleteByCuocThiId(cuocThiId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}