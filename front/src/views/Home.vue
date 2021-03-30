<template>
  <div class="home">
    <img alt="Vue logo" src="../assets/logo.png">
    <el-button plain @click="$router.push('/login')">去登录</el-button>
  </div>
</template>

<script>

export default {
  name: 'Home',
  created () {
  }
}
</script>
