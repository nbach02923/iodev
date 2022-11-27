import axios from "axios";
import authHeader from "../authenticate/auth-header";

class ThiSinhService {
  uploadThiSinh(formData) {
    return axios
      .post("/danhsachthis/import", formData, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getDanhSachThiSinhCuaDoanThi() {
    return axios
      .get("/thisinhs", {params: {size: '10000'}}, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
  getDanhSachThiSinhCuaCuocThi(cuocthiId) {
    return axios
      .get("/cuocthis/" + cuocthiId + "/thisinhs", {params: {size: '10000'}}, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}
export default new ThiSinhService();
