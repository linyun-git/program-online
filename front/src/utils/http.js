import axios from 'axios'
import networkConfig from '@/api/config'

const instance = axios.create({
  baseURL: networkConfig.baseURL,
  timeout: 5000
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

export default function request (config) {
  return instance(config)
}
