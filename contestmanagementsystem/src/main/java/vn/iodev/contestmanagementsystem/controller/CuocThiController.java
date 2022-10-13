package vn.iodev.contestmanagementsystem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import vn.iodev.contestmanagementsystem.config.CMSConfiguration;
import vn.iodev.contestmanagementsystem.exception.ResourceNotFoundException;
import vn.iodev.contestmanagementsystem.model.CuocThi;
import vn.iodev.contestmanagementsystem.model.FileIO;
import vn.iodev.contestmanagementsystem.payload.FileIOResponse;
import vn.iodev.contestmanagementsystem.payload.ResponseMessage;
import vn.iodev.contestmanagementsystem.repository.CuocThiRepository;
import vn.iodev.contestmanagementsystem.repository.FileIORepository;
import vn.iodev.contestmanagementsystem.security.VaiTroChecker;
import vn.iodev.contestmanagementsystem.service.FileStorageService;

@RestController
@RequestMapping("/api")
public class CuocThiController {
    @Autowired
    CuocThiRepository cuocThiRepository;

    @Autowired
    FileStorageService storageService;

    @Autowired
    FileIORepository fileIORepository;

    @Autowired
    CMSConfiguration configuration;

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
                                                            cuocThi.getWebsite()));
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

    @PutMapping("/cuocthis/{id}/hinhanhs")
    public ResponseEntity<ResponseMessage> updateHinhAnhCuocThi(@PathVariable("id") String id, @RequestParam("hinhAnhs") MultipartFile[] hinhAnhFiles,
        @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Optional<CuocThi> cuocThiData = cuocThiRepository.findById(id);
        if (cuocThiData.isPresent()) {
            CuocThi _cuocThi = cuocThiData.get();
            for (MultipartFile hinhAnhFile : hinhAnhFiles) {
                try {
                    FileIO fileIO = storageService.store(hinhAnhFile, _cuocThi);
                    fileIORepository.save(fileIO);
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
          
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Update hinhAnhs successfully!"));
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/cuocthis/{id}/hinhanhs")
    public ResponseEntity<List<FileIOResponse>> getHinhAnhsCuocThi(@PathVariable("id") String id,
        @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Optional<CuocThi> cuocThiData = cuocThiRepository.findById(id);
        String baseUrl = configuration.getGatewayUrl();

        if (cuocThiData.isPresent()) {
            List<FileIOResponse> files = storageService.getAllFilesByCuocThiId(id).map(fileIO -> {
                String fileDownloadUri = String.format("%s/api/files/cuocthis/%s/hinhanhs/%s", baseUrl, id, fileIO.getId());
          
                return new FileIOResponse(
                    fileIO.getId(),
                    fileIO.getName(),
                    fileDownloadUri,
                    fileIO.getType(),
                    fileIO.getData().length);
              }).collect(Collectors.toList());
          
            return ResponseEntity.status(HttpStatus.OK).body(files);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/files/cuocthis/{cuocThiId}/hinhanhs/{id}", produces = {
        MediaType.IMAGE_JPEG_VALUE,
        MediaType.IMAGE_PNG_VALUE,
        MediaType.IMAGE_GIF_VALUE
    })
    public ResponseEntity<byte[]> getFile(@PathVariable("cuocThiId") String cuocThiId, @PathVariable("id") String id) {
        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        FileIO fileIO = storageService.getFile(id);

        return ResponseEntity.ok()
            // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + toChuc.getLogoFileName() + "\"")
            .body(fileIO.getData());
    }
    
    @DeleteMapping("/cuocthis/{cuocThiId}/hinhanhs/{id}")
    public ResponseEntity<HttpStatus> deleteHinhAnhCuocThi(@PathVariable("cuocThiId") String cuocThiId, @PathVariable("id") String id, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (!cuocThiRepository.existsById(cuocThiId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try {
            fileIORepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cuocthis/{id}")
    public ResponseEntity<HttpStatus> deleteCuocThi(@PathVariable("id") String id, @RequestHeader("vaiTros") String vaiTros) {
        if (!VaiTroChecker.hasVaiTroQuanTriHeThong(vaiTros)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
