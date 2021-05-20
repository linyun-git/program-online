<template>
  <div class="code-folders-body" @contextmenu.prevent.stop="onRightClick">
    <el-tree :data="fileNodes" @node-click="onNodeClick" @node-contextmenu="onRightClick">
      <template #default="{ data }">
        <type-file v-if="data.fileType === 'folder'" :file-name="data.fileName"
                   folder/>
        <type-file v-else :file-name="data.fileName"/>
      </template>
    </el-tree>
    <el-card v-show="displayMenu" class="code-folders-menu"
             :style="{top: menuLocation.y + 'px', left: menuLocation.x + 'px'}">
      <div v-for="item in menu" :key="item.title" class="code-folders-menu-item" @click="item.action()">
        {{ item.title }}
      </div>
    </el-card>
    <el-dialog
      title="新建文件"
      v-model="createFileValue.show"
      width="300px">
      <el-input v-model="createFileValue.fileName"></el-input>
      <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="createFile">确 定</el-button>
    </span>
      </template>
    </el-dialog>
    <el-dialog
      title="新建文件夹"
      v-model="createFolderValue.show"
      width="300px">
      <el-input v-model="createFolderValue.fileName"></el-input>
      <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="createFolder">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
let MENU = []

export default {
  name: 'code-folders',
  props: {
    projectId: Number
  },
  data () {
    return {
      fileNodes: [],
      project: {},
      menu: [],
      menuLocation: {
        x: 0,
        y: 0
      },
      displayMenu: false,
      actionFileNode: null,
      createFileValue: {
        fileName: '',
        show: false
      },
      createFolderValue: {
        fileName: '',
        show: false
      }
    }
  },
  methods: {
    onRightClick (event, fileNode) {
      if (!fileNode) {
        this.displayMenu = false
        return
      }
      console.log(fileNode)
      this.actionFileNode = fileNode
      let menu = []
      if (fileNode.fileType === 'file') {
        menu = [MENU[2]]
      } else if (fileNode.filePath === '') {
        menu = [MENU[0], MENU[1]]
      } else {
        menu = [MENU[0], MENU[1], MENU[3]]
      }
      this.showMenu({
        x: event.clientX,
        y: event.clientY
      }, menu)
    },
    showMenu ({
      x,
      y
    }, menu = []) {
      this.menuLocation.x = x
      this.menuLocation.y = y
      this.menu = menu
      this.displayMenu = true
    },
    queryPath () {
      const params = {
        projectId: this.projectId
      }
      this.$api.project.pathInfo(params).then(rep => {
        this.appendNodes(rep.data)
      }).catch(({ msg }) => this.$message.error(msg))
    },
    queryInfo () {
      const params = {
        projectId: this.projectId
      }
      return this.$api.project.info(params).then(rep => {
        this.project = rep.data
      }).catch(({ msg }) => this.$message.error(msg))
    },
    appendNodes (fileNodes) {
      this.fileNodes = [
        {
          fileName: this.project.name,
          fileType: 'folder',
          filePath: '',
          children: fileNodes
        }
      ]
    },
    onNodeClick (fileNode) {
      if (fileNode.fileType === 'file') {
        this.editFile(fileNode)
      }
      this.$emit('node-click', fileNode)
    },
    editFile (fileNode) {
      const {
        fileName,
        fileType,
        filePath
      } = fileNode
      this.$emit('edit-file', {
        fileName,
        fileType,
        filePath
      })
    },
    toCreateFile () {
      this.createFileValue.fileName = ''
      this.createFileValue.show = true
    },
    createFile () {
      if (!this.createFileValue.fileName) {
        this.$message.error('文件名不能为空')
        return
      }
      const params = {
        projectId: this.projectId,
        path: (this.actionFileNode.filePath ? (this.actionFileNode.filePath + '/') : '') + this.createFileValue.fileName,
        fileType: 'file'
      }
      this.$api.project.createFile(params).then(() => {
        this.actionFileNode.children.push({
          fileName: this.createFileValue.fileName,
          fileType: 'file',
          children: null,
          filePath: params.path
        })
        this.createFileValue.show = false
      }).catch(({ msg }) => this.$message.error(msg))
    },
    toCreateFolder () {
      this.createFolderValue.fileName = ''
      this.createFolderValue.show = true
    },
    createFolder () {
      if (!this.createFolderValue.fileName) {
        this.$message.error('文件名不能为空')
        return
      }
      const params = {
        projectId: this.projectId,
        path: (this.actionFileNode.filePath ? (this.actionFileNode.filePath + '/') : '') + this.createFolderValue.fileName,
        fileType: 'folder'
      }
      this.$api.project.createFile(params).then(() => {
        this.actionFileNode.children.push({
          fileName: this.createFolderValue.fileName,
          fileType: 'folder',
          children: [],
          filePath: params.path
        })
        this.createFolderValue.show = false
      }).catch(({ msg }) => this.$message.error(msg))
    },
    deleteFile () {
      const params = {
        projectId: this.projectId,
        path: this.actionFileNode.filePath,
        fileType: 'file'
      }
      this.$api.project.deleteFile(params).then(() => {
        location.reload()
      }).catch(({ msg }) => this.$message.error(msg))
    },
    deleteFolder () {
      const params = {
        projectId: this.projectId,
        path: this.actionFileNode.filePath,
        fileType: 'folder'
      }
      this.$api.project.deleteFile(params).then(() => {
        location.reload()
      }).catch(({ msg }) => this.$message.error(msg))
    }
  },
  created () {
    this.queryInfo().then(() => {
      this.queryPath()
    })
    document.body.addEventListener('click', () => {
      this.displayMenu = false
    })
    MENU = [
      {
        title: '新建文件',
        action: () => {
          this.toCreateFile()
        }
      },
      {
        title: '新建文件夹',
        action: () => {
          this.toCreateFolder()
        }
      },
      {
        title: '删除文件',
        action: () => {
          this.deleteFile()
        }
      },
      {
        title: '删除文件夹',
        action: () => {
          this.deleteFolder()
        }
      }
    ]
  }
}
</script>

<style lang="less">
.code-folders-body {
  .el-card__body {
    padding: 0;
  }
}
</style>

<style lang="less" scoped>
.code-folders-body {
  min-height: 100%;
  background-color: #fff;
  overflow: auto;

  .code-folders-menu {
    position: fixed;
    z-index: 1;

    .code-folders-menu-item {
      padding: 5px 20px;
      min-width: 200px;
      cursor: pointer;
    }

    .code-folders-menu-item:hover {
      background-color: #ecf5ff;
    }

    .code-folders-menu-item:not(:last-child) {
      border-bottom: 1px solid #DCDFE6;
    }
  }
}
</style>
