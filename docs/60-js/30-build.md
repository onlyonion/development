
- js构建
- 加载器
- 打包器

## js 构建
1.	反复重复性的工作	压缩、编译、单元测试
2.	自动化工具	grunt, gulp, digo, browserify, webpack, seajs, requirejs
3.	js代码压缩工具	uglify
4.	包管理工具	npm, cnpm, spm
5.	类比java里的 ant, maven, jenkins

Unix操作系统的管道（pipe）思想，前一级的输出，直接变成后一级的输入

前后端分离开发部署模式

持续集成 GitHub上托管的开源项目用Travis CI进行持续集成 Jenkins/Hudson Travis CI Bamboo TeamCity ThoughtWorks GO Gitlab CI
REPL环境（Read–eval–print loop，”读取-求值-输出”循环
HTML 标签 vs. React 组件

### gulp
gulp.js - 基于流的自动化构建工具
用自动化构建工具增强你的工作流程！

```sh
# 全局安装
npm install --global gulp
# 作为项目的开发依赖（devDependencies）安装
npm install --save-dev gulp
# 在项目根目录下创建一个名为 gulpfile.js 的文件：
var gulp = require('gulp');
gulp.task('default', function() {
  // 将你的默认的任务代码放在这
});
# 运行
gulp
```

#### grunt
JavaScript 世界的构建工具。为何要用构建工具？
自动化。对于需要反复重复的任务，例如压缩（minification）、编译、单元测试、linting等，自动化工具可以减轻你的劳动，简化你的工作。
当你在 Gruntfile 文件正确配置好了任务，任务运行器就会自动帮你或你的小组完成大部分无聊的工作

### digo
digo 是一个描述式的轻量自动化构建工具


## loaer 加载器

### 模块化 modularization
* CMD 服务器端模块的规范，Node.js采用了这个规范
* AMD Asynchronous Module Definition，异步模块定义。它是一个在浏览器端模块化开发的规范，模块和依赖可以异步加载，对浏览器端较为适用
* CommonJS 

CMD 是 SeaJS 在推广过程中对模块定义的规范化产出。
AMD 是 RequireJS 在推广过程中对模块定义的规范化产出。
类似的还有 CommonJS Modules/2.0 规范，是 BravoJS 在推广过程中对模块定义的规范化产出。

### SeaJS
SeaJS 是一个适用于 Web 浏览器端的模块加载器。使用 SeaJS，可以更好地组织 JavaScript 代码。

### RequireJS
RequireJS 是一个JavaScript模块加载器。

[CMD、AMD、CommonJS 规范](https://www.jianshu.com/p/5eb49f5c5196)

## 打包
### browserify
Browserify lets you require('modules') in the browser by bundling up all of your dependencies.

Browserify 可以让你使用类似于 node 的 require() 的方式来组织浏览器端的 Javascript 代码，
通过预编译让前端 Javascript 可以直接使用 Node NPM 安装的一些库

### webpack
本质上，webpack 是一个现代 JavaScript 应用程序的静态模块打包器(module bundler)。
当 webpack 处理应用程序时，它会递归地构建一个依赖关系图(dependency graph)，其中包含应用程序需要的每个模块，然后将所有这些模块打包成一个或多个 bundle。