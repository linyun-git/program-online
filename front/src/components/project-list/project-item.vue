<template>
  <div class="project-item-body">
    <h3 class="project-item-title"><span @click="toProject(project.id)">{{ project.title }}</span></h3>
    <p class="project-item-description">{{ project.description }}</p>
    <div class="project-item-tag-container">
      <span>作者：{{ project.author }}</span>
      <span>创建日期：{{ project.createDate }}</span>
    </div>
  </div>
</template>

<script>
export default {
  name: 'project-item',
  props: {
    project: {
      type: Object,
      default: () => {
      }
    }
  },
  data () {
    return {}
  },
  methods: {
    toProject (projectId) {
      console.log(1)
      this.$router.push(`/project/${projectId}?type=code`)
    }
  }
}
</script>

<style lang="less" scoped>
.project-item-body {
  padding: 20px 0 10px;
  border-bottom: 1px solid #EBEEF5;

  > * {
    margin-top: 0;
    padding: 0;
  }

  > *:not(:last-child) {
    margin-bottom: 10px;
  }

  .project-item-title {
    font-size: 16px;

    span {
      cursor: pointer;
    }
  }

  .project-item-description {
    font-size: 14px;
  }

  .project-item-tag-container {
    font-size: 13px;
    color: #909399;

    > *:not(:last-child) {
      margin-right: 5px;
    }
  }
}
</style>
