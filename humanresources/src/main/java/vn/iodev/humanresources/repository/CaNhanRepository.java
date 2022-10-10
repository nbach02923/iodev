package vn.iodev.humanresources.repository;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iodev.humanresources.model.CaNhan;

public interface CaNhanRepository extends JpaRepository<CaNhan, String> {
    Optional<CaNhan> findByEmail(String email);
    Optional<CaNhan> findByHoTenAndGioiTinhAndNgaySinhAndEmailOrSoDienThoai(String hoTen, Integer gioiTinh, Date ngaySinh, String email, String soDienThoai);
}
