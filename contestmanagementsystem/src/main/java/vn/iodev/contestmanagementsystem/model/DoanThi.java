package vn.iodev.contestmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_DoanThi")
public class DoanThi {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenGoi() {
        return tenGoi;
    }

    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }

    public String getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(String tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public String getDiaChiHoatDong() {
        return diaChiHoatDong;
    }

    public void setDiaChiHoatDong(String diaChiHoatDong) {
        this.diaChiHoatDong = diaChiHoatDong;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToChucId() {
        return toChucId;
    }

    public void setToChucId(String toChucId) {
        this.toChucId = toChucId;
    }

    public String getCuocThiId() {
        return cuocThiId;
    }

    public void setCuocThiId(String cuocThiId) {
        this.cuocThiId = cuocThiId;
    }

    public Integer getThuTuXepHang() {
        return thuTuXepHang;
    }

    public void setThuTuXepHang(Integer thuTuXepHang) {
        this.thuTuXepHang = thuTuXepHang;
    }

    public long getThoiGianTao() {
        return thoiGianTao;
    }

    public void setThoiGianTao(long thoiGianTao) {
        this.thoiGianTao = thoiGianTao;
    }

    public long getThoiGianCapNhat() {
        return thoiGianCapNhat;
    }

    public void setThoiGianCapNhat(long thoiGianCapNhat) {
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "tenGoi", length = 1024)
    private String tenGoi;

    @Column(name = "tiengAnh", nullable = true)
    private String tiengAnh;

    @Column(name = "diaChiHoatDong", nullable = true, columnDefinition = "TEXT")
    private String diaChiHoatDong;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "toChucId", nullable = true)
    private String toChucId;

    @Column(name = "cuocThiId", nullable = true)
    private String cuocThiId;

    @Column(name = "thuTuXepHang", nullable = true)
    private Integer thuTuXepHang;

    @Column(name = "thoiGianTao")
    private long thoiGianTao;

    @Column(name = "thoiGianCapNhat")
    private long thoiGianCapNhat;

    public DoanThi() {}

    public DoanThi(
        String tenGoi,
        String tiengAnh,
        String diaChiHoatDong,
        String email,
        String toChucId,
        String cuocThiId
    ) {
        this.tenGoi = tenGoi;
        this.tiengAnh = tiengAnh;
        this.diaChiHoatDong = diaChiHoatDong;
        this.email = email;
        this.toChucId = toChucId;
        this.cuocThiId = cuocThiId;
        this.thoiGianTao = System.currentTimeMillis();
        this.thoiGianCapNhat = System.currentTimeMillis();
    }
}
