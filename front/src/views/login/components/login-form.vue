<template>
  <el-form class="login-form-body" label-width="80px" :model="form" label-position="top" ref="form">
    <el-form-item label="邮箱" prop="email" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]">
      <el-input v-model="form.email"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password"
                  :rules="[{ required: true, message: '请输入密码', trigger: ['blur', 'change'] }]">
      <el-input v-model="form.password" type="password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: 'login-form',
  data () {
    return {
      form: {
        email: '', // 邮箱
        password: '' // 密码
      }
    }
  },
  methods: {
    submit () {
      if (!this.validate()) {
        return
      }
      const {
        email,
        password
      } = this.form
      this.$emit('submit', {
        email,
        password
      })
    },
    validate () {
      let result = true
      this.formRef.validate(valid => {
        if (!valid) {
          result = false
          return false
        }
      })
      return result
    }
  },
  computed: {
    formRef () {
      return this.$refs.form
    }
  }
}
</script>

<style lang="less">
.login-form-body.el-form {
  .el-form-item__label {
    padding-bottom: 0;
  }

  .el-form-item {
    margin-bottom: 0;
  }

  .el-form-item.is-error {
    margin-bottom: 22px;
  }

  .el-form-item:nth-last-child(2) {
    margin-bottom: 22px;
  }

  button {
    width: 100%;
  }
}
</style>
