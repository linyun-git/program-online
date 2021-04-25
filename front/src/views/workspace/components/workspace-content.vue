<template>
  <div class="main-container">
    <workspace-project v-show="type === 'project'"></workspace-project>
    <workspace-manage v-show="type === 'manage' && isSelf" :workspace="workspace"></workspace-manage>
  </div>
</template>

<script>
import WorkspaceProject from './workspace-project'
import WorkspaceManage from './workspace-manage'

export default {
  name: 'workspace-content',
  props: {
    workspace: Object,
    creator: Object
  },
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
    },
    isSelf () {
      return this.creator.id === this.$store.getters['user/userInfo'].id
    }
  }
}
</script>

<style scoped>
.main-container {
  padding-top: 0;
}
</style>
