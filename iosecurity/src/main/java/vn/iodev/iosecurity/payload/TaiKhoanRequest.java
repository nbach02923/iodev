package vn.iodev.iosecurity.payload;

import javax.validation.constraints.Email;

public class TaiKhoanRequest {
    @Email
    private String email;
    private String id;
    private Integer loaiTaiKhoan;
    
    public Integer getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(Integer loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    private String matKhau;

    public TaiKhoanRequest() {}
}
