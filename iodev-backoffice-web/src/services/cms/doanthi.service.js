import axios from 'axios';
import authHeader from '../authenticate/auth-header';

class DoanThiService {
  getDanhSachDoanThi(cuocThiId) {
    return axios
    .get("/doanthis", {params: {size:'10000'}}, {params: {cuocThiId: cuocThiId}}, {headers: authHeader()})
    .then((response) => {
      return response.data
    })
  }
}

export default new DoanThiService();