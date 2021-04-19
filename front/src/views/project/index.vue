<template>
  <div class="project-body">
    <project-header></project-header>
    <project-content></project-content>
  </div>
</template>

<script>
import ProjectHeader from './components/project-header'
import ProjectContent from './components/project-content'

export default {
  name: 'index',
  components: {
    ProjectHeader,
    ProjectContent
  }
}
</script>

<style scoped>

</style>
