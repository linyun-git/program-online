<template>
  <div class="workspace-manage-body">
    <h3>基本信息</h3>
    <el-card class="box-card">
      <el-form :model="info" status-icon :rules="rules" ref="form" label-width="100px">
        <el-form-item label="仓库名" prop="name">
          <el-input v-model="info.name"></el-input>
        </el-form-item>
        <el-form-item label="仓库介绍" prop="description">
          <el-input type="textarea"
                    :autosize="{ minRows: 2, maxRows: 4}" v-model="info.description"></el-input>
        </el-form-item>
        <el-form-item label="仓库权限" prop="authorityType">
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
    <h3>端口映射</h3>
    <el-card class="box-card">
      <el-form :model="info" status-icon :rules="rules" ref="form" label-width="100px">
        <el-form-item class="port-form-item"
                      v-for="(portRoute, index) in info.portRoutes"
                      :label="'端口映射' + index"
                      :key="portRoute.key"
                      :prop="'portRoutes.' + index + '.port'"
                      :rules="{
      required: true, message: '端口不能为空', trigger: 'blur'
    }"
        >
          <el-input v-model.number="portRoute.port"></el-input>
          <el-button @click.prevent="removePortRoute(portRoute)">删除</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click.prevent="addPortRoute">新增端口</el-button>
          <el-button type="primary">保存</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <h3>管理</h3>
    <el-card>
      <el-form label-width="100px">
        <el-form-item>
          <el-button type="primary" @click="onAddProject">新增项目</el-button>
          <el-button type="danger">删除仓库</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'workspace-manage',
  props: {
    workspace: Object
  },
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
            message: '仓库名不能为空',
            trigger: ['blur', 'change']
          }
        ],
        description: [
          {
            required: true,
            message: '仓库介绍不能为空',
            trigger: ['blur', 'change']
          }
        ],
        authorityType: [
          {
            required: true,
            message: '仓库权限不能为空',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  methods: {
    removePortRoute (item) {
      const index = this.info.portRoutes.indexOf(item)
      if (index !== -1) {
        this.info.portRoutes.splice(index, 1)
      }
    },
    addPortRoute () {
      this.info.portRoutes.push({
        port: null
      })
    },
    onAddProject () {
      this.$router.push(`/new-project?workspace=${this.workspace.id}`)
    }
  }
}
</script>

<style scoped>
.workspace-manage-body {
  padding-top: 20px;
}
</style>

<style lang="less">
.workspace-manage-body {
  .el-button.block-button {
    width: 100%;
  }

  .port-form-item .el-input {
    width: calc(100% - 80px);
    //max-width: 270px;
    margin-right: 10px;
  }
}
</style>
