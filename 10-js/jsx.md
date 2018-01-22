JSX

看起来很像 XML 的 JavaScript 语法扩展

``` jsx
ReactDOM.render(
    <h1>Hello, world!</h1>,
    document.getElementById('example')
);
```

JSX 中使用 JavaScript 表达式。表达式写在花括号 {} 中

``` 
ReactDOM.render(
    <div>
      <h1>{1+1}</h1>
    </div>
    ,
    document.getElementById('example')
);
```

HTML 标签 vs. React 组件