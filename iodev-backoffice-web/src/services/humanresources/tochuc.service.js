import axios from 'axios';
import authHeader from '../authenticate/auth-header';

class ToChucService {
  getDanhSachToChuc() {
    return axios.get("/tochucs", {params: {size: '10000'}}, { headers: authHeader() })
    .then(response => {
      return response.data;
    });
  }
  uploadToChuc(formData) {
    return axios
      .post("/tochucs/import", formData, { headers: authHeader() })
      .then((response) => {
        return response.data;
      });
  }
}

export default new ToChucService();