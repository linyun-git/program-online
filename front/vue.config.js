module.exports = {
  devServer: {
    proxy: {
      '/': {
        target: 'http://localhost:3300',
        changeOrigin: true,
        pathRewrite: {
          '^/': ''
        }
      }
    }
  }
}
