import http from '@/utils/http'
import networkConfig from '../config'

// 登录
export const login = params => http(networkConfig.path.user.login, params)

// 注册
export const register = params => http(networkConfig.path.user.register, params)

export const list = params => http(networkConfig.path.user.list, params)
