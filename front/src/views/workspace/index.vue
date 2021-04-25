<template>
  <div class="workspace-body">
    <workspace-header :workspace="workspace" :creator="creator"></workspace-header>
    <workspace-content :workspace="workspace" :creator="creator"></workspace-content>
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
  },
  data () {
    return {
      creator: {
        name: null,
        id: null,
        description: null
      },
      workspace: {
        id: null,
        name: null,
        description: null,
        creator: null,
        createDate: null
      }
    }
  },
  computed: {
    workspaceId () {
      return parseInt(this.$route.params.workspaceId, 10)
    }
  },
  methods: {
    query () {
      this.queryWorkspace(this.workspaceId)
        .then(() => this.queryCreator(this.workspace.creator))
    },
    queryWorkspace (id) {
      const params = {
        id
      }
      console.log(params)
      return this.$api.workspace.info(params).then(rep => {
        this.patchWorkspace(rep.data)
      }).catch(({ msg }) => this.$message.error(msg))
    },
    queryCreator (id) {
      const params = {
        id
      }
      this.$api.user.query(params).then(rep => {
        this.patchCreator(rep.data)
      }).catch(({ msg }) => this.$message.error(msg))
    },
    patchWorkspace (info = {}) {
      const {
        id,
        name,
        description,
        creator,
        createDate
      } = info
      this.workspace.id = id
      this.workspace.name = name
      this.workspace.description = description
      this.workspace.creator = creator
      this.workspace.createDate = createDate
    },
    patchCreator (info = {}) {
      const {
        id,
        name,
        description
      } = info
      this.creator.id = id
      this.creator.name = name
      this.creator.description = description
    }
  },
  created () {
    this.query()
  }
}
</script>

<style scoped>
.workspace-body {
}
</style>
