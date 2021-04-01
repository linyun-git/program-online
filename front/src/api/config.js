export default {
  baseURL: 'http://localhost:3300',
  timeout: 5000,
  headers: {
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json; charset=UTF-8'
  },
  path: {
    user: {
      login: {
        url: '/login',
        method: 'post'
      },
      register: {
        url: '/register',
        method: 'post'
      }
    },
    workspace: {
      list: {
        url: '',
        method: 'post'
      }
    }
  }
}
