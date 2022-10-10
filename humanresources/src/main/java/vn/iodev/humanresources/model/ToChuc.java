package vn.iodev.humanresources.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import vn.iodev.humanresources.config.HumanResourcesConfiguration;
import vn.iodev.humanresources.utils.RandomUtil;

@Entity(name = "T_ToChuc")
@Table(name = "T_ToChuc")
public class ToChuc {
    @Id
    private String id;

    @Column(name = "tenGoi", nullable = false, length = 1024)
    private String tenGoi;

    @Column(name = "tiengAnh", nullable = true)
    private String tiengAnh;

    @Column(name = "tenVietTat", nullable = true, length = 512)
    private String tenVietTat;

    @Column(name = "loaiToChuc", nullable = false)
    private String loaiToChuc;

    @Column(name = "diaChiHoatDong", nullable = true, columnDefinition = "TEXT")
    private String diaChiHoatDong;
 
    @Column(name = "viTriDiaLy", nullable = true)
    private String viTriDiaLy;
 
    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "web", nullable = true)
    private String web;

    @Column(name = "logo", nullable = true)
    private String logo;

    @Column(name = "tinhTrang", nullable = false)
    private int tinhTrang;

    @Column(name = "thoiGianTao", nullable = false)
    private long thoiGianTao;

    @Column(name = "thoiGianCapNhat", nullable = false)
    private long thoiGianCapNhat;

    public ToChuc(String tenGoi, String tiengAnh, String tenVietTat, String loaiToChuc, String diaChiHoatDong, String viTriDiaLy, String email, String web, String logo, int tinhTrang, long thoiGianTao, long thoiGianCapNhat) {
        this.tenGoi = tenGoi;
        this.tiengAnh = tiengAnh;
        this.tenVietTat = tenVietTat;
        this.loaiToChuc = loaiToChuc;
        this.diaChiHoatDong = diaChiHoatDong;
        this.viTriDiaLy = viTriDiaLy;
        this.email = email;
        this.web = web;
        this.logo = logo;
        this.tinhTrang = tinhTrang;
        this.thoiGianTao = thoiGianTao;
        this.thoiGianCapNhat = thoiGianCapNhat;
    }

    public ToChuc() {}

    public ToChuc(String tenGoi,
        String tiengAnh,
        String tenVietTat,
        String loaiToChuc,
        String diaChiHoatDong,
        String viTriDiaLy,
        String email,
        String web,
        String logo) {
            this.tenGoi = tenGoi;
            this.tiengAnh = tiengAnh;
            this.tenVietTat = tenVietTat;
            this.loaiToChuc = loaiToChuc;
            this.diaChiHoatDong = diaChiHoatDong;
            this.viTriDiaLy = viTriDiaLy;
            this.email = email;
            this.web = web;
            this.logo = logo;
            this.thoiGianTao = System.currentTimeMillis();
            this.thoiGianCapNhat =  System.currentTimeMillis();
            this.id = RandomUtil.generateRandomNumeric(HumanResourcesConfiguration.getInstance().getToChucIdLength());
            this.tinhTrang = 0;
        }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTenGoi() {
        return tenGoi;
    }

    public String getTiengAnh() {
        return tiengAnh;
    }

    public void setTiengAnh(String tiengAnh) {
        this.tiengAnh = tiengAnh;
    }

    public String getTenVietTat() {
        return tenVietTat;
    }

    public void setTenVietTat(String tenVietTat) {
        this.tenVietTat = tenVietTat;
    }

    public String getDiaChiHoatDong() {
        return diaChiHoatDong;
    }

    public void setDiaChiHoatDong(String diaChiHoatDong) {
        this.diaChiHoatDong = diaChiHoatDong;
    }

    public String getViTriDiaLy() {
        return viTriDiaLy;
    }

    public void setViTriDiaLy(String viTriDiaLy) {
        this.viTriDiaLy = viTriDiaLy;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
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

    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }

    public String getLoaiToChuc() {
        return loaiToChuc;
    }

    public void setLoaiToChuc(String loaiToChuc) {
        this.loaiToChuc = loaiToChuc;
    }

    @Override
    public String toString() {
        return "ToChuc [id=" + id + ", tenGoi = " + tenGoi + "]";
    }
}
