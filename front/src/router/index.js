import { createRouter, createWebHistory } from 'vue-router'
import home from './layout-routes/home'
import search from './layout-routes/search'
import workspace from './layout-routes/workspace'
import project from './layout-routes/project'

const routes = [
  {
    path: '',
    name: 'layout',
    component: () => import('@/views/layout'),
    children: [
      home,
      search,
      workspace,
      project
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login')
  },
  {
    path: '/code/:projectId',
    component: () => import('@/views/code')
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
