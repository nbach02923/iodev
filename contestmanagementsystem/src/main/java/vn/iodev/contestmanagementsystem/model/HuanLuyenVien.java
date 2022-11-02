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

@Entity(name = "T_HuanLuyenVien")
@Table(name = "T_HuanLuyenVien")
public class HuanLuyenVien {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCaNhanId() {
        return caNhanId;
    }

    public void setCaNhanId(String caNhanId) {
        this.caNhanId = caNhanId;
    }

    public String getDoanThiId() {
        return doanThiId;
    }

    public void setDoanThiId(String doanThiId) {
        this.doanThiId = doanThiId;
    }

    public String getKhoiThiId() {
        return khoiThiId;
    }

    public void setKhoiThiId(String khoiThiId) {
        this.khoiThiId = khoiThiId;
    }

    public Integer getTruongPhoDoan() {
        return truongPhoDoan;
    }

    public void setTruongPhoDoan(Integer truongPhoDoan) {
        this.truongPhoDoan = truongPhoDoan;
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

    @Column(name = "hoTen", length = 1024)
    private String hoTen;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "soDienThoai", nullable = true)
    private String soDienThoai;

    @Column(name = "caNhanId", nullable = true)
    private String caNhanId;

    @Column(name = "doanThiId", nullable = true)
    private String doanThiId;

    @Column(name = "khoiThiId", nullable = true, columnDefinition = "TEXT")
    private String khoiThiId;

    @Column(name = "truongPhoDoan", nullable = true)
    private Integer truongPhoDoan;

    @Column(name = "thoiGianTao")
    private long thoiGianTao;

    @Column(name = "thoiGianCapNhat")
    private long thoiGianCapNhat;

    @JsonIgnore
    @Transient
    private String tenToChuc;

    public String getTenToChuc() {
        return tenToChuc;
    }

    public void setTenToChuc(String tenToChuc) {
        this.tenToChuc = tenToChuc;
    }

    public String getTenDoanThi() {
        return tenDoanThi;
    }

    public void setTenDoanThi(String tenDoanThi) {
        this.tenDoanThi = tenDoanThi;
    }

    @JsonIgnore
    @Transient
    private String tenDoanThi;

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
    
    public HuanLuyenVien() {}

    public HuanLuyenVien(
        String hoTen,
        String email,
        String soDienThoai,
        String caNhanId,
        CuocThi cuocThi,
        String doanThiId,
        String khoiThiId,
        Integer truongPhoDoan
    ) {
        this.hoTen = hoTen;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.caNhanId = caNhanId;
        this.cuocThi = cuocThi;
        this.khoiThiId = khoiThiId;
        this.truongPhoDoan = truongPhoDoan;
        this.thoiGianTao = System.currentTimeMillis();
        this.thoiGianCapNhat = System.currentTimeMillis();
    }

    @Override 
    public boolean equals(Object o) {
        if (o instanceof HuanLuyenVien) {
            HuanLuyenVien temp = (HuanLuyenVien)o;
            return (id != null && id.contentEquals(temp.getId()));
        }
        else {
            return false;
        }
    }
}
