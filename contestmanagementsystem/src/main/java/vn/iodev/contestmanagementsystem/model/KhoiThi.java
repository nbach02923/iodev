package vn.iodev.contestmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_KhoiThi")
public class KhoiThi {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(length = 36, nullable = false, updatable = false)    private String id;

    @Column(name = "tenGoi", nullable = false)
    private String tenGoi;

    @Column(name = "noiDungThi", nullable = false, columnDefinition = "TEXT")
    private String noiDungThi;

    @Column(name = "vongSoLoai")
    private Boolean vongSoLoai;

    @Column(name = "thiTapThe")
    private Boolean thiTapThe;

    @Column(name = "thiSangTao")
    private Boolean thiSangTao;

    @Column(name = "maxDangKi")
    private Integer maxDangKi;

    @Column(name = "maxThiSinh")
    private Integer maxThiSinh;

    @Column(name = "tinhTrang")
    private Integer tinhTrang;

    @Column(name = "thoiGianTao")
    private long thoiGianTao;

    @Column(name = "thoiGianCapNhat")
    private long thoiGianCapNhat;

    @JsonIgnore
    @Transient
    private String tenCuocThi;
    
    public String getTenCuocThi() {
        return tenCuocThi;
    }

    public void setTenCuocThi(String tenCuocThi) {
        this.tenCuocThi = tenCuocThi;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cuocThiId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private CuocThi cuocThi;

    public CuocThi getCuocThi() {
        return cuocThi;
    }

    public void setCuocThi(CuocThi cuocThi) {
        this.cuocThi = cuocThi;
    }

    public KhoiThi() {}

    public KhoiThi(
        String tenGoi,
        CuocThi cuocThi,
        String noiDungThi,
        Boolean vongSoLoai,
        Boolean thiTapThe,
        Boolean thiSangTao,
        Integer maxDangKi,
        Integer maxThiSinh
    ) {
        this.tenGoi = tenGoi;
        this.cuocThi = cuocThi;
        this.noiDungThi = noiDungThi;
        this.vongSoLoai = vongSoLoai;
        this.thiTapThe = thiTapThe;
        this.thiSangTao = thiSangTao;
        this.maxDangKi = maxDangKi;
        this.maxThiSinh = maxThiSinh;
        this.thoiGianTao = System.currentTimeMillis();
        this.thoiGianCapNhat = System.currentTimeMillis();
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

    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }

    public String getNoiDungThi() {
        return noiDungThi;
    }

    public void setNoiDungThi(String noiDungThi) {
        this.noiDungThi = noiDungThi;
    }

    public Boolean getVongSoLoai() {
        return vongSoLoai;
    }

    public void setVongSoLoai(Boolean vongSoLoai) {
        this.vongSoLoai = vongSoLoai;
    }

    public Boolean getThiTapThe() {
        return thiTapThe;
    }

    public void setThiTapThe(Boolean thiTapThe) {
        this.thiTapThe = thiTapThe;
    }

    public Boolean getThiSangTao() {
        return thiSangTao;
    }

    public void setThiSangTao(Boolean thiSangTao) {
        this.thiSangTao = thiSangTao;
    }

    public Integer getMaxDangKi() {
        return maxDangKi;
    }

    public void setMaxDangKi(Integer maxDangKi) {
        this.maxDangKi = maxDangKi;
    }

    public Integer getMaxThiSinh() {
        return maxThiSinh;
    }

    public void setMaxThiSinh(Integer maxThiSinh) {
        this.maxThiSinh = maxThiSinh;
    }

    public Integer getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(Integer tinhTrang) {
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
}
