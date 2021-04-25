<template>
  <div class="space-body main-container">
    <el-row :gutter="5">
      <el-col :xs="24" :sm="10" :md="6">
        <div class="space-left">
          <img class="profile" :src="info.profile" alt="">
          <h3 class="user-name">{{ info.name }}</h3>
          <p class="user-description">{{ info.description }}</p>
        </div>
      </el-col>
      <el-col :xs="24" :sm="14" :md="18">
        <div class="space-right">
          <el-tabs v-model="activeName">
            <el-tab-pane label="仓库" name="workspace"></el-tab-pane>
            <el-tab-pane v-if="isSelf" label="资料" name="info"></el-tab-pane>
            <el-tab-pane v-if="isSelf" label="设置" name="setting"></el-tab-pane>
          </el-tabs>
          <div class="space-tab-content">
            <space-workspace v-show="activeName === 'workspace'"></space-workspace>
            <space-info v-show="activeName === 'info'"></space-info>
            <space-setting v-show="activeName === 'setting'"></space-setting>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import SpaceWorkspace from './components/space-workspace'
import SpaceInfo from './components/space-info'
import SpaceSetting from './components/space-setting'

export default {
  components: {
    SpaceWorkspace,
    SpaceInfo,
    SpaceSetting
  },
  data () {
    return {
      activeName: 'workspace',
      info: {
        name: null,
        profile: null,
        description: null
      }
    }
  },
  computed: {
    uid () {
      return parseInt(this.$route.params.uid, 10)
    },
    isSelf () {
      return this.$store.getters['user/userInfo'].id === this.uid
    }
  },
  methods: {
    query () {
      if (this.isSelf) {
        this.patchValue(this.$store.getters['user/userInfo'])
        return
      }
      this.$api.user.query({ id: this.uid })
        .then(({ data }) => this.patchValue(data))
        .catch(({ msg }) => this.$message.error(msg))
    },
    patchValue (info) {
      if (!info) {
        this.patchValue({})
      }
      this.info.name = info.name
      this.info.profile = info.profile
      this.info.description = info.description
    }
  },
  created () {
    this.query()
  }
}
</script>

<style lang="less">
.space-body {
  .el-tabs__content {
    display: none;
  }

  .el-tabs__header {
    border-bottom: none;
    margin-bottom: 0;
  }

  .el-tabs__item {
    min-width: 100px;
    text-align: center;
    font-size: 15px;
    padding: 0;
  }
}
</style>

<style lang="less" scoped>
.space-left {
  width: 100%;
  display: flex;
  flex-direction: column;
  align-items: center;

  > * {
    margin: 0;
  }

  > *:not(:last-child) {
    margin-bottom: 10px;
  }

  .profile {
    height: 160px;
    width: 160px;
    border-radius: 50%;
  }

  .user-name {
  }

  .user-description {
    color: #8c92a4;
  }
}

.space-right {
  .space-tab-content {
    margin-top: 10px;
  }
}

</style>
