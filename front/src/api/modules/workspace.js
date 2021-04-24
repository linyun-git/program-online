import http from '@/utils/http'
import networkConfig from '../config'

// 新建仓库
export const add = params => http(networkConfig.path.workspace.add, params)

// 查询仓库
export const list = params => http(networkConfig.path.workspace.list, params)

// 查询仓库
export const listByUid = params => http(networkConfig.path.workspace.listByUid, {}, {}, params)
