import * as storage from './storage-tools'
import { instance } from './http'

/**
 * 存储token并登录
 * @param loginUrl 登录请求的地址
 * @param login 登录进行的操作
 */
export default function (loginUrl, login) {
  autoRequest(instance)
  autoLogin(loginUrl, login)
}

/**
 * 为Axios实例添加拦截器，在每次发送请求时添加token，在收到后端返回的token时保存token
 * @param httpInstance Axios实例
 */
const autoRequest = httpInstance => {
  httpInstance.interceptors.request.use(config => {
    config.headers.token = storage.getLocal('token')
    return config
  })
  httpInstance.interceptors.response.use(res => {
    const token = res.headers ? res.headers.token : null
    if (token === 'empty') {
      storage.removeLocal('token')
    } else if (token) {
      storage.setLocal('token', res.headers.token)
    }
    return res
  })
}

/**
 * 进行一次登录请求
 * @param loginUrl 请求地址
 * @param login 收到登录响应后执行的回调
 * @returns {Promise<never>|Promise<unknown>} 返回一个promise对象
 */
export const autoLogin = (loginUrl, login) => {
  const token = storage.getLocal('token')
  if (!token) {
    return Promise.reject(new Error('token无效'))
  }
  return new Promise((resolve, reject) => {
    instance.get(loginUrl).then(({ data: rep }) => {
      if (rep.code !== '200') {
        return reject(rep)
      }
      login(rep.data)
      resolve(rep.data)
    })
  })
}
