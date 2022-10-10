package vn.iodev.contestmanagementsystem.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "T_CuocThi")
@Table(name = "T_CuocThi")
public class CuocThi {
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

    public String getSerieCuocThi() {
        return serieCuocThi;
    }

    public void setSerieCuocThi(String serieCuocThi) {
        this.serieCuocThi = serieCuocThi;
    }

    public Integer getLanToChuc() {
        return lanToChuc;
    }

    public void setLanToChuc(Integer lanToChuc) {
        this.lanToChuc = lanToChuc;
    }

    public String getDonViToChuc() {
        return donViToChuc;
    }

    public void setDonViToChuc(String donViToChuc) {
        this.donViToChuc = donViToChuc;
    }

    public String getToChucId() {
        return toChucId;
    }

    public void setToChucId(String toChucId) {
        this.toChucId = toChucId;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getThongTinMoTa() {
        return thongTinMoTa;
    }

    public void setThongTinMoTa(String thongTinMoTa) {
        this.thongTinMoTa = thongTinMoTa;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)
    private String id;

    @Column(name = "tenGoi")
    private String tenGoi;

    @Column(name = "tiengAnh", nullable = true)
    private String tiengAnh;

    @Column(name = "serieCuocThi", nullable = true)
    private String serieCuocThi;

    @Column(name = "lanToChuc", nullable = true)
    private Integer lanToChuc;

    @Column(name = "donViToChuc", nullable = false)
    private String donViToChuc;

    @Column(name = "toChucId")
    private String toChucId;

    @Column(name = "ngayBatDau")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayBatDau;

    @Column(name = "ngayKetThuc")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngayKetThuc;

    @Column(name = "thongTinMoTa", columnDefinition = "TEXT")
    private String thongTinMoTa;

    @Column(name = "website", nullable = true)
    private String website;

    @Column(name = "hinhAnh", nullable = true)
    private String hinhAnh;

    @Column(name = "tinhTrang")
    private Integer tinhTrang;
    
    public CuocThi() {}

    public CuocThi(String tenGoi,
        String tiengAnh,
        String serieCuocThi,
        Integer lanToChuc,
        String donViToChuc,
        String toChucId,
        Date ngayBatDau,
        Date ngayKetThuc,
        String thongTinMoTa,
        String website,
        String hinhAnh
        ) {
            this.tenGoi = tenGoi;
            this.tiengAnh = tiengAnh;
            this.serieCuocThi = serieCuocThi;
            this.lanToChuc = lanToChuc;
            this.donViToChuc = donViToChuc;
            this.toChucId = toChucId;
            this.ngayBatDau = ngayBatDau;
            this.ngayKetThuc = ngayKetThuc;
            this.thongTinMoTa = thongTinMoTa;
            this.website = website;
            this.hinhAnh = hinhAnh;
            this.tinhTrang = 1;
        }
}
