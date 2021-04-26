<template>
  <div class="code-editor-tabs-body">
    <el-tabs type="border-card" v-model="activeTabName">
      <el-tab-pane v-for="(item, index) in codes" :key="item.name" :label="item.label" :name="item.name">
        <template #label>
          <span :class="{'code-change': item.codeChange}">{{ item.label }}</span>
          <i class="el-icon-close" @click="onCloseTab(index, $event)"></i>
        </template>
      </el-tab-pane>
    </el-tabs>
    <template v-for="(item, index) in codes" :key="item.name">
      <code-editor class="code-editor" v-show="item.name === activeTabName" :language="item.language"
                   v-model:code="item.code"
                   @update:code="codeUpdate(index)"
                   @save="onSave(index)"
                   @focus="onFocus(index)"
                   :theme="item.theme"></code-editor>
    </template>
  </div>
</template>

<script>
import CodeEditor from './code-editor'
import { getLanguageByFileName } from '@/utils/language-tools'

export default {
  name: 'code-editor-tabs',
  components: {
    CodeEditor
  },
  props: {
    projectId: Number
  },
  data () {
    return {
      activeTabName: 'code2',
      codes: []
    }
  },
  methods: {
    codeUpdate (index) {
      this.codes[index].codeChange = true
    },
    onSave (index) {
      const codeItem = this.codes[index]
      const params = {
        projectId: this.projectId,
        path: codeItem.filePath,
        content: codeItem.code
      }
      this.$api.project.saveFile(params).then(rep => {
        this.$message.success(`保存成功: ${codeItem.label}`)
        codeItem.codeChange = false
      }).catch(({ msg }) => this.$message.error(msg))
    },
    onCloseTab (index, $event) {
      if (this.activeTabName === this.codes[index].name) {
        this.activeTabName = this.codes[index - 1] ? this.codes[index - 1].name : null
      }
      this.codes.splice(index, 1)
      // 阻止事件冒泡
      $event.stopPropagation()
    },
    onFocus (index) {
      this.$emit('editor-focus', this.codes[index])
    },
    // 编辑
    toEdit (fileNode) {
      const codeItem = this.codes.find(item => item.filePath === fileNode.filePath)
      if (codeItem) {
        this.activeTabName = codeItem.name
        return
      }
      this.newEditor(fileNode)
    },
    newEditor (fileNode) {
      this.queryContent(this.projectId, fileNode.filePath).then(content => {
        const name = new Date().getTime()
        this.codes.push({
          ...fileNode,
          name,
          label: fileNode.fileName,
          code: content,
          theme: 'vs',
          language: getLanguageByFileName(fileNode.fileName)
        })
        this.activeTabName = name
      })
    },
    queryContent (projectId, path) {
      const params = {
        projectId,
        path
      }
      return this.$api.project.fileContent(params).then(rep => rep.data).catch(err => err.msg)
    }
  },
  watch: {
    activeTabName (value) {
      const codeItem = this.codes.find(item => item.name === value)
      if (codeItem) {
        this.$emit('editor-focus', codeItem)
      }
    }
  }
}
</script>

<style lang="less">
.code-editor-tabs-body {
  .el-tabs__content {
    display: none;
  }

  .el-tabs__header {
    border-bottom: none;
  }
}
</style>

<style lang="less" scoped>
.code-editor-tabs-body {
  .code-editor {
    height: calc(100% - 39px);
    width: 100%;
  }
}

.code-change {
  position: relative;

  &::after {
    position: absolute;
    content: '*';
    display: block;
    top: 0;
    right: -6px;
    height: 1em;
    text-align: center;
    color: #F56C6C;
    line-height: 1em;
  }
}
</style>
