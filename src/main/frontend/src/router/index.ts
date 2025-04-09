import { createRouter, createWebHistory } from 'vue-router'
import CardList from '@/components/CardList.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/cards', name: 'CardList', component: CardList },
    {
      path: '/about',
      name: 'about',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('@/components/CardList.vue'),
    },
  ],
})

export default router
