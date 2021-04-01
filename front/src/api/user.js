import http from '@/utils/http'
import networkConfig from './config'

export default {
  login: params => http(networkConfig.path.user.login, params)
}
