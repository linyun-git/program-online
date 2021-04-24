export default {
  baseURL: 'http://localhost:9000',
  // baseURL: 'http://service.explosion.red',
  timeout: 5000,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json; charset=UTF-8'
  },
  path: {
    user: {
      autoLogin: {
        url: '/user/login',
        method: 'get'
      },
      login: {
        url: '/user/login',
        method: 'post'
      },
      register: {
        url: '/user/register',
        method: 'post'
      },
      logout: {
        url: '/user/logout',
        method: 'get'
      },
      list: {
        url: '/user/list',
        method: 'post'
      }
    },
    workspace: {
      add: {
        url: '/workspace/add',
        method: 'post'
      },
      list: {
        url: '/workspace/list',
        method: 'post'
      }
    },
    project: {
      add: {
        url: '/project/add',
        method: 'post'
      },
      list: {
        url: '/project/list',
        method: 'post'
      }
    }
  }
}
