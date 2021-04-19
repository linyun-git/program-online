import Logo from './logo'
import WorkspaceList from './workspace-list'
import ProjectList from './project-list'
import TypeFile from './type-file'

function install (Vue) {
  Vue.component(Logo.name, Logo)
  Vue.component(WorkspaceList.name, WorkspaceList)
  Vue.component(ProjectList.name, ProjectList)
  Vue.component(TypeFile.name, TypeFile)
}

export default { install }
