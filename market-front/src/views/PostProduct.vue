<script setup lang="ts">
import { post } from '@/services/products';
import useAuthStore from '@/stores/auth';
import { reactive } from 'vue';

const auth = useAuthStore();

const product = reactive({
  name: '',
  description: '',
  price: 0,
});

async function submitForm() {
  const req = {
    ...product,
    userId: auth.id,
  };

  try {
    await post(req);
  } catch (error) {
    console.log(error);
  }
}

</script>

<template>
  <h1>Sube tu producto</h1>
  <form class="form" @submit.prevent="submitForm">
    <div class="form-fieldset">
      <label for="name">Nombre</label>
      <input type="text" name="name" id="name" v-model="product.name">
    </div>

    <div class="form-fieldset">
      <label for="name">Descripción</label>
      <input type="text" name="description" id="description" v-model="product.description">
    </div>

    <div class="form-fieldset">
      <label for="name">Precio</label>
      <input type="number" name="price" id="price" v-model="product.price">
    </div>

    <button type="submit">Enviar</button>
  </form>
</template>


<style scoped>
button {
  color: white;
  letter-spacing: 1px;
  font-size: 14px;
  font-weight: 500;
  background-color: var(--btn-primary);
  border-radius: 0.125rem;
  padding: 0.8rem;
}
.form {
  display: flex;
  flex-direction: column;
  gap: 0.8rem;
}
.form-fieldset {
  display: flex;
  flex-direction: column;
  gap: 0.3rem;
}
input {
  font-size: 1rem;
  padding: 0.5rem 0.8rem;
}
h1 {
  font-size: 1.5rem;
  padding-bottom: 0.8rem;
  padding-left: 0.8rem;
  border-bottom: 1.5px solid grey;
  margin-bottom: 0.8rem;
}
</style>