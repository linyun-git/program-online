<template>
  <div class="project-manage-body">
    <h3>基本信息</h3>
    <el-card class="box-card">
      <el-form :model="info" status-icon :rules="rules" ref="form" label-width="100px">
        <el-form-item label="项目名称" prop="name">
          <el-input v-model="info.name"></el-input>
        </el-form-item>
        <el-form-item label="项目介绍" prop="description">
          <el-input type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}" v-model="info.description"></el-input>
        </el-form-item>
        <el-form-item label="项目权限" prop="authorityType">
          <el-radio-group v-model="info.authorityType">
            <el-radio label="private">私有</el-radio>
            <el-radio label="public">开放</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <h3>管理</h3>
    <el-card>
      <el-form label-width="100px">
        <el-form-item>
          <el-button type="primary" @click="onCodeEdit">代码编辑</el-button>
          <el-button type="danger">删除项目</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'project-manage',
  data () {
    return {
      info: {
        id: null,
        name: null,
        description: null,
        environments: [],
        authorityType: null,
        portRoutes: [
          {
            port: null,
            path: null
          }
        ]
      },
      rules: {
        name: [
          {
            required: true,
            message: '项目名不能为空',
            trigger: ['blur', 'change']
          }
        ],
        description: [
          {
            required: true,
            message: '项目介绍不能为空',
            trigger: ['blur', 'change']
          }
        ],
        authorityType: [
          {
            required: true,
            message: '项目权限不能为空',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  computed: {
    projectId () {
      return parseInt(this.$route.params.projectId, 10)
    }
  },
  methods: {
    onCodeEdit () {
      window.open(`/code/${this.projectId}`)
    }
  }
}
</script>

<style scoped>
.project-manage-body {
  /*padding-top: 20px;*/
}
</style>
