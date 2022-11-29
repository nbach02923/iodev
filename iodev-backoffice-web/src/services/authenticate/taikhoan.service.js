import axios from 'axios';
import authHeader from '../authenticate/auth-header';

class TaiKhoanService {
  getDanhSachTaiKhoan(tinhTrang) {
    return axios
        .get('/taikhoans', { headers: authHeader() }, {
            tinhTrang: tinhTrang
        })
        .then(response => {
          return response.data;
        });
  }

  themTaiKhoan(taiKhoan) {
    return axios
        .post('/taikhoans', taiKhoan, { headers: authHeader() } )
        .then(response => {
          return response.data;
        });
  }

  kichHoatTaiKhoan(email) {
    return axios
        .put('/taikhoans/' + email + "/kichhoat", { headers: authHeader() })
        .then(response => {
          return response.data;
        });
  }

  xoaTaiKhoan(email) {
    return axios
        .delete('/taikhoans/' + email, { headers: authHeader() })
        .then(response => {
          return response.data;
        });
  }

  suaTaiKhoan(reset) {
    return axios
      .post('taikhoans' + reset.id, )
  }
}

export default new TaiKhoanService();