package vn.iodev.iosecurity.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

import lombok.extern.slf4j.Slf4j;
import vn.iodev.iosecurity.IOConstants;
import vn.iodev.iosecurity.model.ELoaiTaiKhoan;
import vn.iodev.iosecurity.model.EVaiTro;
import vn.iodev.iosecurity.model.IOUserDetails;
import vn.iodev.iosecurity.model.LoaiTinhTrang;
import vn.iodev.iosecurity.model.MailQueue;
import vn.iodev.iosecurity.model.QueueStatus;
import vn.iodev.iosecurity.model.TaiKhoan;
import vn.iodev.iosecurity.model.VaiTro;
import vn.iodev.iosecurity.payload.CaNhanRequest;
import vn.iodev.iosecurity.payload.CaNhanResponse;
import vn.iodev.iosecurity.payload.TaiKhoanRequest;
import vn.iodev.iosecurity.payload.ToChucRequest;
import vn.iodev.iosecurity.payload.ToChucResponse;
import vn.iodev.iosecurity.repository.MailQueueRepository;
import vn.iodev.iosecurity.repository.TaiKhoanRepository;
import vn.iodev.iosecurity.repository.VaiTroRepository;
import vn.iodev.iosecurity.service.EmailService;
import vn.iodev.iosecurity.service.HumanResourceService;
import vn.iodev.iosecurity.service.ThymeleafService;
import vn.iodev.iosecurity.utils.RandomUtil;

@RestController
@RequestMapping("/api")
@Slf4j
public class TaiKhoanController {
    @Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Autowired
    HumanResourceService humanResourceService;

    @Autowired
    EmailService emailService;

    @Value("${io.app.activecode.length:8}")
    private int activeCodeLength;

    @Value("${io.app.active.expired:24}")
    private int activeExpired;

    @Autowired
    ThymeleafService thymeleafService;

    @Autowired
    MailQueueRepository mailQueueRepository;

