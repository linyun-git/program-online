<template>
  <div class="main-container new-project-body">
    <div class="center-container">
      <h2 class="title">新建项目</h2>
      <el-form label-position="top" :model="info" ref="form">
        <el-form-item label="名称" prop="name"
                      :rules="[{ required: true, message: '项目名称不能为空', trigger: ['blur', 'change'] }]">
          <el-input v-model="info.name"></el-input>
        </el-form-item>
        <el-form-item label="所属仓库" prop="workspaceId"
                      :rules="[{ required: true, message: '所属仓库不能为空', trigger: ['blur', 'change'] }]">
          <el-select v-model="info.workspaceId">
            <el-option
              v-for="item in workspaceList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>
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
        authorityType: 'private',
        workspaceId: null
        // directoryCode: '',
        // workspaceId: null
      },
      workspaceList: []
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
        authorityType,
        workspaceId
      } = this.info
      this.create({
        name,
        description,
        authorityType,
        workspaceId
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
    },
    queryWorkspace () {
      const params = {
        uid: this.$store.getters['user/userInfo'].id
      }
      this.$api.workspace.listByUid(params)
        .then(rep => {
          this.workspaceList = rep.data
        })
        .catch(({ msg }) => this.$message.error(msg))
    }
  },
  computed: {
    formRef () {
      return this.$refs.form
    },
    workspaceId () {
      return parseInt(this.$route.query.workspace, 10)
    }
  },
  created () {
    this.info.workspaceId = this.workspaceId
    this.queryWorkspace()
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
