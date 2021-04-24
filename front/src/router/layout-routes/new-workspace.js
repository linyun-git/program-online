export default {
  path: 'new-workspace',
  component: () => import('@/views/new-workspace'),
  name: 'new-workspace',
  meta: {
    name: '新建',
    title: '',
    icon: 'icon-name',
    loginResolve: true
  }
}
