<template>
  <div class="search-body">
    <search-header :query-value="q" :query-type="type" @search="query"></search-header>
    <search-content></search-content>
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
      type: 'workspace',
      q: ' '
    }
  },
  computed: {
    isWorkspace () {
      return this.type === 'workspace'
    },
    isProject () {
      return this.type === 'project'
    }
  },
  created () {
    console.log(1)
    this.init()
    this.$watch(() => this.$route.query, () => {
      this.init()
    })
  },
  methods: {
    init () {
      const {
        page,
        q,
        type
      } = this.$route.query
      this.page = parseInt(page, 10) || 1
      this.q = q || ' '
      this.type = type || 'workspace'
    },
    change () {
      this.$router.push('/search?p=456')
    },
    query () {
      console.log('ok')
    }
  },
  activated () {
    document.title = '查询'
  }
}
</script>
<style lang="less">
.search-body{
  .search-input {
    input{
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
