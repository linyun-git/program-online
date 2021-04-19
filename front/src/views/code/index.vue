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
      <code-editor-tabs class="editor-container"></code-editor-tabs>
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
  width: 100vw;
  overflow-y: hidden;

  .top-container {
    background-color: #DCDFE6;
  }

  .bottom-container {
    background-color: #fff;
    height: calc(100% - 65px);
    width: 100%;
    display: flex;

    .folders-container {
      border: 1px solid #409EFF;
      width: 300px;
    }

    .editor-container {
      height: 100%;
      width: calc(100% - 300px);
    }
  }
}
</style>
