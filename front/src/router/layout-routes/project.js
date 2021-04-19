export default {
  path: 'project/:projectId',
  component: () => import('@/views/project'),
  name: 'project',
  meta: {
    name: '项目',
    title: '',
    icon: 'icon-name'
  }
}