    @GetMapping("/taikhoans")
    public ResponseEntity<List<TaiKhoan>> getAllTaiKhoans(@RequestParam(required = false) String email, @RequestParam(required = false) Integer tinhTrang) {
        log.info("API GET /taikhoans");
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
            log.debug("API GET /taikhoans", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/taikhoans/{email}")
    public ResponseEntity<TaiKhoan> getTaiKhoanById(@PathVariable("email") String email, Authentication authentication) {
        log.info("API GET /taikhoans/{email}");
        IOUserDetails userDetails = (IOUserDetails)authentication.getPrincipal();
        boolean isAdmin = false;
        for (GrantedAuthority sga : authentication.getAuthorities()) {
            if (sga.getAuthority().equals(EVaiTro.VAITRO_QUANTRIHETHONG.toString())) {
                isAdmin = true;
                break;
            }
        }
        if (!userDetails.getUsername().equals(email) && !isAdmin) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);
        if (taiKhoanData.isPresent()) {
            return new ResponseEntity<>(taiKhoanData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/taikhoans")
    public ResponseEntity<TaiKhoan> createTaiKhoan(@Valid @RequestBody TaiKhoan taiKhoan) {
        log.info("API POST /taikhoans");
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
                        ToChucResponse toChuc = humanResourceService.getToChucById(taiKhoan.getId());
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
            taiKhoanMoi.setMaKichHoat(RandomUtil.generateRandomAlphanumeric(activeCodeLength));
            long nowTime = System.currentTimeMillis();
            taiKhoanMoi.setThoiHanKichHoat(nowTime + activeExpired * 60 * 60 * 1000);

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
            HashMap<String, Object> activeVariables = new HashMap<>();
            activeVariables.put("MaKichHoat", taiKhoan.getMaKichHoat());
                
            String msgBody = thymeleafService.getContent(IOConstants.ACTIVE_USER_MAIL_TEMPLATE, activeVariables);
            MailQueue mailQueue = new MailQueue(_taiKhoan.getEmail(), msgBody, IOConstants.ACTIVE_USER_MAIL_SUBJECT, "", QueueStatus.WAITED, 0);
            mailQueueRepository.save(mailQueue);
            
            // emailService.sendActiveUserHtmlMail(_taiKhoan);

            return new ResponseEntity<>(_taiKhoan, HttpStatus.CREATED);
        } catch (Exception e) {
            log.debug("API POST /taikhoans", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/taikhoans/{id}")
    public ResponseEntity<TaiKhoan> updateTaiKhoan(@PathVariable("id") String email, @Valid @RequestBody TaiKhoan taiKhoan, Authentication authentication) {
        log.info("API PUT /taikhoans/{id}");
        IOUserDetails userDetails = (IOUserDetails)authentication.getPrincipal();
        boolean isAdmin = false;
        for (GrantedAuthority sga : authentication.getAuthorities()) {
            if (sga.getAuthority().equals(EVaiTro.VAITRO_QUANTRIHETHONG.toString())) {
                isAdmin = true;
                break;
            }
        }
        if (!userDetails.getUsername().equals(email) && !isAdmin) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

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
        log.info("API DELETE /taikhoans/{email}");
        try {
            Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);
            if (taiKhoanData.isPresent()) {
                TaiKhoan taiKhoan = taiKhoanData.get();
                for (VaiTro vaiTro : taiKhoan.getVaiTros()) {
                    taiKhoan.removeVaiTro(vaiTro);
                }
                taiKhoanRepository.delete(taiKhoan);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            log.debug("API DELETE /taikhoans/{email}", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/taikhoans/{id}/kichhoat")
    public ResponseEntity<TaiKhoan> updateKichHoat(@PathVariable("id") String email, Authentication authentication) {
        log.info("API PUT /taikhoans/{id}/kichhoat");
        boolean isAdmin = false;
        for (GrantedAuthority sga : authentication.getAuthorities()) {
            if (sga.getAuthority().equals(EVaiTro.VAITRO_QUANTRIHETHONG.toString())) {
                isAdmin = true;
                break;
            }
        }
        if (!isAdmin) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);
        log.info("User email: " + email);
        if (taiKhoanData.isPresent()) {
            TaiKhoan _taiKhoan = taiKhoanData.get();
            _taiKhoan.setTinhTrang(LoaiTinhTrang.DA_KICH_HOAT);
            return new ResponseEntity<>(taiKhoanRepository.save(_taiKhoan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/auth/{id}/verify-email")
    public ResponseEntity<TaiKhoan> verifyEmail(@PathVariable("id") String email, @RequestParam("maKichHoat") String maKichHoat) {
        log.info("API PUT /auth/{id}/verify-email");
        Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);

        if (taiKhoanData.isPresent()) {
            TaiKhoan _taiKhoan = taiKhoanData.get();
            long now = System.currentTimeMillis();
            if (_taiKhoan.getMaKichHoat().equals(maKichHoat) && now < _taiKhoan.getThoiHanKichHoat()) {
                _taiKhoan.setTinhTrang(LoaiTinhTrang.DA_KICH_HOAT);
                return new ResponseEntity<>(taiKhoanRepository.save(_taiKhoan), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<TaiKhoan> dangKyTaiKhoan(@Valid @RequestBody TaiKhoanRequest taiKhoan) {
        log.info("API POST /auth/register");
        try {
            boolean isOrganizeAdmin = false;
            String id = null;

            if (taiKhoan.getEmail() != null) {
                if (taiKhoan.getLoaiTaiKhoan() != null) {
                    if (taiKhoan.getLoaiTaiKhoan() == ELoaiTaiKhoan.TAIKHOAN_CANHAN) {    
                        try {                 
                            CaNhanResponse caNhan = humanResourceService.getCaNhanByEmail(taiKhoan.getEmail());
                            if (caNhan.getId() != null) {
                                Optional<TaiKhoan> tkCuData = taiKhoanRepository.findById(caNhan.getId());
                                if (tkCuData.isPresent()) {
                                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                                }
                            }
                        }
                        catch (Exception e1) {
                            log.debug("API POST /auth/register", e1);
                            CaNhanRequest request = new CaNhanRequest();
                            request.setEmail(taiKhoan.getEmail());
                            request.setGioiTinh(0);
                            request.setNgaySinh(new Date(0));
                            request.setHoTen(taiKhoan.getEmail());
                            try {
                                CaNhanResponse result = humanResourceService.createCaNhan(request);
                                id = result.getId();
                            }
                            catch (Exception e2) {
                                log.debug("API POST /auth/register", e2);
                                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                            }                        
                        }
                    }
                    else if (taiKhoan.getLoaiTaiKhoan() == ELoaiTaiKhoan.TAIKHOAN_TOCHUC) {
                        ToChucResponse toChuc = null;
                        try {
                            toChuc = humanResourceService.getToChucByEmail(taiKhoan.getEmail());
                        }
                        catch (Exception e) {
                            log.debug("API POST /auth/register", e);
                        }
                        if (toChuc != null && toChuc.getId() != null) {
                            id = toChuc.getId();
                        }
                        else {
                            isOrganizeAdmin = true;

                            if (toChuc != null && toChuc.getEmail().equals(taiKhoan.getEmail())) {
                            }
                            else {
                                ToChucRequest request = new ToChucRequest();
                                request.setEmail(taiKhoan.getEmail());
                                request.setTenGoi(taiKhoan.getEmail());
                                request.setLoaiToChuc("");
                                try {
                                    ToChucResponse result = humanResourceService.createToChuc(request);
                                    id = result.getId();
                                }
                                catch (Exception e) {
                                    log.debug("API POST /auth/register", e);
                                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                                }
                            }
                        }
                    }
                }
                else {
                    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                TaiKhoan taiKhoanWithEmail = taiKhoanRepository.findByEmail(taiKhoan.getEmail());
                if (taiKhoanWithEmail != null) {
                    return new ResponseEntity<>(null, HttpStatus.CONFLICT);
                }
            }
            TaiKhoan taiKhoanMoi = new TaiKhoan(taiKhoan.getEmail(), id, taiKhoan.getLoaiTaiKhoan(), passwordEncoder.encode(taiKhoan.getMatKhau()));
            taiKhoanMoi.setMaKichHoat(RandomUtil.generateRandomAlphanumeric(activeCodeLength));
            long nowTime = System.currentTimeMillis();
            taiKhoanMoi.setThoiHanKichHoat(nowTime + activeExpired * 60 * 60 * 1000);

            Optional<VaiTro> vtNdData = vaiTroRepository.findByTen(EVaiTro.VAITRO_NGUOIDUNG);
            Set<VaiTro> vaiTros = new HashSet<>();

            if (vtNdData.isPresent()) {
                vaiTros.add(vtNdData.get());
            }
            if (isOrganizeAdmin) {
                Optional<VaiTro> vtAdminData = vaiTroRepository.findByTen(EVaiTro.VAITRO_QUANTRITOCHUC);
                if (vtAdminData.isPresent()) {
                    vaiTros.add(vtAdminData.get());
                }
            }

            taiKhoanMoi.setVaiTros(vaiTros);

            TaiKhoan _taiKhoan = taiKhoanRepository
                .save(taiKhoanMoi);
            HashMap<String, Object> activeVariables = new HashMap<>();
            activeVariables.put("MaKichHoat", taiKhoanMoi.getMaKichHoat());
                
            String msgBody = thymeleafService.getContent(IOConstants.ACTIVE_USER_MAIL_TEMPLATE, activeVariables);
            MailQueue mailQueue = new MailQueue(_taiKhoan.getEmail(), msgBody, IOConstants.ACTIVE_USER_MAIL_SUBJECT, "", QueueStatus.WAITED, 0);
            mailQueueRepository.save(mailQueue);
            
            // emailService.sendActiveUserHtmlMail(_taiKhoan);

            return new ResponseEntity<>(_taiKhoan, HttpStatus.CREATED);
        } catch (Exception e) {
            log.debug("API POST /auth/register", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/taikhoans/{id}/dongtaikhoan")
    public ResponseEntity<TaiKhoan> dongTaiKhoan(@PathVariable("id") String email, Authentication authentication) {
        log.info("API PUT /taikhoans/{id}/dongtaikhoan");
        boolean isAdmin = false;
        for (GrantedAuthority sga : authentication.getAuthorities()) {
            if (sga.getAuthority().equals(EVaiTro.VAITRO_QUANTRIHETHONG.toString())) {
                isAdmin = true;
                break;
            }
        }
        if (!isAdmin) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<TaiKhoan> taiKhoanData = taiKhoanRepository.findById(email);

        if (taiKhoanData.isPresent()) {
            TaiKhoan _taiKhoan = taiKhoanData.get();
            _taiKhoan.setTinhTrang(LoaiTinhTrang.DONG_TAI_KHOAN);

            return new ResponseEntity<>(taiKhoanRepository.save(_taiKhoan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
