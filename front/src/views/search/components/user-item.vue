<template>
  <div class="content-item">
    <div class="user-item-body">
      <img :src="imgSrc" alt="">
      <div class="item-text-content">
        <div class="item-text-title">
          <h3 class="content-title">{{ name }}</h3>
          <el-tag effect="dark" type="danger"><i class="el-icon-plus"></i> 关注</el-tag>
          <el-tag effect="dark"><i class="el-icon-chat-dot-round"></i> 私信</el-tag>
        </div>
        <div class="item-text-description">
          {{ description }}
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'user-item',
  data () {
    return {
      id: 'userid',
      name: '这里是昵称',
      description: '动态表单，VUE动态表单。基于vue+element ui实现动态表单组件，通过拖拽组件到面板即可实现一个表单。支持各个组件的动态隐藏显示，动态表格弹窗式维护。致力打造开源最强vue动态表单组件，持续维护。',
      imgSrc: 'https://picsum.photos/200/200'
    }
  }
}
</script>

<style lang="less" scoped>
.user-item-body {
  display: flex;

  > *:not(:last-child) {
    margin-right: 10px;
  }

  .content-title {
    margin: 0;
  }

  img {
    height: 90px;
    width: 90px;
    border-radius: 50%;
    cursor: pointer;
  }

  .item-text-content {
    display: flex;
    flex-direction: column;

    > *:not(:last-child) {
      margin-bottom: 10px;
    }

    .item-text-title {
      display: flex;
      align-items: center;

      > * {
        cursor: pointer;
      }

      > *:not(:last-child) {
        margin-right: 10px;
      }
    }

    .item-text-description {
      font-size: 14px;
    }
  }
}
</style>
