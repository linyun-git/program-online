<template>
  <div class="code-folders-body" @contextmenu.prevent.stop="onRightClick">
    <el-tree :data="fileNodes" @node-click="onNodeClick">
      <template #default="{ data }">
        <type-file v-if="data.fileType === 'folder'" :file-name="data.fileName"
                   folder/>
        <type-file v-else :file-name="data.fileName"/>
      </template>
    </el-tree>
  </div>
</template>

<script>
export default {
  name: 'code-folders',
  props: {
    projectId: Number
  },
  data () {
    return {
      fileNodes: [],
      project: {}
    }
  },
  methods: {
    onRightClick () {
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
    }
  },
  created () {
    this.queryInfo().then(() => {
      this.queryPath()
    })
  }
}
</script>

<style lang="less" scoped>
.code-folders-body {
  min-height: 100%;
  background-color: #fff;
  overflow: auto;
}
</style>
