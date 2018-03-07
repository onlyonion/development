1. 全局安装 gulp：
$ npm install --global gulp

2. 作为项目的开发依赖（devDependencies）安装：
$ npm install --save-dev gulp

3. 在项目根目录下创建一个名为 gulpfile.js 的文件：
var gulp = require('gulp');
gulp.task('default', function() {
  // 将你的默认的任务代码放在这
});

4. 运行 gulp：
$ gulp


Unix操作系统的管道（pipe）思想，前一级的输出，直接变成后一级的输入



前后端分离开发部署模式

持续集成 GitHub上托管的开源项目用Travis CI进行持续集成 Jenkins/Hudson Travis CI Bamboo TeamCity ThoughtWorks GO Gitlab CI

REPL环境（Read–eval–print loop，”读取-求值-输出”循环