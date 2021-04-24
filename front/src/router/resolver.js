import router from './index'
import store from '../store'
import { autoLogin } from '../utils/login-tools'
import networkConfig from '../api/config'

router.beforeEach((to, from, next) => {
  if (to.meta && to.meta.loginResolve) {
    if (store.getters['user/userInfo'].id === null) {
      autoLogin(networkConfig.path.user.autoLogin.url, () => 0)
        .then(info => {
          store.commit('user/Add_USER_INFO', info)
          next()
        })
        .catch(() => {
          next('/login')
        })
      return
    } else {
      next()
      return
    }
  }
  if (to.meta && to.meta.unLoginResolve) {
    if (store.getters['user/userInfo'].id === null) {
      next()
      return
    } else {
      next(false)
      return
    }
  }
  next()
})

router.afterEach(to => {
  if (to.meta && to.meta.title) {
    document.title = to.meta.title
  }
})
