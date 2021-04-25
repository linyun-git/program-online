<template>
  <el-form :inline="true" :model="form" ref="form" class="environment-form-body">
    <el-form-item label="环境名" prop="name"
                  :rules="[{ required: true, message: '环境名不能为空', trigger: ['blur', 'change'] }]">
      <el-select v-model="form.name">
        <el-option v-for="selectName in names" :key="selectName" :label="selectName" :value="selectName"></el-option>
      </el-select>
    </el-form-item>
    <el-form-item label="版本" prop="version"
                  :rules="[{ required: true, message: '环境版本不能为空', trigger: ['blur', 'change'] }]">
      <el-select v-model="form.version">
        <el-option v-for="selectVersion in versions" :key="selectVersion" :label="selectVersion"
                   :value="selectVersion"></el-option>
      </el-select>
    </el-form-item>
    <el-button v-if="!hideDelete" type="danger" @click="onDelete">删除</el-button>
  </el-form>
</template>

<script>
import { environments } from '@/constants/workspace'

export default {
  name: 'environment-form',
  props: {
    environment: Object,
    hideDelete: Boolean
  },
  data () {
    return {
      form: {
        name: null,
        version: null
      }
    }
  },
  computed: {
    name () {
      return this.form.name
    },
    version () {
      return this.form.version
    },
    names () {
      return environments.map(item => item.name)
    },
    versions () {
      const environment = environments.find(item => item.name === this.name)
      if (!environment) {
        return []
      }
      return environment.version
    }
  },
  methods: {
    checkValid () {
      let result = true
      if (!this.$refs.form) {
        return true
      }
      this.$refs.form.validate(valid => {
        if (!valid) {
          result = false
          return false
        }
      })
      return result
    },
    onDelete () {
      this.$emit('delete')
    }
  },
  watch: {
    form: {
      deep: true,
      callback (value) {
        this.$emit('update:environment', value)
      }
    },
    name () {
      this.form.version = null
    }
  }
}
</script>

<style lang="less">
.environment-form-body.el-form {
  .el-form-item {
    margin-bottom: 0;
    transition: margin-bottom .3s;
  }

  .el-form-item.is-error {
    margin-bottom: 22px;
  }

  label.el-form-item__label {
    margin-right: 8px;
  }
}

</style>
