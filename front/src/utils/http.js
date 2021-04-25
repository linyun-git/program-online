import axios from 'axios'
import networkConfig from '@/api/config'

const instance = axios.create({
  baseURL: networkConfig.baseURL,
  timeout: networkConfig.timeout
})

instance.interceptors.request.use(
  config => {
    return config
  }, err => {
    return err
  }
)

instance.interceptors.response.use(
  res => {
    return res
  },
  err => {
    return err
  }
)

function get (url, params = {}, options = {}) {
  return new Promise((resolve, reject) => {
    instance.get(url, {
      params,
      ...options
    })
      .then(({ data: response }) => {
        if (response.code === '200') {
          resolve(response)
        } else {
          reject(response)
        }
      })
      .catch(() => {
        return {
          code: '404',
          msg: '网络错误'
        }
      })
  })
}

function post (url, params = {}, options = {}) {
  return new Promise((resolve, reject) => {
    instance.post(url, params, options)
      .then(({ data: response }) => {
        if (response.code === '200') {
          resolve(response)
        } else {
          reject(response)
        }
      })
      .catch(() => {
        return {
          code: '404',
          msg: '网络错误'
        }
      })
  })
}

export { instance }

export default function request (config = {}, params = {}, options = {}, pathParams = {}) {
  if (config.restful) {
    return restful(config, params, options, pathParams)
  }
  if (config.method === 'get') {
    return get(config.url, params, options)
  }
  if (config.method === 'post') {
    return post(config.url, params, options)
  }
  throw Error(`不支持的方法: ${config.method}`)
}

function restful (config = {}, params = {}, options = {}, pathParams = {}) {
  let url = config.url
  for (const key of Object.keys(pathParams)) {
    const value = pathParams[key]
    // 默认两边大括号
    const slashKey = '{' + key + '}'
    url = url.replace(slashKey, value)
  }
  const newConfig = {
    ...config,
    url,
    restful: false
  }
  return request(newConfig, params, options)
}
