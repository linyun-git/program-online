const CopyWebpackPlugin = require('copy-webpack-plugin')

module.exports = {
  configureWebpack: {
    plugins: [
      new CopyWebpackPlugin([
        {
          from: 'node_modules/monaco-editor',
          to: 'monaco-editor'
        }
      ])
    ]
  }
}
