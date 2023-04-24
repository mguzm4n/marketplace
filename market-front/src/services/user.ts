import axios from './axios'
export function login(bodyReq: any) {
  return axios.post("auth/login", bodyReq);
}
export function logout() {
  return axios.post("auth/logout");
}
export function checkLogin() {
  return axios.get("auth/user");
}