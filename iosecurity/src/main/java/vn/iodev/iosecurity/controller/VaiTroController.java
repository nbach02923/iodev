package vn.iodev.iosecurity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.iodev.iosecurity.model.VaiTro;
import vn.iodev.iosecurity.repository.VaiTroRepository;

@RestController
@RequestMapping("/api")
public class VaiTroController {
    @Autowired
    VaiTroRepository vaiTroRepository;

    @GetMapping("/vaitros")
    public ResponseEntity<List<VaiTro>> getAllVaiTros() {
        try {
            List<VaiTro> vaiTros = vaiTroRepository.findAll();

            return new ResponseEntity<>(vaiTros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vaitros/{id}")
    public ResponseEntity<VaiTro> getVaiTroById(@PathVariable("id") Integer id) {
        Optional<VaiTro> vaiTroData = vaiTroRepository.findById(id);

        if (vaiTroData.isPresent()) {
            return new ResponseEntity<>(vaiTroData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/vaitros")
    public ResponseEntity<VaiTro> createVaiTro(@RequestBody VaiTro vaiTro) {
        try {
            VaiTro vaiTroMoi = new VaiTro(vaiTro.getTen());

            VaiTro _vaiTro = vaiTroRepository
                .save(vaiTroMoi);
            return new ResponseEntity<>(_vaiTro, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vaitros/{id}")
    public ResponseEntity<VaiTro> updateVaiTro(@PathVariable("id") Integer id, @RequestBody VaiTro vaiTro) {
        Optional<VaiTro> vaiTroData = vaiTroRepository.findById(id);

        if (vaiTroData.isPresent()) {
            VaiTro _vaiTro = vaiTroData.get();
            if (vaiTro.getTen() != null) {
                _vaiTro.setTen(vaiTro.getTen());
            }
            return new ResponseEntity<>(vaiTroRepository.save(_vaiTro), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/vaitros/{id}")
    public ResponseEntity<HttpStatus> deleteVaiTro(@PathVariable("id") Integer id) {
        try {
            vaiTroRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
