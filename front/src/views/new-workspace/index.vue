<template>
  <div class="main-container new-workspace-body">
    <div class="center-container">
      <h2 class="title">新建仓库</h2>
      <el-form label-position="top" :model="info" ref="form">
        <el-form-item label="名称" prop="name"
                      :rules="[{ required: true, message: '仓库名称不能为空', trigger: ['blur', 'change'] }]">
          <el-input v-model="info.name"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="description"
                      :rules="[{ required: true, message: '仓库简介不能为空', trigger: ['blur', 'change'] }]">
          <el-input type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}" v-model="info.description"></el-input>
        </el-form-item>
        <div>
          <p class="label required-label">环境</p>
          <environment-form v-for="(environment, index) in info.environments" :key="environment.key"
                            :hide-delete="index === 0" :ref="addRef"
                            v-model:enviroment="info.environments[index]" @delete="onDelete(index)"></environment-form>
          <el-button type="text" @click="onAddEnvironment">添加环境</el-button>
        </div>
        <el-form-item label="权限控制" prop="authorityType"
                      :rules="[{ required: true, message: '仓库权限不能为空', trigger: ['blur', 'change'] }]">
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
import EnvironmentForm from './components/environment-form'

export default {
  name: 'new-workspace',
  components: {
    EnvironmentForm
  },
  data () {
    return {
      info: {
        name: '',
        description: '',
        authorityType: 'private',
        environments: [
          {
            name: 'java',
            version: '11',
            key: new Date().getTime()
          }
        ]
      },
      formRefs: []
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
        environments
      } = this.info
      this.create({
        name,
        description,
        authorityType,
        environments
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
      this.formRefs.forEach(form => {
        if (!form || !form.checkValid) {
          return
        }
        if (!form.checkValid()) {
          result = false
        }
      })
      return result
    },
    create (info) {
      this.$api.workspace.add(info)
        .then(rep => {
          this.$message.success('创建成功')
          const id = rep.data.id
          this.$router.push(`/workspace/${id}`)
        })
        .catch(error => {
          this.$message.error(error.msg)
        })
    },
    onAddEnvironment () {
      this.info.environments.push({
        name: null,
        version: null,
        key: new Date().getTime()
      })
    },
    onDelete (index) {
      this.info.environments.splice(index, 1)
    },
    addRef (ref) {
      this.formRefs.push(ref)
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
.new-workspace-body {
  .center-container {
    > .el-form {
      > .el-form-item {
        margin-bottom: 0;
        transition: margin-bottom .3s;
      }

      > .el-form-item.is-error {
        margin-bottom: 22px;
      }

      > .el-form-item:nth-last-child(2) {
        margin-bottom: 22px;
      }
    }
  }
}

button.el-button--text {
  padding-top: 0;
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

.label {
  font-size: 14px;
  margin: 0;
  padding: 0 0 10px 0;
  height: 50px;
  text-align: left;
  line-height: 40px;
  display: inline-block;
  box-sizing: border-box;
}

.required-label.label::before {
  content: '*';
  margin-right: 4px;
  color: #F56C6C;
}
</style>
