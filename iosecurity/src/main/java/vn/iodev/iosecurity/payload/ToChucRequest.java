package vn.iodev.iosecurity.payload;

public class ToChucRequest {
    private String email;
    private String loaiToChuc;
    public String getLoaiToChuc() {
        return loaiToChuc;
    }
    public void setLoaiToChuc(String loaiToChuc) {
        this.loaiToChuc = loaiToChuc;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    private String tenGoi;
    public String getTenGoi() {
        return tenGoi;
    }
    public void setTenGoi(String tenGoi) {
        this.tenGoi = tenGoi;
    }
    public ToChucRequest() {}
}
