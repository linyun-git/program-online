<template>
<div class="terminal-body"></div>
</template>

<script>
import 'xterm/css/xterm.css'
import { Terminal } from 'xterm'
import { FitAddon } from 'xterm-addon-fit'
import { AttachAddon } from 'xterm-addon-attach'
import * as storage from '../../utils/storage-tools'

export default {
  name: 'index',
  computed: {
    token () {
      return storage.getLocal('token')
    },
    projectId () {
      return parseInt(this.$route.params.projectId, 10)
    }
  },
  mounted () {
    const term = new Terminal()
    const fitAddon = new FitAddon()
    const socket = new WebSocket(`ws://service.explosion.red:9000/terminal/${this.projectId}?token=${this.token}`)
    const attachAddon = new AttachAddon(socket)

    term.loadAddon(attachAddon)
    term.loadAddon(fitAddon)
    term.open(this.$el)
    fitAddon.fit()
    term.focus()
    // 当连接建立时向终端发送一个换行符，不这么做的话最初终端是没有内容的，输入换行符可让终端显示当前用户的工作路径
    socket.onopen = () => {
      socket.send('\n')
      socket.send('cd /root/project\n')
    }
    socket.onclose = () => {
      console.log('close')
    }

    window.onresize = function () { // 窗口尺寸变化时，终端尺寸自适应
      fitAddon.fit()
    }

    this.term = term
    this.socket = socket
  },
  beforeUnmount () {
    this.socket.close()
    this.term.dispose()
  }
}
</script>

<style scoped>
.terminal-body {
  position: fixed;
  height: 100%;
  width: 100%;
  top: 0;
  left: 0;
}
</style>
