const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin'); // 通过 npm 安装
const webpack = require('webpack'); // 用于访问内置插件

module.exports = {
  mode: 'production',
  entry: './path/to/my/entry/file.js',
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: 'my-first-webpack.bundle.js'
  },
  module: {
    rules: [
      { test: /\.txt$/, use: 'raw-loader' } // require()/import 语句中被解析为 '.txt' 的路径，打包之前，先 使用 raw-loader 转换
    ],
    plugins: [
      new HtmlWebpackPlugin({template: './src/index.html'})
    ]
  }
};
