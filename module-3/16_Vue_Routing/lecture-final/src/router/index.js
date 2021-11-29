import Vue from 'vue'
import VueRouter from 'vue-router'
// import ProductsList from '../components/ProductsList.vue';
import ProductsList from '@/views/ProductsList.vue'
import ProductDetail from '@/views/ProductDetail.vue'
import AddReview from '@/views/AddReview.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    component: ProductsList,
    name: 'products-list'
  },

  {
    path: '/products/:productId',
    component: ProductDetail,
    name: 'product-detail'
  },

  {
    path: '/products/:id/add-review',
    name: 'add-review',
    component: AddReview
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
