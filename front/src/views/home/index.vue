<template>
  <div class="home-body">
    <div class="home-body-top">
      <div class="main-container">
        <div class="home-body-logo">
          <logo size="large" block :linked="false"></logo>
        </div>
        <div class="home-body-content">
          <p class="description">
            一个基于docker和monaco editor的在线编程系统
          </p>
        </div>
      </div>
    </div>
    <div class="main-container">
      <el-button plain @click="$router.push('/login')">去登录</el-button>
      <el-button plain @click="toCode">To Code</el-button>
      <el-button plain @click="load">Loading</el-button>
      <el-button plain @click="success">Success</el-button>
      <el-button plain @click="info">Info</el-button>
      <el-button plain @click="error">Error</el-button>
      <el-button plain @click="warning">Warning</el-button>
    </div>
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
  },
  methods: {
    toCode () {
      window.open('/code/1')
    },
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

<style lang="less" scoped>
.home-body {

  .home-body-top {
    background-color: #ecf5ff;

    .home-body-logo {
      display: flex;
      align-items: center;
      justify-content: center;
      opacity: .3;
    }

    .home-body-content {
      .description {
        font-size: 25px;
        display: flex;
        align-items: center;
        justify-content: center;
        font-weight: 600;
        color: #888;
      }
    }
  }

  .home-body-content {
  }
}
</style>
