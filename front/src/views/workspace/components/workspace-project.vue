<template>
  <div>
    <project-list :project-list="projectList"></project-list>
  </div>
</template>

<script>
export default {
  name: 'workspace-project',
  data () {
    return {
      projectList: [
        {
          title: '这里是标题',
          description: '这里是项目简介',
          author: '白风',
          createDate: '2021-04-19'
        }, {
          title: '这里是标题',
          description: '这里是项目简介',
          author: '白风',
          createDate: '2021-04-19'
        }, {
          title: '这里是标题',
          description: '这里是项目简介',
          author: '白风',
          createDate: '2021-04-19'
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>
