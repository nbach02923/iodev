import axios from 'axios';
import authHeader from '../authenticate/auth-header';

class KhoiThiService {
  getDanhSachKhoiThi() {
    return axios.get("/khoithis", { headers: authHeader() }).then(response => {
      return response.data;
    });
  }
  getDanhSachKhoiThiCuaCuocThi(cuocThiId) {
    return axios.get("/cuocthis/" + cuocThiId + "/khoithis", { headers: authHeader() }).then(response => {
      return response.data;
    });
  }
  themKhoiThi(cuocThiId, khoiThi) {
    return axios
        .post('/cuocthis/' + cuocThiId + '/khoithis', khoiThi, { headers: authHeader() } )
        .then(response => {
          return response.data;
        });
  }
  suaKhoiThi(khoiThi) {
    return axios
        .put('/khoithis/' + khoiThi.id, khoiThi, { headers: authHeader() } )
        .then(response => {
          return response.data;
        });
  }
  xoaKhoiThi(id) {
    return axios
        .delete('/khoithis/' + id, { headers: authHeader() })
        .then(response => {
          return response.data;
        });
  }
}

export default new KhoiThiService();