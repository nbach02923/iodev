package vn.iodev.iosecurity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import vn.iodev.iosecurity.model.EVaiTro;
import vn.iodev.iosecurity.model.TaiKhoan;
import vn.iodev.iosecurity.model.VaiTro;
import vn.iodev.iosecurity.repository.TaiKhoanRepository;
import vn.iodev.iosecurity.repository.VaiTroRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableScheduling
public class IOSecurityApplication implements CommandLineRunner {

	@Autowired
    TaiKhoanRepository taiKhoanRepository;

    @Autowired
    VaiTroRepository vaiTroRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(IOSecurityApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        Optional<VaiTro> vtNdData = vaiTroRepository.findByTen(EVaiTro.VAITRO_NGUOIDUNG);

        if (!vtNdData.isPresent()) {
            VaiTro vaiTro = new VaiTro(EVaiTro.VAITRO_NGUOIDUNG);
            vaiTroRepository.save(vaiTro);
        }

        vtNdData = vaiTroRepository.findByTen(EVaiTro.VAITRO_QUANTRICUOCTHI);
        if (!vtNdData.isPresent()) {
            VaiTro vaiTro = new VaiTro(EVaiTro.VAITRO_QUANTRICUOCTHI);
            vaiTroRepository.save(vaiTro);
        }

        vtNdData = vaiTroRepository.findByTen(EVaiTro.VAITRO_QUANTRIHETHONG);
        if (!vtNdData.isPresent()) {
            VaiTro vaiTro = new VaiTro(EVaiTro.VAITRO_QUANTRIHETHONG);
            vaiTroRepository.save(vaiTro);
        }

        vtNdData = vaiTroRepository.findByTen(EVaiTro.VAITRO_QUANTRITOCHUC);

        if (!vtNdData.isPresent()) {
            VaiTro vaiTro = new VaiTro(EVaiTro.VAITRO_QUANTRITOCHUC);
            vaiTroRepository.save(vaiTro);
        }

        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setEmail("trung.dk070384@gmail.com");
        taiKhoan.setMatKhau(passwordEncoder.encode("123456"));
        taiKhoan.setId("SUPERADMIN");
        taiKhoan.setLoaiTaiKhoan(1);

        vtNdData = vaiTroRepository.findByTen(EVaiTro.VAITRO_QUANTRIHETHONG);
        
        Set<VaiTro> vaiTros = new HashSet<>();
        if (vtNdData.isPresent()) {
            vaiTros.add(vtNdData.get());
        }
        taiKhoan.setVaiTros(vaiTros);
            
        taiKhoanRepository.save(taiKhoan);
    }
}
