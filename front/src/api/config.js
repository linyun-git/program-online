export default {
  baseURL: 'http://localhost:9000',
  timeout: 5000,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json; charset=UTF-8'
  },
  path: {
    user: {
      login: {
        url: '/user/login',
        method: 'post'
      },
      register: {
        url: '/user/register',
        method: 'post'
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
