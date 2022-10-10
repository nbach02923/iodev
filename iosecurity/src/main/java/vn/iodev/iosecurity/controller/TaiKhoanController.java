package vn.iodev.iosecurity.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vn.iodev.iosecurity.model.ELoaiTaiKhoan;
import vn.iodev.iosecurity.model.EVaiTro;
import vn.iodev.iosecurity.model.LoaiTinhTrang;
import vn.iodev.iosecurity.model.TaiKhoan;
import vn.iodev.iosecurity.model.VaiTro;
import vn.iodev.iosecurity.payload.CaNhanResponse;
import vn.iodev.iosecurity.payload.ToChucResponse;
import vn.iodev.iosecurity.repository.TaiKhoanRepository;
import vn.iodev.iosecurity.repository.VaiTroRepository;
import vn.iodev.iosecurity.service.HumanResourceService;

@RestController
@RequestMapping("/api")
public class TaiKhoanController {
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Autowired
    HumanResourceService humanResourceService;

    @GetMapping("/taikhoans")
    public ResponseEntity<List<TaiKhoan>> getAllTaiKhoans(@RequestParam(required = false) String email, @RequestParam(required = false) Integer tinhTrang) {
        try {
            List<TaiKhoan> taiKhoans = new ArrayList<TaiKhoan>();

            if (email == null && tinhTrang == null)
                taiKhoanRepository.findAll().forEach(taiKhoans::add);
            else if (email != null && tinhTrang == null) {
                taiKhoanRepository.findByEmailContaining(email).forEach(taiKhoans::add);
            }
            else if (email != null && tinhTrang != null) {
                taiKhoanRepository.findByEmailContainingAndTinhTrang(email, tinhTrang).forEach(taiKhoans::add);
            }
            else if (email == null && tinhTrang != null) {
                taiKhoanRepository.findByTinhTrang(tinhTrang).forEach(taiKhoans::add);
            }
            if (taiKhoans.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(taiKhoans, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/taikhoans/{email}")
    public ResponseEntity<TaiKhoan> getTaiKhoanById(@PathVariable("email") String email) {
        Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);

        if (taiKhoanData.isPresent()) {
            return new ResponseEntity<>(taiKhoanData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/taikhoans")
    public ResponseEntity<TaiKhoan> createTaiKhoan(@Valid @RequestBody TaiKhoan taiKhoan) {
        try {
            if (taiKhoan.getId() != null) {
                if (taiKhoan.getLoaiTaiKhoan() != null) {
                    if (taiKhoan.getLoaiTaiKhoan() == ELoaiTaiKhoan.TAIKHOAN_CANHAN) {                     
                        CaNhanResponse caNhan = humanResourceService.getCaNhanById(taiKhoan.getId());
                        if (caNhan.getId() != null) {
                            Optional<TaiKhoan> tkCuData = taiKhoanRepository.findById(caNhan.getId());
                            if (tkCuData.isPresent()) {
                                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                            }
                        }
                        else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    }
                    else {
                        ToChucResponse toChuc = humanResourceService.getToChucId(taiKhoan.getId());
                        if (toChuc.getId() != null) {
                            Optional<TaiKhoan> tkCuData = taiKhoanRepository.findById(toChuc.getId());
                            if (tkCuData.isPresent()) {
                                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                            }
                        }
                        else {
                            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                            
                    }
                }
                else {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
            if (taiKhoan.getEmail() != null) {
                TaiKhoan taiKhoanWithEmail = taiKhoanRepository.findByEmail(taiKhoan.getEmail());
                if (taiKhoanWithEmail != null) {
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }
            TaiKhoan taiKhoanMoi = new TaiKhoan(taiKhoan.getEmail(), taiKhoan.getId(), taiKhoan.getLoaiTaiKhoan(), passwordEncoder.encode(taiKhoan.getMatKhau()));
            if (taiKhoan.getVaiTros() == null || taiKhoan.getVaiTros().size() == 0) {
                Optional<VaiTro> vtNdData = vaiTroRepository.findByTen(EVaiTro.VAITRO_NGUOIDUNG);

                Set<VaiTro> vaiTros = new HashSet<>();
                if (vtNdData.isPresent()) {
                    vaiTros.add(vtNdData.get());
                }
                taiKhoanMoi.setVaiTros(vaiTros);
            }
            else {
                for (VaiTro vt : taiKhoan.getVaiTros()) {
                    Optional<VaiTro> vtNdData = vaiTroRepository.findByTen(vt.getTen());
                    if (vtNdData.isPresent()) {
                        vt.setId(vtNdData.get().getId());
                    }
                }
                taiKhoanMoi.setVaiTros(taiKhoan.getVaiTros());
            }

            TaiKhoan _taiKhoan = taiKhoanRepository
                .save(taiKhoanMoi);
            return new ResponseEntity<>(_taiKhoan, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/taikhoans/{id}")
    public ResponseEntity<TaiKhoan> updateTaiKhoan(@PathVariable("id") String email, @Valid @RequestBody TaiKhoan taiKhoan) {
        Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);

        if (taiKhoanData.isPresent()) {
            TaiKhoan _taiKhoan = taiKhoanData.get();
            if (taiKhoan.getId() != null) {
                _taiKhoan.setId(taiKhoan.getId());
            }
            if (taiKhoan.getLoaiTaiKhoan() != null) {
                _taiKhoan.setLoaiTaiKhoan(taiKhoan.getLoaiTaiKhoan());
            }
            if (taiKhoan.getTinhTrang() != _taiKhoan.getTinhTrang()) {
                _taiKhoan.setTinhTrang(taiKhoan.getTinhTrang());
            }
            if (taiKhoan.getMatKhau() != null) {
                _taiKhoan.setMatKhau(taiKhoan.getMatKhau());
            }
            if (taiKhoan.getVaiTros() != null) {
                for (VaiTro vt : taiKhoan.getVaiTros()) {
                    Optional<VaiTro> vtNdData = vaiTroRepository.findByTen(vt.getTen());
                    if (vtNdData.isPresent()) {
                        vt.setId(vtNdData.get().getId());
                    }
                }
                _taiKhoan.setVaiTros(taiKhoan.getVaiTros());
            }
            return new ResponseEntity<>(taiKhoanRepository.save(_taiKhoan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/taikhoans/{email}")
    public ResponseEntity<HttpStatus> deleteTaiKhoan(@PathVariable("email") String email) {
        try {
            taiKhoanRepository.deleteById(email);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/taikhoans/{id}/kichhoat")
    public ResponseEntity<TaiKhoan> updateKichHoat(@PathVariable("id") String email) {
        Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);

        if (taiKhoanData.isPresent()) {
            TaiKhoan _taiKhoan = taiKhoanData.get();
            _taiKhoan.setTinhTrang(LoaiTinhTrang.DA_KICH_HOAT);
            return new ResponseEntity<>(taiKhoanRepository.save(_taiKhoan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
