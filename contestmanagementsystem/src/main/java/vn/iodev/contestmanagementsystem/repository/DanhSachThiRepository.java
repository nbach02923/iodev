package vn.iodev.contestmanagementsystem.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.iodev.contestmanagementsystem.model.CuocThi;
import vn.iodev.contestmanagementsystem.model.DanhSachThi;

public interface DanhSachThiRepository extends JpaRepository<DanhSachThi, Long> {
    List<DanhSachThi> findByCuocThiId(String cuocThiId, Pageable pageable);
    Optional<DanhSachThi> findByThiSinhIdAndCuocThiIdAndKhoiThiIdOrDoiThiId(String thiSinhId, String cuocThiId, String khoiThiId, String doiThiId);

    @Query("SELECT dst FROM T_DanhSachThi dst WHERE (:cuocThi is null OR dst.cuocThi = :cuocThi) AND (:khoiThiId is null OR dst.khoiThiId = :khoiThiId) AND (:doiThiId is null OR dst.doiThiId = :doiThiId)")
    List<DanhSachThi> findDanhSachThiByMultipleConditions(@Param("cuocThi") CuocThi cuocThi, @Param("khoiThiId") String khoiThiId, @Param("doiThiId") String doiThiId, Pageable pageable);

    Optional<DanhSachThi> findByThiSinhIdAndCuocThiIdAndKhoiThiIdAndDoiThiId(String thiSinhId, String cuocThiId, String khoiThiId, String doiThiId);
    @Transactional
    void deleteByCuocThiId(String cuocThiId);

    List<DanhSachThi> findByCuocThiIdAndKhoiThiId(String cuocThiId, String khoiThiId);
    List<DanhSachThi> findByCuocThiIdAndKhoiThiIdAndThiSinhIdIn(String cuocThiId, String khoiThiId, List<String> thiSinhIds);
    List<DanhSachThi> findByCuocThiIdAndKhoiThiIdAndDoiThiIdIn(String cuocThiId, String khoiThiId, List<String> doiThiIds);

    @Query("SELECT dst FROM T_DanhSachThi dst WHERE (:cuocThi is null OR dst.cuocThi = :cuocThi) AND (:khoiThiId is null OR dst.khoiThiId = :khoiThiId) AND dst.doiThiId is null ORDER BY dst.thuTuXepHang")
    List<DanhSachThi> getDanhSachThiCaNhan(@Param("cuocThi") CuocThi cuocThi, @Param("khoiThiId") String khoiThiId);

    @Query("SELECT dst FROM T_DanhSachThi dst WHERE (:cuocThi is null OR dst.cuocThi = :cuocThi) AND (:khoiThiId is null OR dst.khoiThiId = :khoiThiId) AND dst.doiThiId is not null ORDER BY dst.thuTuXepHang")
    List<DanhSachThi> getDanhSachThiTapThe(@Param("cuocThi") CuocThi cuocThi, @Param("khoiThiId") String khoiThiId);
    @Query("SELECT dst FROM T_DanhSachThi dst WHERE dst.cuocThi = :cuocThi AND dst.khoiThiId = :khoiThiId AND dst.doiThiId = :doiThiId ORDER BY dst.thuTuXepHang")
    List<DanhSachThi> getDanhSachThiCuaDoiThi(@Param("cuocThi") CuocThi cuocThi, @Param("khoiThiId") String khoiThiId, @Param("doiThiId") String doiThiId);
    @Query("SELECT dst FROM T_DanhSachThi dst WHERE dst.cuocThi = :cuocThi AND dst.thiSinhId = :thiSinhId")
    List<DanhSachThi> getDanhSachThamGiaCuaCaNhan(@Param("cuocThi") CuocThi cuocThi, @Param("thiSinhId") String thiSinhId);
}
