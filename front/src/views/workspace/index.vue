<template>
  <div class="workspace-body">
    <workspace-header></workspace-header>
    <workspace-content></workspace-content>
  </div>
</template>

<script>
import WorkspaceHeader from './components/workspace-header'
import WorkspaceContent from './components/workspace-content'

export default {
  name: 'index',
  components: {
    WorkspaceHeader,
    WorkspaceContent
  }
}
</script>

<style scoped>
.workspace-body {
}
</style>
