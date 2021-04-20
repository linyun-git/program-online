<template>
  <div class="main-container search-content-body">
    <div class="search-content-header content-item">
      <span class="sort-container">默认排序</span>
      <span>共999个查询结果</span>
    </div>
    <div v-if="isWorkspace">
      <workspace-list></workspace-list>
    </div>
    <div v-if="isProject">
      <project-list></project-list>
    </div>
    <div v-if="isUser">
      <user-item></user-item>
      <user-item></user-item>
      <user-item></user-item>
      <user-item></user-item>
      <user-item></user-item>
      <user-item></user-item>
      <user-item></user-item>
    </div>
  </div>
</template>

<script>
import UserItem from './user-item'

export default {
  name: 'search-content',
  components: {
    UserItem
  },
  props: {
    queryType: String
  },
  computed: {
    isWorkspace () {
      return this.queryType === 'workspace'
    },
    isProject () {
      return this.queryType === 'project'
    },
    isUser () {
      return this.queryType === 'user'
    }
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
    display: flex;
    justify-content: space-between;

    span.sort-container {
      margin-bottom: 0;
    }
  }
}
</style>

<style scoped>

</style>
