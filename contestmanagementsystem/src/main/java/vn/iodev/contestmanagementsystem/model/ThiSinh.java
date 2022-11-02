package vn.iodev.contestmanagementsystem.model;

import java.sql.Date;

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
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "T_ThiSinh")
@Table(name = "T_ThiSinh")
public class ThiSinh {
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

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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

    public String getDoiTuongThi() {
        return doiTuongThi;
    }

    public void setDoiTuongThi(String doiTuongThi) {
        this.doiTuongThi = doiTuongThi;
    }

    public String getNganhDaoTao() {
        return nganhDaoTao;
    }

    public void setNganhDaoTao(String nganhDaoTao) {
        this.nganhDaoTao = nganhDaoTao;
    }

    public String getDatGiaiThuong() {
        return datGiaiThuong;
    }

    public void setDatGiaiThuong(String datGiaiThuong) {
        this.datGiaiThuong = datGiaiThuong;
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

    @Column(name = "hoTen", nullable = false)
    private String hoTen;
    
    @Column(name = "gioiTinh", nullable = false)
    private Integer gioiTinh;

    @Column(name = "ngaySinh", nullable = true)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date ngaySinh;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "soDienThoai", nullable = true)
    private String soDienThoai;

    @Column(name = "doiTuongThi", nullable = true)
    private String doiTuongThi;

    @Column(name = "nganhDaoTao", nullable = true)
    private String nganhDaoTao;

    @Column(name = "datGiaiThuong", nullable = true)
    private String datGiaiThuong;

    @Column(name = "caNhanId", nullable = true)
    private String caNhanId;
    
    @Column(name = "doanThiId", nullable = true)
    private String doanThiId;

    @Column(name = "thoiGianTao")
    private long thoiGianTao;

    @Column(name = "thoiGianCapNhat")
    private long thoiGianCapNhat;

    @JsonIgnore
    @Transient
    private String tenCuocThi;

    @JsonIgnore
    @Transient
    private String tenKhoiThi;

    @JsonIgnore
    @Transient
    private boolean thamGia;

    @JsonIgnore
    @Transient
    private String tenDoiThi;

    @JsonIgnore
    @Transient
    private String tenDoanThi;

    public String getTenDoanThi() {
        return tenDoanThi;
    }

    public void setTenDoanThi(String tenDoanThi) {
        this.tenDoanThi = tenDoanThi;
    }

    public String getTenCuocThi() {
        return tenCuocThi;
    }

    public void setTenCuocThi(String tenCuocThi) {
        this.tenCuocThi = tenCuocThi;
    }

    public String getTenKhoiThi() {
        return tenKhoiThi;
    }

    public void setTenKhoiThi(String tenKhoiThi) {
        this.tenKhoiThi = tenKhoiThi;
    }

    public boolean isThamGia() {
        return thamGia;
    }

    public void setThamGia(boolean thamGia) {
        this.thamGia = thamGia;
    }

    public String getTenDoiThi() {
        return tenDoiThi;
    }

    public void setTenDoiThi(String tenDoiThi) {
        this.tenDoiThi = tenDoiThi;
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
    
    public ThiSinh() {}

    public ThiSinh(
        String hoTen,
        Integer gioiTinh,
        Date ngaySinh,
        String email,
        String soDienThoai,
        String doiTuongThi,
        String nganhDaoTao,
        String datGiaiThuong,
        String caNhanId,
        CuocThi cuocThi,
        String doanThiId
    ) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.doiTuongThi = doiTuongThi;
        this.nganhDaoTao = nganhDaoTao;
        this.datGiaiThuong = datGiaiThuong;
        this.caNhanId = caNhanId;
        this.cuocThi = cuocThi;
        this.doanThiId = doanThiId;
        this.thoiGianTao = System.currentTimeMillis();
        this.thoiGianCapNhat = System.currentTimeMillis();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ThiSinh) {
            ThiSinh temp = (ThiSinh)o;
            return (id != null && id.contentEquals(temp.getId()));
        }
        else {
            return false;
        }
    }
}
