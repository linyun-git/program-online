import Logo from './logo'
import TypeFile from './type-file'
import UserList from './user-list'
import WorkspaceList from './workspace-list'
import ProjectList from './project-list'

function install (Vue) {
  Vue.component(Logo.name, Logo)
  Vue.component(WorkspaceList.name, WorkspaceList)
  Vue.component(ProjectList.name, ProjectList)
  Vue.component(TypeFile.name, TypeFile)
  Vue.component(UserList.name, UserList)
}

export default { install }
