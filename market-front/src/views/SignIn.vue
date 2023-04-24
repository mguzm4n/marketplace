<script setup lang="ts">
import { useLogin } from '@/composers/useLogin';
import { login } from '@/services/user';
import useAuthStore from '@/stores/auth';
import { onMounted, reactive } from 'vue';
import { useRouter } from 'vue-router';

const auth = useAuthStore();
const router = useRouter();

const loginBody = reactive({
  email: '',
  password: ''
});

onMounted(() => {
  if (auth.user) {
    router.push("/");
  }
})

async function submitForm() {
  console.log(loginBody);
  try {
    const req = await login(loginBody);
    auth.loadUser(req.data);
    setTimeout(() => {
      router.push("/");
    }, 0.5 * 1000);
  } catch (error) {
    console.log(error); 
  }
}


</script>

<template>
  <h1>Inicia sesión</h1>
  <form class="form" @submit.prevent="submitForm">
    <div class="form-fieldset">
      <label for="email">Email</label>
      <input type="email" name="email" id="email" v-model="loginBody.email">
    </div>

    <div class="form-fieldset">
      <label for="password">Contraseña</label>
      <input type="password" name="password" id="password" v-model="loginBody.password">
    </div>

    <button type="submit">Entrar</button>
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