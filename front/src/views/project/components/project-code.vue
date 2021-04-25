<template>
  <el-card class="project-code-body" shadow="never">
    <div class="project-code-header">
      ProjectName
      <span v-for="(fileName) in currentPath" :key="fileName">/{{ fileName }}</span>
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
        <type-file v-if="fileNode.fileType === 'folder'" :file-name="fileNode.fileName" @onFileNameClick="pathChange(index)"
                   folder/>
        <type-file v-else :file-name="fileNode.fileName" @onFileNameClick="toShowFile"/>
      </div>
    </template>
  </el-card>
</template>

<script>
export default {
  name: 'project-code',
  data () {
    return {
      fileNodes: [],
      path: [],
      fileCode: '// code \n',
      showFileCode: false
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
    pathChange (index = -1) {
      if (index === -1) {
        this.path.pop()
        return
      }
      this.path.push(index)
    },
    toShowFile (index = -1) {
      this.showFileCode = true
    },
    queryPathNodes () {
      const params = {
        projectId: this.projectId
      }
      this.$api.project.pathInfo(params).then(rep => {
        this.fileNodes = rep.data
      }).catch(({ msg }) => this.$message.error(msg))
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
