
## registry
npm config set registry http://registry.npm.taobao.org/

npm config set registry https://registry.npmjs.org/
## script
npm run 实际上是 npm run-script 命令的简写
```js
 "scripts": {
    "test": "echo \"Error: no test specified\" && exit 1"
  },
```
npm link
```js
"bin": {
        "kid": "index.js"
    }
```

## yarn
- yarn 快速、可靠、安全的依赖管理工具