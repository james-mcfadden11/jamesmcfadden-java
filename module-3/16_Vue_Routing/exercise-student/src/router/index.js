import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import MyBooks from '../views/MyBooks.vue';
import NewBook from '../views/NewBook.vue';
import ISBN from '../views/ISBN.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: Home,
    name: ''
  }, 
  {
    path: '/myBooks',
    component: MyBooks,
    name: 'my-books'
  },
  {
    path: '/addBook',
    component: NewBook,
    name: ''
  },
  {
    path: '/book/:isbn',
    component: ISBN,
    name: 'book-details'
  }
];

const router = new VueRouter({
  mode: 'history',
  routes
});

export default router;
