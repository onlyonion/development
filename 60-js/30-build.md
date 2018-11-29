
## js 构建

1.	反复重复性的工作	压缩、编译、单元测试
2.	自动化工具	grunt, gulp, digo, browserify, webpack, seajs, requirejs
3.	js代码压缩工具	uglify
4.	包管理工具	npm, cnpm, spm
5.	类比java里的ant, maven, jenkins

### gulp
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

### JSX

看起来很像 XML 的 JavaScript 语法扩展

```js
ReactDOM.render(
    <h1>Hello, world!</h1>,
    document.getElementById('example')
);
```

JSX 中使用 JavaScript 表达式。表达式写在花括号 {} 中

```js
ReactDOM.render(
    <div>
      <h1>{1+1}</h1>
    </div>
    ,
    document.getElementById('example')
);
```

HTML 标签 vs. React 组件