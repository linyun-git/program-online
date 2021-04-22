<template>
  <el-card class="box-card login-body">
    <el-tabs v-model="activeName">
      <el-tab-pane label="登录" name="login" :lazy="true">
        <login-form v-if="isLogin" @submit="login"></login-form>
      </el-tab-pane>
      <el-tab-pane label="注册" name="register" :lazy="true">
        <register-form v-if="isRegister" @submit="register"></register-form>
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script>
import LoginForm from './components/login-form'
import RegisterForm from './components/register-form'

export default {
  components: {
    LoginForm,
    RegisterForm
  },
  data () {
    return {
      activeName: 'login'
    }
  },
  computed: {
    isLogin () {
      return this.activeName === 'login'
    },
    isRegister () {
      return this.activeName === 'register'
    }
  },
  methods: {
    login (value) {
      this.$api.user.login(value)
        .then(rep => {
          const user = rep.data
          this.$message.success(`登录成功: ${user.name}`)
          this.$store.commit('user/Add_USER_INFO', user)
          this.$router.push('/')
        })
        .catch(error => {
          this.$message.error('登录失败：' + error)
        })
    },
    register (value) {
      this.$api.user.register(value)
        .then(rep => {
          const user = rep.data
          this.$message.success(`注册成功: ${user.name}`)
          this.$store.commit('user/Add_USER_INFO', user)
          this.$router.push('/')
        })
        .catch(error => {
          this.$message.error(error.msg)
        })
    }
  }
}
</script>

<style lang="less" scoped>
.box-card {
  width: 480px;
  position: fixed;
  top: 20%;
  left: 50%;
  transform: translate(-50%, 0);
}
</style>

<style lang="less">
.login-body {
  .el-tabs__nav {
    width: 100%;
  }

  .el-tabs__item {
    width: 50%;
    text-align: center;
    padding: 0;
  }
}
</style>
