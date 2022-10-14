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
}

export default new TaiKhoanService();