import { ElNotification, ElLoading } from 'element-plus'

export default {
  info: (message, duration = 4500) => {
    ElNotification({
      title: '消息',
      message,
      type: 'info',
      duration
    })
  },
  success: (message, duration = 4500) => {
    ElNotification({
      title: '成功',
      message,
      type: 'success',
      duration
    })
  },
  error: (message, duration = 4500) => {
    ElNotification({
      title: '错误',
      message,
      type: 'error',
      duration
    })
  },
  warning: (message, duration = 4500) => {
    ElNotification({
      title: '警告',
      message,
      type: 'warning',
      duration
    })
  },
  fullLoading: (message, spinner, background) => {
    return ElLoading.service({
      lock: true,
      text: message,
      spinner,
      background
    })
  },
  closeLoading: loading => loading.close()
}
