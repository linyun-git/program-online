import axios from 'axios'
import networkConfig from '@/api/config'

const instance = axios.create({
  baseURL: networkConfig.baseURL,
  timeout: networkConfig.timeout
})

instance.interceptors.request.use(
  config => {
    console.log(config)
    return config
  }, err => {
    console.log(err)
  }
)

instance.interceptors.response.use(
  res => {
    console.log(res)
    return res.data
  },
  err => {
    console.log(err)
  }
)

function get (url, params = {}, options = {}) {
  return new Promise((resolve, reject) => {
    instance.get(url, {
      params,
      ...options
    })
      .then(response => resolve(response))
      .catch(error => reject(error))
  })
}

function post (url, params = {}, options = {}) {
  return new Promise((resolve, reject) => {
    instance.post(url, params, options)
      .then(response => resolve(response))
      .catch(error => reject(error))
  })
}

export default function request (config = {}, params = {}, options = {}) {
  if (config.method === 'get') {
    return get(config.url, params, options)
  }
  if (config.method === 'post') {
    return post(config.url, params, options)
  }
  throw Error(`不支持的方法: ${config.method}`)
}
