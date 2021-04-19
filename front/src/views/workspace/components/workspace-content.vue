<template>
  <div class="main-container">
    <workspace-project v-show="type === 'project'"></workspace-project>
    <workspace-manage v-show="type === 'manage'"></workspace-manage>
  </div>
</template>

<script>
import WorkspaceProject from './workspace-project'
import WorkspaceManage from './workspace-manage'

export default {
  name: 'workspace-content',
  components: {
    WorkspaceProject,
    WorkspaceManage
  },
  data () {
    return {}
  },
  computed: {
    type () {
      return this.$route.query.type
    }
  }
}
</script>

<style scoped>
.main-container {
  padding-top: 0;
}
</style>
