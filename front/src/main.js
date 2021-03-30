import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/lib/theme-chalk/index.css'
import api from './api'

const app = createApp(App)
// 引入网络请求
app.config.globalProperties.$api = api
// 使用插件
app.use(store).use(router).use(ElementPlus)
// 挂载
app.mount('#app')
