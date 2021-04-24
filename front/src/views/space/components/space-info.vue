<template>
  <div>
    <el-form :model="form" status-icon :rules="rules" ref="form" label-width="100px" class="demo-ruleForm">
      <el-form-item label="头像" prop="profile">
        <el-upload
          class="avatar-uploader"
          action="https://jsonplaceholder.typicode.com/posts/"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
        >
          <img v-if="form.profile" :src="form.profile" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
      </el-form-item>
      <el-form-item label="昵称" prop="name">
        <el-input v-model="form.name"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="form.email" disabled></el-input>
      </el-form-item>
      <el-form-item label="个人简介" prop="description">
        <el-input v-model="form.description" type="textarea"
                  :autosize="{ minRows: 2, maxRows: 4}"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSave">保存</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: 'space-info',
  data () {
    return {
      form: {
        profile: 'https://picsum.photos/200/200',
        name: null,
        email: null,
        description: null
      },
      rules: {
        name: [
          {
            required: true,
            message: '昵称不能为空',
            trigger: ['blur', 'change']
          }
        ],
        email: [
          {
            required: true,
            message: '邮箱不能为空',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  methods: {
    patchValue (formValue) {
      if (!formValue) {
        return
      }
      for (const Key in this.form) {
        if (formValue[Key] !== undefined) {
          this.form[Key] = formValue[Key]
        }
      }
    },
    handleAvatarSuccess (res, file) {
      this.form.profile = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload (file) {
      // const isJPG = file.type === 'image/jpeg'
      // const isLt2M = file.size / 1024 / 1024 < 2
      //
      // // if (!isJPG) {
      // //   this.$message.error('上传头像图片只能是 JPG 格式!')
      // // }
      // // if (!isLt2M) {
      // //   this.$message.error('上传头像图片大小不能超过 2MB!')
      // // }
      // // return isJPG && isLt2M
      return true
    },
    onSave () {
      if (!this.checkValid()) {
        return
      }
      const {
        name,
        description
      } = this.form
      const params = {
        name,
        description
      }
      this.$api.user.update(params).then(() => {
        this.$message.success('保存成功')
        this.$router.go(0)
      }).catch(err => this.$message.error(err))
    },
    checkValid () {
      let result = true
      this.$refs.form.validate((valid) => {
        if (!valid) {
          result = false
          return false
        }
      })
      return result
    }
  },
  created () {
    const info = this.$store.getters['user/userInfo']
    this.patchValue(info)
  }
}
</script>

<style scoped>
img {
  height: 100px;
  width: 100px;
  border-radius: 50%;
}
</style>
