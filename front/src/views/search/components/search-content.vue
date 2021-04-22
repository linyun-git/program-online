<template>
  <div class="main-container search-content-body">
    <div class="search-content-header content-item">
      <span class="sort-container">默认排序</span>
      <span>共{{ count }}个查询结果</span>
    </div>
    <div v-if="queryType === 'workspace'">
      <workspace-list :workspace-list="list" :page="pageIndex" :size="size" :count="count" @page-change="onPageChange"></workspace-list>
    </div>
    <div v-if="queryType === 'project'">
      <project-list :project-list="list" :page="pageIndex" :size="size" :count="count" @page-change="onPageChange"></project-list>
    </div>
    <div v-if="queryType === 'user'">
      <user-list :user-list="list" :page="pageIndex" :size="size" :count="count" @page-change="onPageChange"></user-list>
    </div>
  </div>
</template>

<script>

export default {
  name: 'search-content',
  data () {
    return {
      list: [],
      count: 0,
      size: 10
    }
  },
  created () {
    this.query()
  },
  computed: {
    queryValue () {
      return this.$route.query.q || ''
    },
    queryType () {
      return this.$route.query.type || 'project'
    },
    pageIndex () {
      return parseInt(this.$route.query.page, 10) || 1
    },
    queryParams () {
      return {
        query: this.queryValue,
        pageIndex: this.pageIndex,
        pageSize: this.size
      }
    }
  },
  methods: {
    query () {
      switch (this.queryType) {
        case 'workspace':
          this.queryWorkspace()
          break
        case 'project':
          this.queryProject()
          break
        case 'user':
          this.queryUser()
          break
      }
    },
    queryWorkspace () {
      this.$api.workspace.list(this.queryParams).then(rep => {
        const {
          count,
          list
        } = rep.data
        this.list = list
        this.count = count
      })
    },
    queryProject () {
      this.$api.project.list(this.queryParams).then(rep => {
        const {
          count,
          list
        } = rep.data
        this.list = list
        this.count = count
      })
    },
    queryUser () {
      this.$api.user.list(this.queryParams).then(rep => {
        const {
          count,
          list
        } = rep.data
        this.list = list
        this.count = count
      })
    },
    onPageChange (page) {
      this.pageIndex = page
      this.$router.push(`/search?q=${this.queryValue}&type=${this.queryType}&page=${page}`)
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
