<template>
  <el-container>
    <!-- 占位置的元素 -->
    <el-header></el-header>
    <el-header class="header-body">
      <layout-header class="header"></layout-header>
    </el-header>
    <el-main class="el-main">
      <router-view v-slot="{ Component }" :key="$route.fullPath">
        <keep-alive>
          <component :is="Component"/>
        </keep-alive>
      </router-view>
    </el-main>
  </el-container>
</template>

<script>
import LayoutHeader from './components/layout-header'

export default {
  name: 'index',
  components: {
    LayoutHeader
  }
}
</script>

<style lang="less" scoped>
.header-body {
  background-color: #fff;
  border-bottom: 1px solid #DCDFE6;
  position: fixed;
  z-index: 1;
  width: 100%;
  top: 0;
  left: 0;

  .header {
    max-width: 1200px;
    margin: 0 auto;
  }
}

.el-main {
  padding: 0;
}
</style>
