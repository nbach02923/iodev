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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "T_DoiThi")
@Table(name = "T_DoiThi")
public class DoiThi {
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
    public String getChuDeSangTao() {
        return chuDeSangTao;
    }
    public void setChuDeSangTao(String chuDeSangTao) {
        this.chuDeSangTao = chuDeSangTao;
    }
    public String getHuanLuyenVienId() {
        return huanLuyenVienId;
    }
    public void setHuanLuyenVienId(String huanLuyenVienId) {
        this.huanLuyenVienId = huanLuyenVienId;
    }
    public Integer getKetQuaSoLoai() {
        return ketQuaSoLoai;
    }
    public void setKetQuaSoLoai(Integer ketQuaSoLoai) {
        this.ketQuaSoLoai = ketQuaSoLoai;
    }
    public Integer getThuTuXepHang() {
        return thuTuXepHang;
    }
    public void setThuTuXepHang(Integer thuTuXepHang) {
        this.thuTuXepHang = thuTuXepHang;
    }
    public String getBangDiemThi() {
        return bangDiemThi;
    }
    public void setBangDiemThi(String bangDiemThi) {
        this.bangDiemThi = bangDiemThi;
    }
    public String getHangGiaiThuong() {
        return hangGiaiThuong;
    }
    public void setHangGiaiThuong(String hangGiaiThuong) {
        this.hangGiaiThuong = hangGiaiThuong;
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

    @Column(name = "tenGoi", nullable = false)
    private String tenGoi;

    @Column(name = "doanThiId", nullable = true)
    private String doanThiId;

    @Column(name = "khoiThiId", nullable = true)
    private String khoiThiId;

    @Column(name = "chuDeSangTao", nullable = true)
    private String chuDeSangTao;

    @Column(name = "huanLuyenVienId", nullable = true)
    private String huanLuyenVienId;

    @Column(name = "ketQuaSoLoai", nullable = true)
    private Integer ketQuaSoLoai;

    @Column(name = "thuTuXepHang")
    private Integer thuTuXepHang;

    @Column(name = "bangDiemThi", nullable = true)
    private String bangDiemThi;

    @Column(name = "hangGiaiThuong", nullable = true)
    private String hangGiaiThuong;

    @Column(name = "thoiGianTao")
    private long thoiGianTao;

    @Column(name = "thoiGianCapNhat")
    private long thoiGianCapNhat;

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
    public DoiThi() {}
    public DoiThi(
        String tenGoi,
        CuocThi cuocThi,
        String doanThiId,
        String khoiThiId,
        String chuDeSangTao,
        String huanLuyenVienId,
        Integer ketQuaSoLoai,
        Integer thuTuXepHang,
        String bangDiemThi,
        String hangGiaiThuong
    ) {
        this.tenGoi = tenGoi;
        this.cuocThi = cuocThi;
        this.doanThiId = doanThiId;
        this.khoiThiId = khoiThiId;
        this.chuDeSangTao = chuDeSangTao;
        this.huanLuyenVienId = huanLuyenVienId;
        this.ketQuaSoLoai = ketQuaSoLoai;
        this.thuTuXepHang = thuTuXepHang;
        this.bangDiemThi = bangDiemThi;
        this.hangGiaiThuong = hangGiaiThuong;
        this.thoiGianTao = System.currentTimeMillis();
        this.thoiGianCapNhat = System.currentTimeMillis();
    }
}
