<script setup lang="ts">
import { post } from '@/services/messages';
import { ref } from 'vue';
import { useRoute } from 'vue-router';

const content = ref('');
const isLoading = ref(false);
const productId = useRoute().params.productId as string;

async function onSubmitMessage() {
  isLoading.value = true;
  try {
    const req = await post(productId, content.value);
    console.log(req);
    content.value = "";
  } catch (error) {
    console.log(error);
  } finally {
    isLoading.value = false;
  }
}
</script>
<template>
<form @submit.prevent="onSubmitMessage">
  <div class="form-container">
    <p>
      ¿Estás interesado en este producto? Envía un mensaje al vendedor.
    </p>
    <textarea v-model="content" class="form-textarea" placeholder="Escribe el mensaje aquí..." name="content" id="content" cols="65" rows="8"></textarea>
    <button :class="{ 'btn-loading': isLoading }" class="form-submit-btn" type="submit">Enviar</button>
  </div>
</form>
</template>

<style>
.form-textarea {
  font-family: inherit;
  color: black;
  font-size: 16px;
  padding: 0.5rem 1rem;
}
.form-submit-btn {
  color: var(--white);
  font-size: 14px;
  font-weight: 600;
  border-radius: 0.125rem;

  background-color: var(--btn-primary);
  padding: 0.5rem 0.5rem;
  width: 300px;
  align-self: flex-end;
}

.form-container {
  display: flex;
  flex-direction: column;
  max-width: min-content;
  gap: 0.5rem;
}

.btn-loading {
  cursor: wait;
  opacity: 80%;
}
</style>
