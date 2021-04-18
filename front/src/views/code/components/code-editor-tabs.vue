<template>
  <div class="code-editor-tabs-body">
    <el-tabs type="border-card" v-model="activeTabName">
      <el-tab-pane v-for="(item, index) in codes" :key="index" :label="item.label" :name="item.name"/>
    </el-tabs>
    <template v-for="item in codes" :key="item.name">
      <code-editor class="code-editor" v-if="item.name === activeTabName" :language="item.language"
                   v-model:code="item.code"
                   :theme="item.theme"></code-editor>
    </template>
  </div>
</template>

<script>
import CodeEditor from './code-editor'

export default {
  name: 'code-editor-tabs',
  components: {
    CodeEditor
  },
  data () {
    return {
      activeTabName: 'code2',
      codes: [
        {
          name: 'code1',
          label: 'code1',
          code: '// java',
          theme: 'vs-dark',
          language: 'java'
        },
        {
          name: 'code2',
          label: 'code2',
          code: '// javascript',
          theme: 'vs',
          language: 'javascript'
        },
        {
          name: 'code3',
          label: 'code3',
          code: '// typescript',
          theme: 'hc-black',
          language: 'typescript'
        }
      ]
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
</style>
