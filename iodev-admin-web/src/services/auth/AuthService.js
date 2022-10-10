import http from "../../http-common";

class AuthService {
    authenticate(data) {
        return http.post("/authenticate", data);
    }
}

export default new AuthService();