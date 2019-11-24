# react
- [React 技术栈系列教程](http://www.ruanyifeng.com/blog/2016/09/react-technology-stack.html)
- [2019 年 React 学习路线图](https://www.infoq.cn/article/AEkiVAiJf25LZmoUe_yc)


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