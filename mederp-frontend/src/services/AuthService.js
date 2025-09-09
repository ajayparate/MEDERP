import axios from "axios";

let baseUrl = "http://localhost:8081/api/auth";
let baseUrl2 = "http://localhost:8081/api/medicines";

class AuthService {
    userRegistration (u) {
        return axios.post(baseUrl+"/register", u);
    }

    userLogin(p){
        let signin = {'content-Type' : 'application/json'}
        return axios.post(baseUrl+"/login",p,{headers: signin});
    }

    userLogiut(p) {
        return axios.post(baseUrl+"/signout", p);
    }
}

export default new AuthService();