import axios from 'axios';
import authHeader from '../authenticate/auth-header';

class DanhMucService {
  getDanhSachDanhMuc() {
    return axios.get("/danhmucs", {params: {size: '10000'}}, { headers: authHeader() }).then(response => {
      return response.data;
    });
  }
  getDanhSachDanhMucByLoaiDanhMuc(loaiDanhMuc) {
    return axios.get("/danhmucs", {params: {size: '10000'}}, { params: { loaiDanhMuc: loaiDanhMuc } }, { headers: authHeader() }).then(response => {
      return response.data;
    });
  }
}

export default new DanhMucService();