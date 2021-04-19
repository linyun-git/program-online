<template>
  <div>
    <project-item v-for="(project, index) in projectList" :key="index" :project="project"></project-item>
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
      default: () => []
    }
  }
}
</script>

<style scoped>

</style>
