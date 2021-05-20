import http from '@/utils/http'
import networkConfig from '../config'

// 新建仓库
export const add = params => http(networkConfig.path.project.add, params)

// 查询仓库
export const list = params => http(networkConfig.path.project.list, params)

// 查询仓库
export const pathInfo = params => http(networkConfig.path.project.pathInfo, {}, {}, params)
  .then(rep => {
    fillPath(rep.data)
    return rep
  })

// 查询文件内容
export const fileContent = params => http(networkConfig.path.project.fileContent, params)

// 保存文件
export const saveFile = params => http(networkConfig.path.project.saveFile, params)

// 查询项目信息
export const info = params => http(networkConfig.path.project.info, {}, {}, params)

// 查询文件内容
export const createFile = params => http(networkConfig.path.project.createFile, params)

// 查询文件内容
export const deleteFile = params => http(networkConfig.path.project.deleteFile, params)

// 填充每个文件对应的路径
const fillPath = (fileNodes, prefixPath) => {
  fileNodes.forEach(fileNode => {
    const prefix = prefixPath ? `${prefixPath}/` : ''
    fileNode.filePath = prefix + fileNode.fileName
    if (fileNode.children) {
      fillPath(fileNode.children, fileNode.filePath)
    }
  })
}
