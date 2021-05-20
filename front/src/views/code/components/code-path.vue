<template>
  <div class="code-path-body">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item v-for="(path, index) in pathList" :key="index">{{ path }}</el-breadcrumb-item>
    </el-breadcrumb>
    <a :href="`/terminal/${projectId}`" target="_blank" title="打开终端"><i class="el-icon-top-right" style="cursor: pointer" @click="onTerminal"></i></a>
  </div>
</template>

<script>
export default {
  name: 'path',
  props: {
    pathList: Array,
    projectId: Number
  }
}
</script>

<style scoped>
.code-path-body {
  min-height: 25px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;
}
</style>
