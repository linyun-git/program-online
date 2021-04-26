<template>
  <div class="main-container">
    <project-code :project="project" v-show="type === 'code'"></project-code>
    <project-manage v-show="type === 'manage'"></project-manage>
  </div>
</template>

<script>
import ProjectCode from './project-code'
import ProjectManage from './project-manage'

export default {
  name: 'project-content',
  components: {
    ProjectCode,
    ProjectManage
  },
  props: {
    project: Object
  },
  computed: {
    type () {
      return this.$route.query.type
    }
  }
}
</script>

<style scoped>

</style>
