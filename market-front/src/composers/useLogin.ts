import useAuthStore from "@/stores/auth";
import { ref } from "vue";
import { login } from "@/services/user";

export function useLogin() {
  const authStore = useAuthStore();
  const isLoading = ref(false);
  const isError = ref(false);

  
  return { login, isLoading, isError };
}