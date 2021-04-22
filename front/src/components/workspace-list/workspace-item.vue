<template>
  <div class="content-item">
    <h3 class="content-title"><span class="workspace-title" @click="onTitleClick">{{ workspace.name }}</span></h3>
    <p class="content-description">{{ workspace.description }}</p>
    <div class="content-tag-container">
      <el-tag size="small" type="info" v-for="environment of workspace.environments" :key="environment.name">{{
          environment.name
        }}
        {{ environment.version }}
      </el-tag>
    </div>
    <div class="content-unimportant content-tag-container"><span>作者：{{
        workspace.creator
      }}</span><span>创建日期：{{ workspace.createDate }}</span></div>
  </div>
</template>

<script>
export default {
  name: 'workspace-item',
  props: {
    workspace: Object
  },
  methods: {
    onTitleClick () {
      this.$router.push('/workspace/1?type=project')
    }
  }
}
</script>

<style lang="less" scoped>
.content-item {
  padding: 20px 0 10px;
  border-bottom: 1px solid #EBEEF5;

  > * {
    margin-top: 0;
    padding: 0;
  }

  > *:not(:last-child) {
    margin-bottom: 10px;
  }

  .workspace-title {
    cursor: pointer;
  }

  .content-title {
    font-size: 16px;
  }

  .content-description {
    font-size: 14px;
  }

  .content-tag-container {
    > *:not(:last-child) {
      margin-right: 5px;
    }
  }

  .content-unimportant {
    font-size: 13px;
    color: #909399;
  }
}
</style>
