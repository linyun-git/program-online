import { createRouter, createWebHistory } from 'vue-router'
import home from './layout-routes/home'
import search from './layout-routes/search'
import workspace from './layout-routes/workspace'
import project from './layout-routes/project'
import space from './layout-routes/space'
import newWorkspace from './layout-routes/new-workspace'
import newProject from './layout-routes/new-project'

const routes = [
  {
    path: '',
    name: 'layout',
    component: () => import('@/views/layout'),
    children: [
      home,
      search,
      workspace,
      project,
      space,
      newWorkspace,
      newProject
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    meta: {
      unLoginResolve: true,
      title: '登录',
      name: '登录'
    }
  },
  {
    path: '/code/:projectId',
    component: () => import('@/views/code'),
    meta: {
      loginResolve: true,
      title: 'Code',
      name: 'Code'
    }
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
