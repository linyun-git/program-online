<template>
  <div class="header-content-body full-height">
    <div class="space-container">
      <logo @click="onLogoClick"></logo>
    </div>
    <div class="space-container">
      <el-input
        placeholder="站内搜索"
        prefix-icon="el-icon-search"
        @keydown.enter="onSearch"
        v-model="searchValue">
      </el-input>
      <el-dropdown v-if="login">
        <el-button type="text"><i class="el-icon-plus"></i></el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="onCreateWorkspace">新建仓库</el-dropdown-item>
            <el-dropdown-item>代码测试</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <!-- 通知 -->
      <el-button v-if="login" type="text"><i class="el-icon-bell"></i></el-button>
      <el-dropdown v-if="login">
        <el-button type="text">我的</el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="onMySpace">个人主页</el-dropdown-item>
<!--            <el-dropdown-item @click="onMyWorkspace">我的仓库</el-dropdown-item>-->
            <el-dropdown-item @click="logout">退出</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
      <el-button v-if="!login" type="text" @click="onLoginClick">登录/注册</el-button>
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
  computed: {
    login () {
      const id = this.$store.getters['user/userInfo'].id
      return id !== null
    }
  },
  methods: {
    onLogoClick () {
      this.$router.push('/')
    },
    onSearch () {
      this.$router.push(`/search?q=${this.searchValue}`)
      this.searchValue = ''
    },
    onLoginClick () {
      this.$router.push('/login')
    },
    onMyWorkspace () {
      this.$router.push('/workspace')
    },
    onMySpace () {
      this.$router.push(`/space/${this.$store.getters['user/userInfo'].id}`)
    },
    logout () {
      this.$api.user.logout().then(() => {
        this.$store.dispatch('user/logout')
      }).catch(err => {
        this.$message.error(err)
      })
    },
    onCreateWorkspace () {
      this.$router.push('/new-workspace')
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
