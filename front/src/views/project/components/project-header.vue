<template>
  <div class="workspace-header-body">
    <div class="main-container">
      <h3 style="margin: 0">白风 / Project Name</h3>
      <div class="tab-container">
        <a class="tab-button" :class="{active: type === 'code'}" @click="typeTo('code')">代码
          <el-tag>{{ count }}</el-tag>
        </a>
        <a class="tab-button" :class="{active: type === 'manage'}" @click="typeTo('manage')">管理</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'project-header',
  data () {
    return {
      count: 10
    }
  },
  computed: {
    type () {
      return this.$route.query.type
    },
    projectId () {
      return this.$route.params.projectId
    }
  },
  methods: {
    typeTo (type) {
      if (type === this.type) {
        return
      }
      this.$router.push(`/project/${this.projectId}?type=${type}`)
    },
    checkType () {
      if (['code', 'manage'].includes(this.type)) {
        return
      }
      this.$router.push(`/project/${this.projectId}?type=code`)
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
