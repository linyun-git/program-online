const express = require('express')
const app = express()
const loginSuccess = require('./data/login_success.json')
const error = require('./data/error.json')
const url = require('url')

const port = 3300

app.use((req, res, next) => {
  res.header('Access-Control-Allow-Origin', '*')
  res.header('Access-Control-Allow-Headers', 'Authorization,X-API-KEY, Origin, X-Requested-With, Content-Type, Accept, Access-Control-Request-Method')
  res.header('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, PATCH, PUT, DELETE')
  res.header('Allow', 'GET, POST, PATCH, OPTIONS, PUT, DELETE')
  next()
})

app.get('/login', (req, res) => {
  const user = url.parse(req.url, true).query.user
  if (user === 'admin') {
    res.send(loginSuccess)
  } else {
    res.send(error)
  }
})

app.post('/login', (req, res) => {
  res.send(loginSuccess)
})

app.listen(port, () => {
  console.log(`服务器运行在${port}端口上`)
})
