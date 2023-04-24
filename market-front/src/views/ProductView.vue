<script setup lang="ts">
import useProductStore from '@/stores/product';
import useAuthStore from '@/stores/auth';
import { computed } from '@vue/reactivity';
import { onMounted, toRefs } from 'vue';
import { useRoute } from 'vue-router';
import { getById } from '../services/products';
import SubmitMessage from '../components/SubmitMessage.vue';


interface ProductViewProps {
  productId: string;
}
const route = useRoute();
const productId = route.params.productId as string;

const authStore = useAuthStore();
const productStore = useProductStore();

const product = computed(() => productStore.product);
const user = computed(() => authStore.user);

async function fetchProduct(productId: string) {
  try {
    const req = await getById(productId);
    productStore.setProduct(req.data);
  } catch (error) {
    console.log(error);
  }
}

onMounted(() => {
  if (!productStore.product) {
    fetchProduct(productId);
  }
});


</script>

<template>

<div v-if="!product">No encontramos el producto que buscabas...</div>
<div v-else>
  <div class="product">
    <p v-text="product.name"></p>
    <p v-text="product.description"></p>
    <p v-text="product.price"></p>
    <p v-text="product.uploadedAt"></p>
    <p v-text="product.publisher"></p>
  </div>

  <div v-if="user">
    <SubmitMessage />
    <div v-if="user.id.toString() === product.publisherId">
      
      <p>Este producto ha sido publicado por ti.</p>
      <RouterLink :to="`/product/${product.id}/messages`">
        <p>Ver los mensajes que han llegado a tu producto aquí.</p>
      </RouterLink>
    </div>
  </div>
</div>

</template>

<style>

</style>
