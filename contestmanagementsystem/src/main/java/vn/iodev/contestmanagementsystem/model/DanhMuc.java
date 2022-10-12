package vn.iodev.contestmanagementsystem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name = "T_DanhMuc")
@Table(name = "T_DanhMuc")
@IdClass(DanhMucId.class)
public class DanhMuc {
    public String getLoaiDanhMuc() {
        return loaiDanhMuc;
    }

    public void setLoaiDanhMuc(String loaiDanhMuc) {
        this.loaiDanhMuc = loaiDanhMuc;
    }

    public String getMaDanhMuc() {
        return maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(String giaTri) {
        this.giaTri = giaTri;
    }

    @Id
    @Column(name = "loaiDanhMuc", length = 512)
    private String loaiDanhMuc;

    @Id
    @Column(name = "maDanhMuc", length = 512)
    private String maDanhMuc;

    @Column(name = "giaTri", nullable = false, columnDefinition = "TEXT")
    private String giaTri;

    public DanhMuc() {}

    public DanhMuc(String loaiDanhMuc, String maDanhMuc, String giaTri) {
        this.loaiDanhMuc = loaiDanhMuc;
        this.maDanhMuc = maDanhMuc;
        this.giaTri = giaTri;
    }
}
