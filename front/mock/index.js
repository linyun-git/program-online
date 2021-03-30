const express = require('express')
const app = express()
const loginSuccess = require('./data/login_success.json')
// const error = require('./data/error.json')
// const url = require('url')

const port = 3300

// app.get('/login', (req, res) => {
//   const user = url.parse(req.url, true).query.user
//   if (user === 'admin') {
//     res.send(adminLogin)
//   } else {
//     res.send(vipLogin)
//   }
// })

app.post('/login', (req, res) => {
  res.send(loginSuccess)
})

app.listen(port, () => {
  console.log(`服务器运行在${port}端口上`)
})
