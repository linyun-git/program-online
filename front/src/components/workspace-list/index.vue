<template>
  <div>
    <workspace-item v-for="workspace of workspaces" :key="workspace"></workspace-item>
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
    workspaces: {
      type: Array,
      default () {
        return [1, 2, 3, 4, 5, 6]
      }
    }
  }
}
</script>

<style scoped>

</style>
