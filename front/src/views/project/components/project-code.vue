<template>
  <el-card class="project-code-body" shadow="never">
    <div class="project-code-header">
      <span class="history-path" @click="pathDeepTo(0)">{{ project.name }}</span>
      <span class="history-path" v-for="(fileName, index) in currentPath" :key="fileName"
            @click="pathDeepTo(index + 1)">/{{ fileName }}</span>
      <span v-if="showFileCode">/{{ showFileName }}</span>
    </div>
    <div v-if="showFileCode" class="project-code-file-code">
      <el-input
        readonly
        type="textarea"
        :autosize="{ minRows: 5}"
        v-model="fileCode">
      </el-input>
    </div>
    <template v-else>
      <div v-show="path.length > 0" class="project-code-file">
        <span class="project-code-path-back" @click="pathChange(-1)"></span>
      </div>
      <div v-for="(fileNode, index) in currentNodes" :key="index" class="project-code-file">
        <type-file v-if="fileNode.fileType === 'folder'" :file-name="fileNode.fileName"
                   @onFileNameClick="pathChange(index)"
                   folder/>
        <type-file v-else :file-name="fileNode.fileName" @onFileNameClick="toShowFile(index)"/>
      </div>
    </template>
  </el-card>
</template>

<script>
export default {
  name: 'project-code',
  props: {
    project: Object
  },
  data () {
    return {
      fileNodes: [],
      path: [],
      fileCode: '',
      showFileCode: false,
      showFileName: null
    }
  },
  computed: {
    currentNodes () {
      return this.getNodes(this.path, this.fileNodes)
    },
    currentPath () {
      const pathNameList = []
      let nodes = this.fileNodes
      for (const index of this.path) {
        pathNameList.push(nodes[index].fileName)
        nodes = nodes[index].children
      }
      return pathNameList
    },
    projectId () {
      return parseInt(this.$route.params.projectId, 10)
    }
  },
  methods: {
    getNodes (path = [], nodes = []) {
      if (path.length === 0) {
        return nodes
      }
      if (path.length === 1) {
        return nodes[path[0]].children
      }
      const [, ...newPath] = path
      return this.getNodes(newPath, nodes[path[0]].children)
    },
    pathDeepTo (index) {
      while (this.currentPath.length > index) {
        this.path.pop()
      }
      if (this.showFileCode) {
        this.showFileCode = false
        this.showFileName = null
        this.fileCode = ''
      }
    },
    pathChange (index = -1) {
      if (index === -1) {
        this.path.pop()
        return
      }
      this.path.push(index)
    },
    toShowFile (index = -1) {
      const currentFileName = this.currentNodes[index].fileName
      const filePath = this.currentPath.join('/') + '/' + currentFileName
      this.queryContent(this.projectId, filePath).then(content => {
        this.fileCode = content
        this.showFileCode = true
        this.showFileName = currentFileName
      }).catch(err => this.$message.error(err))
    },
    queryPathNodes () {
      const params = {
        projectId: this.projectId
      }
      this.$api.project.pathInfo(params).then(rep => {
        this.fileNodes = rep.data
      }).catch(({ msg }) => this.$message.error(msg))
    },
    queryContent (projectId, path) {
      const params = {
        projectId,
        path
      }
      return this.$api.project.fileContent(params).then(rep => rep.data).catch(err => err.msg)
    }
  },
  created () {
    this.queryPathNodes()
  }
}
</script>

<style lang="less">
.project-code-body {

  .el-card__body {
    padding: 0
  }

  .el-card {
    overflow: hidden;
  }

  .project-code-file-code {
    padding: 5px;
    display: flex;
    justify-content: center;

    textarea, textarea:focus {
      resize: none;
      width: 100%;
      border-style: none;
      outline: none;
    }
  }
}
</style>

<style lang="less" scoped>
.project-code-body {
  border: 1px;

  .project-code-header {
    background-color: #ecf5ff;
    height: 30px;
    padding: 5px 10px;
    display: flex;
    align-items: center;

    .history-path {
      cursor: pointer;
      margin-right: 2px;
    }
  }

  .project-code-file {
    height: 30px;
    padding: 5px 10px;
    display: flex;
    align-items: center;

    .project-code-path-back {
      cursor: pointer;
      margin-left: 20px
    }

    .project-code-path-back::after {
      content: '..';
    }
  }

  .project-code-file:not(:last-child) {
    border-bottom: 1px solid #eaeefb;
  }
}
</style>
