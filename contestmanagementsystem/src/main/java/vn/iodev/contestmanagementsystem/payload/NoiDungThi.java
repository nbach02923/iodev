package vn.iodev.contestmanagementsystem.payload;

public class NoiDungThi {
    public String getTenNoiDung() {
        return tenNoiDung;
    }
    public void setTenNoiDung(String tenNoiDung) {
        this.tenNoiDung = tenNoiDung;
    }
    public Integer getSoDoi() {
        return soDoi;
    }
    public void setSoDoi(Integer soDoi) {
        this.soDoi = soDoi;
    }
    public Integer getSoThiSinh() {
        return soThiSinh;
    }
    public void setSoThiSinh(Integer soThiSinh) {
        this.soThiSinh = soThiSinh;
    }
    private String tenNoiDung;
    private Integer soDoi;
    private Integer soThiSinh;
    public NoiDungThi() {}
}
