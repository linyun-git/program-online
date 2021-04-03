<template>
  <div class="search-body">
    <search-header :query-value="q" :query-type="type" @search="query"></search-header>
    <search-content :query-type="type"></search-content>
  </div>
</template>

<script>
import SearchHeader from './components/search-header'
import SearchContent from './components/search-content'

export default {
  components: {
    SearchHeader,
    SearchContent
  },
  data () {
    return {
      page: 1,
      type: 'project',
      q: ' '
    }
  },
  computed: {
    isWorkspace () {
      return this.type === 'workspace'
    },
    isProject () {
      return this.type === 'project'
    },
    isUser () {
      return this.type === 'user'
    }
  },
  created () {
    const {
      page,
      q,
      type
    } = this.$route.query
    this.page = parseInt(page, 10) || 1
    this.q = q || ' '
    this.type = type || 'project'
  },
  methods: {
    change () {
      this.$router.push('/search?p=456')
    },
    query (queryParams) {
      const {
        queryValue,
        queryType
      } = queryParams
      this.$router.push(`/search?q=${queryValue || ''}&type=${queryType || this.type}&page=${this.page}`)
    }
  },
  activated () {
    document.title = '查询'
  }
}
</script>
<style lang="less">
.search-body {
  .search-input {
    input {
      border-top-right-radius: 0;
      border-bottom-right-radius: 0;
    }

    .el-button {
      border-top-left-radius: 0;
      border-bottom-left-radius: 0;
      background-color: #409EFF;
      color: #FFF;
      border-color: #409EFF;
    }
  }
}
</style>

<style scoped>

</style>
