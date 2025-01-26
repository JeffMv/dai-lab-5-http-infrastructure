import { createRouter, createWebHistory } from 'vue-router';
import Home from './views/Home.vue';
import Count from './views/Count.vue';
import Task from './views/Task.vue';

const routes = [
  { path: '/', component: Home }, // Route "/"
  { path: '/count', component: Count },
  { path: '/task', component: Task},
];

const router = createRouter({
  history: createWebHistory(), // Utilise le mode historique du navigateur (sans hash #)
  routes,
});

export default router;
