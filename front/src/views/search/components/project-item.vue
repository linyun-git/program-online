<template>
  <div class="content-item">
    <h3 class="content-title">{{ title }}</h3>
    <p class="content-description">{{ description }}</p>
    <div class="content-tag-container">
      <el-tag size="small" type="info" v-for="environment of environments" :key="environment.name">{{ environment.name }}
        {{ environment.version }}
      </el-tag>
    </div>
    <div class="content-unimportant content-tag-container"><span>作者：白风</span><span>创建日期：2020-01-01</span></div>
  </div>
</template>

<script>
export default {
  name: 'project-item',
  data () {
    return {
      title: 'GavinZhulei/vue-form-making',
      description: '动态表单，VUE动态表单。基于vue+element ui实现动态表单组件，通过拖拽组件到面板即可实现一个表单。支持各个组件的动态隐藏显示，动态表格弹窗式维护。致力打造开源最强vue动态表单组件，持续维护。',
      environments: [
        {
          name: 'nodejs',
          version: '1.1'
        },
        {
          name: 'java',
          version: '13.0'
        },
        {
          name: 'docker',
          version: '1.2'
        }
      ]
    }
  }
}
</script>

<style scoped>

</style>
