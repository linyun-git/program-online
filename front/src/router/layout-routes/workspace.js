export default {
  path: 'workspace/:workspaceId',
  component: () => import('@/views/workspace'),
  name: 'workspace',
  meta: {
    name: '仓库',
    title: '',
    icon: 'icon-name'
  }
}
