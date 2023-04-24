<script setup lang="ts">
import { logout } from '@/services/user';
import useAuthStore from '@/stores/auth';
import { onMounted, ref } from 'vue';
const auth = useAuthStore();
const displayOptions = ref(false);
onMounted(() => {
});

async function onLogout() {
  try {
    await logout();
    auth.destroyUser();
  } catch (error) {
    
  }
}
function toggleOptions(option: boolean) {
  displayOptions.value = option;
}
</script>

<template>
  <div class="user-nav-container">
    <div @mouseenter="toggleOptions(true)">Hola, {{ auth.username }}.</div>
    <ul v-if="displayOptions" @mouseleave="toggleOptions(false)"
      class="user-options-list">
      <li>Datos</li>
      <li>Mis publicaciones</li>
      <li>Compras</li>
      <li>Notificaciones</li>
      <li>
        <button @click="onLogout">Cerrar sesión</button>
      </li>
    </ul>
  </div>
</template>

<style scoped>
.user-nav-container {
  position: relative;
}
.user-options-list {
  padding: 0.5rem 0.5rem;

  border: 1px solid rgb(0 0 0 / 0.08);
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 0.05);
  border-radius: 0.125rem;

  background-color: white;
  text-align: center;
  color: black;

  width: max-content;
  position: absolute;
  left: calc(-0.7 * 100%);
  top: calc(var(--navbar-height) - 5px);
  z-index: 101;
}


li {
  padding: 0.2rem 0.5rem;
}

li:hover {
  background-color: rgb(0 0 0 / 0.08);
  border-radius: 0.125rem;
}
</style>
