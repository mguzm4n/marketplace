<script setup lang="ts">
import useAuthStore from './stores/auth';
import { RouterLink, RouterView } from 'vue-router'
import NavBar from './components/NavBar.vue';
const authStore = useAuthStore();

</script>

<template>
  <NavBar />
  <div class="container">
    <div class="main">
      <RouterView />
    </div>
  </div>
</template>

<style scoped>
.container {
  margin-top: var(--navbar-height);
  
  display: flex;
  justify-content: center;
}

.main {
  border-radius: 0.125rem;
  border: 1px solid rgb(0 0 0 / 0.02);
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 0.05);
  background-color: var(--main-bg-color);

  margin-top: 2rem;
  padding: 2rem;
  width: 78%;
  min-height: 350px;
  
}
</style>
