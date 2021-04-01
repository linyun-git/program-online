import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '',
    name: 'layout',
    component: () => import('@/views/layout'),
    children: [
      {
        path: '',
        component: () => import('@/views/home'),
        name: 'home',
        meta: {
          // 匹配规则
          name: '首页',
          title: '',
          icon: 'icon-name'
        }
      },
      {
        path: 'search',
        component: () => import('@/views/search'),
        name: 'home',
        meta: {
          // 匹配规则
          name: '搜索',
          title: '',
          icon: 'icon-name'
        }
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login')
  },
  {
    path: '/page-not-found',
    component: () => import('@/views/error-page/page-not-found')
  },
  {
    path: '/:pathMatch(.*)*',
    redirect: '/page-not-found'
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
