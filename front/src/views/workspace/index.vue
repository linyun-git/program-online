<template>
  <div class="workspace-body">
    <workspace-header></workspace-header>
  </div>
</template>

<script>
import WorkspaceHeader from './components/workspace-header'

export default {
  name: 'index',
  components: {
    WorkspaceHeader
  }
}
</script>

<style scoped>
.workspace-body {
}
</style>
