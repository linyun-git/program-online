<template>
  <div class="workspace-header-body">
    <div class="main-container">
      <h3 style="margin: 0">{{ creator.name }} / {{ workspace.name }}</h3>
      <div class="tab-container">
        <a class="tab-button" :class="{active: type === 'project'}" @click="typeTo('project')">项目
          <el-tag>{{ count }}</el-tag>
        </a>
        <a class="tab-button" v-if="isSelf" :class="{active: type === 'manage'}" @click="typeTo('manage')">管理</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'workspace-header',
  props: {
    workspace: Object,
    creator: Object
  },
  data () {
    return {
      count: 10
    }
  },
  computed: {
    type () {
      return this.$route.query.type
    },
    workspaceId () {
      return this.$route.params.workspaceId
    },
    isSelf () {
      return this.creator.id === this.$store.getters['user/userInfo'].id
    }
  },
  methods: {
    typeTo (type) {
      if (type === this.type) {
        return
      }
      this.$router.push(`/workspace/${this.workspaceId}?type=${type}`)
    },
    checkType () {
      if (['project', 'manage'].includes(this.type)) {
        return
      }
      this.$router.push(`/workspace/${this.workspaceId}?type=project`)
    }
  },
  activated () {
    this.checkType()
  }
}
</script>

<style lang="less">
.workspace-header-body {
  .main-container {
    padding-bottom: 0;
  }
}
</style>

<style lang="less" scoped>
.workspace-header-body {
  background-color: #ecf5ff;
  width: 100%;
  border-bottom: 1px solid #EBEEF5;
  height: 99px;
}

.tab-container {
  display: flex;

  .tab-button {
    padding: 10px;
    cursor: pointer;
  }

  .tab-button.active {
    color: #409EFF;
    border-bottom: 2px solid #409EFF;
  }

  .tab-button:hover {
    color: #409EFF;
  }

  .tab-button:not(:first-child) {
    margin-left: 20px;
  }
}
</style>
