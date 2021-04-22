<template>
  <div class="search-header-body">
    <div class="main-container">
      <el-input
        class="search-input"
        placeholder="搜索内容"
        v-model="searchValue"
        @keydown.enter="onSearch"
      >
        <template #append>
          <el-button type="primary" @click="onSearch">搜索</el-button>
        </template>
      </el-input>
      <div class="tab-container">
        <a class="tab-button" v-for="button of tabButtons"
           :class="{active: queryType===button.key}"
           @click="onTypeClick(button.key)" :key="button.key"><i :class="button.icon"></i>{{button.title}}</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'search-header',
  data () {
    return {
      // 搜索框的输入值
      searchValue: '',
      tabButtons: [
        {
          key: 'workspace',
          title: '仓库',
          icon: 'el-icon-house'
        },
        {
          key: 'project',
          title: '项目',
          icon: 'el-icon-files'
        },
        {
          key: 'user',
          title: '用户',
          icon: 'el-icon-user'
        }
      ]
    }
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
    },
    queryValue () {
      return this.$route.query.q || ''
    },
    queryType () {
      return this.$route.query.type || 'project'
    }
  },
  methods: {
    onSearch () {
      const queryParams = {
        queryValue: this.searchValue,
        queryType: this.queryType
      }
      this.query(queryParams)
    },
    onTypeClick (type = 'project') {
      const queryParams = {
        queryValue: this.searchValue,
        queryType: type
      }
      this.query(queryParams)
    },
    query (queryParams = {}) {
      this.$router.push(`/search?q=${queryParams.queryValue}&type=${queryParams.queryType}&page=1`)
    }
  },
  created () {
    this.searchValue = this.queryValue
  }
}
</script>

<style lang="less">
.search-header-body {
  .main-container {
    padding-bottom: 0;
  }
}
</style>

<style lang="less" scoped>
.search-header-body {
  background-color: #ecf5ff;
  width: 100%;
  border-bottom: 1px solid #EBEEF5;
}
.tab-container {
  margin-top: 10px;
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
