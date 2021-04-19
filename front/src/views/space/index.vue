<template>
  <div class="space-body main-container">
    <el-row :gutter="5">
      <el-col :xs="24" :sm="10" :md="6">
        <div class="space-left">
          <img class="profile" src="https://picsum.photos/200/200" alt="">
          <h3 class="user-name">白风</h3>
          <p class="user-description">这个人很懒，什么也没写~</p>
        </div>
      </el-col>
      <el-col :xs="24" :sm="14" :md="18">
        <div class="space-right">
          <el-tabs v-model="activeName">
            <el-tab-pane label="仓库" name="workspace"></el-tab-pane>
            <el-tab-pane label="资料" name="info"></el-tab-pane>
            <el-tab-pane label="设置" name="setting"></el-tab-pane>
          </el-tabs>
          <div class="space-tab-content">
            <space-workspace v-show="activeName === 'workspace'"></space-workspace>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import SpaceWorkspace from './components/space-workspace'

export default {
  components: {
    SpaceWorkspace
  },
  data () {
    return {
      activeName: 'workspace'
    }
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
