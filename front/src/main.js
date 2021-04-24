import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
import api from './api'
import message from './utils/message'
import components from './components'
import autoLogin from './utils/login-tools'
import networkConfig from './api/config'
// 全局守卫
import './router/resolver'

const app = createApp(App)
// 引入网络请求
app.config.globalProperties.$api = api
// 使用插件
app.use(store).use(router).use(ElementPlus).use(components)
// 自动登录
autoLogin(networkConfig.path.user.autoLogin.url, info => {
  if (!info) {
    return
  }
  store.commit('user/Add_USER_INFO', info)
})
// 引入消息工具
app.config.globalProperties.$message = message
// 挂载
app.mount('#app')
document.title = 'CodeOnline'
