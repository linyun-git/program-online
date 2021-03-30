<template>
  <el-card class="box-card login-body">
    <el-tabs v-model="activeName">
      <el-tab-pane label="登录" name="login" :lazy="true">
        <login-form v-if="isLogin" @submit="submit"></login-form>
      </el-tab-pane>
      <el-tab-pane label="注册" name="register" :lazy="true">
        <register-form v-if="isRegister" @submit="submit"></register-form>
      </el-tab-pane>
    </el-tabs>
  </el-card>
</template>

<script>
import LoginForm from '../components/login-form'
import RegisterForm from '../components/register-form'

export default {
  name: 'login',
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
    submit (value) {
      console.log(value)
    }
  }
}
</script>

<style lang="less" scoped>
.box-card {
  width: 480px;
  margin: 10% auto 0;
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
