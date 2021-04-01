<template>
  <div class="main-container search-content-body">
    <div class="search-content-header content-item">
      共999个查询结果
    </div>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
    <workspace-item></workspace-item>
  </div>
</template>

<script>
import WorkspaceItem from './workspace-item'

export default {
  name: 'search-content',
  components: {
    WorkspaceItem
  }
}
</script>

<style lang="less">
.search-content-body {
  .content-item {
    padding: 20px 0 10px;
    border-bottom: 1px solid #EBEEF5;

    > * {
      margin-top: 0;
      padding: 0;
    }

    > *:not(:last-child) {
      margin-bottom: 10px;
    }

    .content-title {
      font-size: 16px;
    }

    .content-description {
      font-size: 14px;
    }

    .content-tag-container {
      > *:not(:last-child) {
        margin-right: 5px;
      }
    }

    .content-unimportant {
      font-size: 13px;
      color: #909399;
    }
  }

  .search-content-header {
    padding-top: 0;
    font-size: 13px;
    color: #909399;
  }
}
</style>

<style scoped>

</style>
