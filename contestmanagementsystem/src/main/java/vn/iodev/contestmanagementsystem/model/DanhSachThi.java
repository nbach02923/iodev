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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "T_DanhSachThi")
@Table(name = "T_DanhSachThi")
public class DanhSachThi {
    public String getThiSinhId() {
        return thiSinhId;
    }

    public void setThiSinhId(String thiSinhId) {
        this.thiSinhId = thiSinhId;
    }

    public String getKhoiThiId() {
        return khoiThiId;
    }

    public void setKhoiThiId(String khoiThiId) {
        this.khoiThiId = khoiThiId;
    }

    public String getDoiThiId() {
        return doiThiId;
    }

    public void setDoiThiId(String doiThiId) {
        this.doiThiId = doiThiId;
    }

    public String getChuDeSangTao() {
        return chuDeSangTao;
    }

    public void setChuDeSangTao(String chuDeSangTao) {
        this.chuDeSangTao = chuDeSangTao;
    }

    public String getSoBaoDanh() {
        return soBaoDanh;
    }

    public void setSoBaoDanh(String soBaoDanh) {
        this.soBaoDanh = soBaoDanh;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "danhSachThiId")
    private long danhSachThiId;

    @Column(name = "thiSinhId")
    private String thiSinhId;

    @Column(name = "khoiThiId", nullable = true)
    private String khoiThiId;

    @Column(name = "doiThiId", nullable = true)
    private String doiThiId;

    @Column(name = "chuDeSangTao", nullable = true)
    private String chuDeSangTao;

    @Column(name = "soBaoDanh", nullable = true)
    private String soBaoDanh;

    @Column(name = "ketQuaSoLoai", nullable = true)
    private Integer ketQuaSoLoai;

    @Column(name = "thuTuXepHang", nullable = true)
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

    public DanhSachThi() {}

    public DanhSachThi(
        String thiSinhId,
        CuocThi cuocThi,
        String khoiThiId,
        String doiThiId,
        String chuDeSangTao,
        String soBaoDanh,
        Integer ketQuaSoLoai,
        Integer thuTuXepHang,
        String bangDiemThi,
        String hangGiaiThuong
    ) {
        this.thiSinhId = thiSinhId;
        this.cuocThi = cuocThi;
        this.khoiThiId = khoiThiId;
        this.doiThiId = doiThiId;
        this.chuDeSangTao = chuDeSangTao;
        this.soBaoDanh = soBaoDanh;
        this.ketQuaSoLoai = ketQuaSoLoai;
        this.thuTuXepHang = thuTuXepHang;
        this.bangDiemThi = bangDiemThi;
        this.hangGiaiThuong = hangGiaiThuong;
        this.thoiGianTao = System.currentTimeMillis();
        this.thoiGianCapNhat = System.currentTimeMillis();
    }

    public long getDanhSachThiId() {
        return danhSachThiId;
    }

    public void setDanhSachThiId(long danhSachThiId) {
        this.danhSachThiId = danhSachThiId;
    }
}
