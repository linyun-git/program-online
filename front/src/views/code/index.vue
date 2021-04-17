<template>
  <div class="code-page-body">
    <div class="header-container">
      <code-header></code-header>
    </div>
    <div class="top-container">
      <code-path></code-path>
    </div>
    <div class="bottom-container">
      <div class="folders-container">
        <code-folders></code-folders>
      </div>
      <div class="editor-container">
        <code-editor-tabs></code-editor-tabs>
      </div>
    </div>
  </div>
</template>

<script>
import CodePath from './components/code-path'
import CodeHeader from './components/code-header'
import CodeFolders from './components/code-folders'
import CodeEditorTabs from './components/code-editor-tabs'

export default {
  name: 'index',
  components: {
    CodePath,
    CodeHeader,
    CodeFolders,
    CodeEditorTabs
  }
}
</script>

<style lang="less">
.code-page-body, .code-page-body * {
  box-sizing: border-box;
}
</style>

<style lang="less" scoped>
.code-page-body {
  height: 100vh;
  overflow-y: hidden;
  display: flex;
  flex-direction: column;

  .top-container {
    background-color: #DCDFE6;
  }

  .bottom-container {
    background-color: antiquewhite;
    flex: 1;
    display: flex;
    .folders-container {
      min-width: 300px;
      border: 1px solid #409EFF;
    }
    .editor-container {
      flex: 1;
      border: 1px solid #409EFF;
    }
  }
}
</style>
