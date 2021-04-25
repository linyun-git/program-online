<template>
  <div>
    <workspace-item v-for="workspace of workspaceList" :key="workspace.id" :workspace="workspace"></workspace-item>
  </div>
</template>

<script>
import WorkspaceItem from './workspace-item'

export default {
  name: 'workspace-list',
  components: {
    WorkspaceItem
  },
  props: {
    workspaceList: Array
  }
}
</script>

<style scoped>

</style>
