<template>
  <div class="code-editor-tabs-body">
    <el-tabs type="border-card">
      <el-tab-pane label="tab1" />
      <el-tab-pane label="tab2" />
      <el-tab-pane label="tab3" />
      <el-tab-pane label="tab4" />
    </el-tabs>
    <code-editor class="code-editor"></code-editor>
  </div>
</template>

<script>
import CodeEditor from './code-editor'

export default {
  name: 'code-editor-tabs',
  components: {
    CodeEditor
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
  display: flex;
  flex-direction: column;
  height: 100%;

  .code-editor {
    flex: 1;
  }
}
</style>
