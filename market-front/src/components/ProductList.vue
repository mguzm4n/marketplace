<script setup lang="ts">
import { getAll } from '@/services/products';
import useProductStore from '@/stores/product';
import { onMounted, ref } from 'vue';
import { RouterLink, useRouter } from 'vue-router';
export type Product = {
  id: number,
  name: string,
  description: string,
  price: number,
  publisher: string,
  uploadedAt: string,
}
const productStore = useProductStore();

const products = ref<Product[]>([]);
const isLoading = ref(false);
const router = useRouter();

async function loadProducts() {
  isLoading.value = true;
  try {
    const req = await getAll();
    products.value = req.data;
  } catch (error) {
    console.log(error);
  } finally {
    isLoading.value = false;
  }
}
onMounted(() => {
  loadProducts();
  console.log(products.value);
});

function onProductClick(product: Product) {
  productStore.setProduct(product);
  router.push(`product/${product.id}`);
}

</script>


<template>
  <p v-if="isLoading">Cargando todos los productos...</p>
  Lista de productos
  <div v-for="product in products">
    <p v-text="product.publisher"></p>
    <a :title="`Ir a producto '${product.name.substring(0, 10)}...'`" 
      class="product-link" @click.prevent="onProductClick(product)">
      <p v-text="product.name"></p>
    </a>
    <p v-text="product.price"></p>
    <p v-text="product.uploadedAt"></p>
  </div>
  <p v-if="products.length === 0">Sin productos.</p>
</template>

<style scoped>
.product-link {
  cursor: pointer;

}
</style>
