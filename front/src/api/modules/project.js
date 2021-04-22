import http from '@/utils/http'
import networkConfig from '../config'

// 新建仓库
export const add = params => http(networkConfig.path.project.add, params)

// 查询仓库
export const list = params => http(networkConfig.path.project.list, params)
