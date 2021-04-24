const localStorage = window.localStorage

// 设值
export const setLocal = (name, content) => {
  if (!name) return
  if (typeof content !== 'string') {
    content = JSON.stringify(content)
  }
  localStorage.setItem(name, content)
}

// 取值
export const getLocal = (name) => {
  if (!name) return
  let value = localStorage.getItem(name)
  if (value !== null) {
    try {
      value = JSON.parse(value)
    } catch (error) {
    }
  }
  return value
}

// 删除数据
export const removeLocal = (name) => {
  if (!name) return
  localStorage.removeItem(name)
}
