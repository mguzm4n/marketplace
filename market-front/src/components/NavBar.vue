<script setup lang="ts">
import { addClassnameMode, toggleClasslist } from '../utils/theme';
import { onMounted, ref } from 'vue';
import { RouterLink } from 'vue-router'
import { PhSun, PhMoon } from '@phosphor-icons/vue';
import UserNav from './UserNav.vue';
import { checkLogin } from '@/services/user';
import useAuthStore from '@/stores/auth';

const isLightMode = ref(true);
const authStore = useAuthStore();

async function checkAuth() {
  authStore.loading(true);
  try {
    const checkReq = await checkLogin();
    authStore.loadUser(checkReq.data);
  } catch (e: any) {
    console.error(e);
    if (e.status === 401) {
      authStore.destroyUser();
    }
  } finally {
    authStore.loading(false);
  }
}
onMounted(() => {
  checkAuth();
  
  let lightModeFlag = localStorage.getItem('lightMode');
  if (lightModeFlag) { // There's a remembered mode on.
    const lightMode = (lightModeFlag === 'true');
    isLightMode.value = lightMode;
    if (lightMode) {
      toggleClasslist({ add: 'light', remove: 'dark' });
    } else {
      toggleClasslist({ add: 'dark', remove: 'light' });
    }
    return;
  }

  if ( // No mode found on local storage.
      window.matchMedia && 
      window.matchMedia('(prefers-color-scheme: dark)').matches
    ) {
    document.documentElement.classList.add("dark");
    localStorage.setItem('lightMode', 'false');
  } else {
    document.documentElement.classList.add("light");
    localStorage.setItem('lightMode', 'true');
  }
  isLightMode.value = localStorage.getItem('lightMode') === 'true';
});

function toggleMode() {
  addClassnameMode();
  isLightMode.value = !isLightMode.value;
}
</script>


<template>
  <div class="navbar">
    <div class="navbar-left">
      
      <RouterLink to="/">MarketPlace</RouterLink>
      <button @click="toggleMode">
        <ph-sun class="theme-ico" v-if="isLightMode" color="white" :size="18" />
        <ph-moon class="theme-ico" v-else color="white" :size="18" />
      </button>
    </div>

    <div class="navbar-right">
      <div v-if="!authStore.user">
        <RouterLink to="/sign-in">Iniciar Sesión</RouterLink>
      </div>
      <div v-else>
        <RouterLink to="/post" class="post-btn">
          Subir Producto
        </RouterLink>
        <UserNav />
      </div>
    </div>
  </div>
</template>

<style scoped>
.navbar {
  z-index: 100;
  position: fixed;
  top: 0;
  
  display: flex;
  justify-content: space-between;
  align-items: center;

  width: 100%;
  height: var(--navbar-height);
  padding: 1rem 2.5rem;

  background-color: var(--blue-950);
  color: white;
}

.navbar-left, .navbar-right div {
  font-size: 15px;
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.post-btn {
  background-color: var(--post-btn-color);
  border-radius: 0.2rem;
  padding: 0.2rem 0.8rem;
  margin-right: 0.5rem;
}

.theme-ico {
  margin-top: 5px;
}
</style>
