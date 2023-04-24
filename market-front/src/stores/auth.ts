import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

type User = {
  id: number,
  firstName: string,
  lastName: string,
  email: string,
  birthdate: string,
  roles: string[]
};

interface AuthState {
  user: User | null,
  isLoading: boolean,
  error: string | null,
}

const useAuthStore = defineStore('auth', {
  state: (): AuthState => ({
    user: null,
    isLoading: false,
    error: null,
  }),
  getters: {
    username(): string {
      return this.user ? this.user.firstName : '';
    },
    id(): number {
      return this.user ? this.user.id : 0;
    }
  },
  actions: {
    loadUser(user: User) {
      this.user = user;
    },
    destroyUser() {
      this.user = null;
    },
    loading(status: boolean) {
      this.isLoading = status;
    }
  }
})

export default useAuthStore;