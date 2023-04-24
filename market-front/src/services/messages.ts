import axios from "./axios";

const URL = "messages"
export function post(productId: string, content: string) {
  return axios.post(`${URL}/product/${productId}`, { content });
}

export function getAll(productId: string) {
  return axios.get(`${URL}/product/${productId}`);
}