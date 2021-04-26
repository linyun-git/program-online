<template>
  <div class="project-body">
    <project-header :project="project" :creator="creator"></project-header>
    <project-content :project="project"></project-content>
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
  },
  data () {
    return {
      project: {},
      creator: {}
    }
  },
  computed: {
    projectId () {
      return parseInt(this.$route.params.projectId, 10)
    }
  },
  methods: {
    queryProject (projectId) {
      const params = {
        projectId
      }
      return this.$api.project.info(params).then(rep => {
        this.project = rep.data
      }).catch(({ msg }) => this.$message.error(msg))
    },
    query () {
      this.queryProject(this.projectId)
        .then(() => this.queryCreator(this.project.creator))
    },
    queryCreator (id) {
      const params = {
        id
      }
      this.$api.user.query(params).then(rep => {
        this.creator = rep.data
      }).catch(({ msg }) => this.$message.error(msg))
    }
  },
  created () {
    this.query()
  }
}
</script>

<style scoped>

</style>
