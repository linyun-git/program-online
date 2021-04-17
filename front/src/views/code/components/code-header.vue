<template>
  <div class="code-header-body">
    <logo></logo>
    <img src="https://picsum.photos/200/200" alt="">
  </div>
</template>

<script>
export default {
  name: 'code-header'
}
</script>

<style lang="less" scoped>
.code-header-body {
  height: 40px;
  line-height: 40px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 20px;

  img {
    height: 30px;
    width: 30px;
    border-radius: 50%;
  }
}
</style>
