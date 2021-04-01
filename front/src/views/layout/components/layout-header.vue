<template>
  <div class="header-content-body full-height">
    <div class="space-container">
      <logo @click="onLogoClick"></logo>
    </div>
    <div class="space-container">
      <el-button type="text"><i class="el-icon-bell"></i></el-button>
      <el-input
        placeholder="站内搜索"
        prefix-icon="el-icon-search"
        v-model="searchValue">
      </el-input>
      <el-dropdown>
        <el-button type="text"><i class="el-icon-plus"></i></el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>新建仓库</el-dropdown-item>
            <el-dropdown-item>代码测试</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <el-dropdown>
        <el-button type="text">我的</el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>个人主页</el-dropdown-item>
            <el-dropdown-item>我的仓库</el-dropdown-item>
            <el-dropdown-item>退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <el-button type="text">登录/注册</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'header',
  data () {
    return {
      searchValue: ''
    }
  },
  methods: {
    onLogoClick () {
      console.log('click')
    }
  }
}
</script>

<style lang="less">
.header-content-body {
  .el-button {
    font-size: 16px;
  }

  .el-button + .el-button {
    margin-left: 0;
  }
  .el-input input {
    font-size: 14px;
    height: 2em;
    width: 10em;
    transition: width .3s ease-out;
  }
  .el-input input:focus {
    width: 14em;
  }
}
</style>

<style lang="less" scoped>
.header-content-body {
  display: flex;
  justify-content: space-between;
}

.full-height {
  height: 100%;
}

a {
  cursor: pointer;
}
</style>
