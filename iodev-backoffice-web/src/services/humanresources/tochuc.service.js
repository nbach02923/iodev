import axios from 'axios';
import authHeader from '../authenticate/auth-header';

class ToChucService {
  getDanhSachToChuc() {
    return axios.get("/tochucs", { headers: authHeader() }).then(response => {
      for (var i = 0; i < response.data.length; i++) {
        response.data[i].fakeId = i;
      }
      return response.data;
    });
  }
}

export default new ToChucService();