import Logo from './logo'
import WorkspaceItem from './workspace-item'
import WorkspaceList from './workspace-list'

function install (Vue) {
  Vue.component(Logo.name, Logo)
  Vue.component(WorkspaceItem.name, WorkspaceItem)
  Vue.component(WorkspaceList.name, WorkspaceList)
}

export default { install }
