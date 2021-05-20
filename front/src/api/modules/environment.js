import http from '@/utils/http'
import networkConfig from '../config'

// 查询环境
export const list = () => http(networkConfig.path.environment.list)
