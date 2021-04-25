import http from '@/utils/http'
import networkConfig from '../config'

// 登录
export const login = params => http(networkConfig.path.user.login, params)

// 注册
export const register = params => http(networkConfig.path.user.register, params)

// 登出
export const logout = () => http(networkConfig.path.user.logout)

export const list = params => http(networkConfig.path.user.list, params)

// 修改信息
export const update = params => http(networkConfig.path.user.update, params)

// 查询用户信息
export const query = (params) => http(networkConfig.path.user.query, {}, {}, params)
