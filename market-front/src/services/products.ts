import axios from './axios';

export function getAll() {
  return axios.get("products");
}

export function getById(productId: string) {
  return axios.get(`products/${productId}`);
}

export function post(product: any) {
  return axios.post("products", product);
}