import axios from 'axios'
import networkConfig from '@/api/config'

/**
 * 创建一个Axios实例，这里定制化了http请求地址的根路径和超时时间
 * @type {AxiosInstance} 通过定制化Axios实例来控制请求行为
 */
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

/**
 * 统一封装的get请求操作
 * @param url 请求路径
 * @param params 请求参数
 * @param options 额外的请求配置
 * @returns {Promise<unknown>} 返回promise对象
 */
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

/**
 * 统一封装的post请求操作
 * @param url 请求路径
 * @param params 请求参数
 * @param options 额外的请求配置
 * @returns {Promise<unknown>} 返回promise对象
 */
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

/**
 * 通过导出Axios实例可以实现
 */
export { instance }

/**
 * 统一请求方法，在本系统中都将通过该方法进行http请求操作
 * @param config 统一封装的请求参数对象，包括：请求方法、请求路径、请求路径风格
 * @param params 请求参数
 * @param options 额外的请求配置
 * @param pathParams 路径参数
 * @returns {*|Promise<minimist.Opts.unknown>} 返回一个promise对象
 */
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

/**
 * 处理restful风格的请求
 * @param config 统一封装的请求参数对象，包括：请求方法、请求路径、请求路径风格
 * @param params 请求参数
 * @param options 额外的请求配置
 * @param pathParams 路径参数
 * @returns {*|Promise<minimist.Opts.unknown>} 返回一个promise对象
 */
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
