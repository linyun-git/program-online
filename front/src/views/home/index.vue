<template>
  <div class="home">
    <img alt="Vue logo" src="~@/assets/logo.png">
    <el-button plain @click="$router.push('/login')">去登录</el-button>
    <el-button plain @click="load">Loading</el-button>
    <el-button plain @click="success">Success</el-button>
    <el-button plain @click="info">Info</el-button>
    <el-button plain @click="error">Error</el-button>
    <el-button plain @click="warning">Warning</el-button>
  </div>
</template>

<script>
import message from '@/utils/message'

export default {
  data () {
    return {
      loading: null
    }
  },
  created () {
    console.log(this.$api)
  },
  methods: {
    load () {
      this.loading = message.fullLoading()
    },
    success () {
      message.success('这里是成功了的消息')
    },
    info () {
      message.info('您有新的消息')
    },
    error () {
      message.error('这里出现了一个错误')
    },
    warning () {
      message.warning('这里是警告')
    }
  }
}
</script>
