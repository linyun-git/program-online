<template>
  <el-form class="register-form-body" label-width="80px" :model="form" label-position="top" ref="form">
    <el-form-item label="昵称" prop="name" :rules="[{ required: true, message: '请输入昵称', trigger: 'blur' }]">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
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
    <el-form-item label="确认密码" prop="confirmPassword"
                  :rules="rules.confirmPassword">
      <el-input v-model="form.confirmPassword" type="password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit">注册</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: 'register-form',
  data () {
    // 校验确认密码
    const validateConfirmPassword = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.form.password) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      form: {
        name: '', // 昵称
        email: '', // 邮箱
        password: '', // 密码
        confirmPassword: '' // 确认密码
      },
      // 校验规则
      rules: {
        confirmPassword: [
          {
            validator: validateConfirmPassword,
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  methods: {
    submit () {
      if (!this.validate()) {
        return
      }
      const {
        name,
        email,
        password
      } = this.form
      this.$emit('submit', {
        name,
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
.register-form-body.el-form {
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
