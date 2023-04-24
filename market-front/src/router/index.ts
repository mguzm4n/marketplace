import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import ProductList from '../components/ProductList.vue';
import PostProduct from '../views/PostProduct.vue';
import SignIn from '../views/SignIn.vue';
import ProductView from '../views/ProductView.vue';
import ProductMessages from '../views/ProductMessages.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: ProductList
    },
    {
      path: '/post',
      name: 'post',
      component: PostProduct
    },
    {
      path: '/sign-in',
      name: 'sign-in',
      component: SignIn
    },
    {
      path: '/product/:productId',
      name: 'product-view',
      component: ProductView,
    },
    {
      path: '/product/:productId/messages',
      name: 'product-messages',
      component: ProductMessages,
    }
  ]
})

export default router
