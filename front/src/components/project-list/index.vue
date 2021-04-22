<template>
  <div>
    <project-item v-for="(project, index) in projectList" :key="index" :project="project"></project-item>
    <el-pagination
      background
      hide-on-single-page
      layout="prev, pager, next"
      :current-page="page"
      :page-size="size"
      @current-change="onPageChange"
      :total="count">
    </el-pagination>
  </div>
</template>

<script>
import ProjectItem from './project-item'

export default {
  name: 'project-list',
  components: {
    ProjectItem
  },
  props: {
    projectList: {
      type: Array,
      default () {
        return []
      }
    },
    page: Number,
    size: Number,
    count: Number
  },
  methods: {
    onPageChange (page) {
      this.$emit('page-change', page)
    }
  }
}
</script>

<style scoped>

</style>
