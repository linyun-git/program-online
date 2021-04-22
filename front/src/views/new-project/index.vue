<template>
  <div class="main-container new-project-body">
    <div class="center-container">
      <h2 class="title">新建项目</h2>
      <el-form label-position="top" :model="info" ref="form">
        <el-form-item label="名称" prop="name"
                      :rules="[{ required: true, message: '项目名称不能为空', trigger: ['blur', 'change'] }]">
          <el-input v-model="info.name"></el-input>
        </el-form-item>
<!--        <el-form-item label="路径" prop="name"-->
<!--                      :rules="[{ required: true, message: '项目名称不能为空', trigger: ['blur', 'change'] }]">-->
<!--          <el-input-->
<!--            v-model="info.directoryCode"-->
<!--            class="input-with-select"-->
<!--          >-->
<!--            <template #prepend>-->
<!--              <el-form-item>-->
<!--                <el-select v-model="info.workspaceId">-->
<!--                  <el-option label="餐厅名" :value="1"></el-option>-->
<!--                  <el-option label="订单号" value="2"></el-option>-->
<!--                  <el-option label="用户电话" value="3"></el-option>-->
<!--                </el-select>-->
<!--              </el-form-item>-->
<!--            </template>-->
<!--          </el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="简介" prop="description"
                      :rules="[{ required: true, message: '项目简介不能为空', trigger: ['blur', 'change'] }]">
          <el-input type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}" v-model="info.description"></el-input>
        </el-form-item>
        <el-form-item label="权限控制" prop="authorityType"
                      :rules="[{ required: true, message: '项目权限不能为空', trigger: ['blur', 'change'] }]">
          <el-radio-group v-model="info.authorityType">
            <el-radio label="private">私有</el-radio>
            <el-radio label="public">开放</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-button type="primary" @click="submit">创建</el-button>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: 'new-project',
  data () {
    return {
      info: {
        name: '',
        description: '',
        authorityType: 'private'
        // directoryCode: '',
        // workspaceId: null
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
        description,
        authorityType
      } = this.info
      this.create({
        name,
        description,
        authorityType
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
    },
    create (info) {
      this.$api.project.add(info)
        .then(rep => {
          this.$message.success('创建成功')
          const id = rep.data.id
          this.$router.push(`/project/${id}`)
        })
        .catch(error => {
          this.$message.error(error.msg)
        })
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
.new-project-body {
  .el-form {
    .el-form-item {
      margin-bottom: 0;
      transition: margin-bottom .3s;
    }

    .el-form-item.is-error {
      margin-bottom: 22px;
    }

    .el-form-item:nth-last-child(2) {
      margin-bottom: 22px;
    }
  }
  .input-with-select .el-input-group__prepend {
    background-color: #fff;
    height: 40px;
  }
  .el-select .el-input {
    width: 130px;
  }
}
</style>

<style lang="less" scoped>
.center-container {
  max-width: 800px;
  margin: 0 auto;
}

.title {
  color: rgba(0, 0, 0, 0.8);
}
</style>
