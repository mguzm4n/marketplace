import type { Product } from '@/components/ProductList.vue';
import { defineStore } from 'pinia';

const useProductStore = defineStore('product', {
  state: (): { product: Product | undefined } => ({
    product: undefined
  }),
  getters: {
  },
  actions: {
    setProduct(product: Product) {
      this.product = product;
    },
  }
})

export default useProductStore;