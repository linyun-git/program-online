import axios from 'axios'

const instance = axios.create({
  baseURL: 'http://localhost',
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
