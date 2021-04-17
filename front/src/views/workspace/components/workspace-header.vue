<template>
  <div class="workspace-header-body">
    <div class="main-container">
      <h3 style="margin: 0">Dev Workspace</h3>
      <div class="tab-container">
        <a class="tab-button active">项目 <el-tag>{{count}}</el-tag></a>
        <a class="tab-button">设置</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'workspace-header',
  data () {
    return {
      count: 10
    }
  }
}
</script>

<style lang="less">
.workspace-header-body {
  .main-container {
    padding-bottom: 0;
  }
}
</style>

<style lang="less" scoped>
.workspace-header-body {
  background-color: #ecf5ff;
  width: 100%;
  border-bottom: 1px solid #EBEEF5;
}

.tab-container {
  display: flex;

  .tab-button {
    padding: 10px;
    cursor: pointer;
  }

  .tab-button.active {
    color: #409EFF;
    border-bottom: 2px solid #409EFF;
  }

  .tab-button:hover {
    color: #409EFF;
  }

  .tab-button:not(:first-child) {
    margin-left: 20px;
  }
}
</style>
