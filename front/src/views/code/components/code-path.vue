<template>
  <el-breadcrumb class="code-path-body" separator-class="el-icon-arrow-right">
    <el-breadcrumb-item v-for="(path, index) in pathList" :key="index">{{ path }}</el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
export default {
  name: 'path',
  props: {
    pathList: Array
  }
}
</script>

<style scoped>
.code-path-body {
  min-height: 25px;
  display: flex;
  align-items: center;
}
</style>
