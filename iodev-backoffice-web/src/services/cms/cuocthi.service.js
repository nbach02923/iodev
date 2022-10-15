import axios from 'axios';
import authHeader from '../authenticate/auth-header';

class CuocThiService {
  getDanhSachCuocThi() {
    return axios.get("/cuocthis", { headers: authHeader() }).then(response => {
      return response.data;
    });
  }
  themCuocThi(cuocThi) {
    return axios
        .post('/cuocthis', cuocThi, { headers: authHeader() } )
        .then(response => {
          return response.data;
        });
  }
  xoaCuocThi(id) {
    return axios
        .delete('/cuocthis/' + id, { headers: authHeader() })
        .then(response => {
          return response.data;
        });
  }
}

export default new CuocThiService();