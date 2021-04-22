<template>
  <div class="search-body">
    <search-header></search-header>
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
