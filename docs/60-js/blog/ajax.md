[@RequestBody和@RequestParam](https://blog.csdn.net/weixin_38004638/article/details/99655322)
### @RequestParam 
@RequestParam 请求参数 处理 Content-Type为application/x-www-form-urlencoded 
```
GET  没有HttpEntity，所以@RequestBody并不适用
remove(@RequestParam("ids[]") Integer[] ids) // 处理url字符串后边的参数
$.ajax({url : 'remove.do', dataType : 'json', type : 'post', data : {ids : ids});

```
### @RequestBody
@RequestBody 请求体 处理非application/x-www-form-urlencoded，如application/json、application/xml
```
POST 通过HttpEntity传递的参数，必须要在请求头中声明数据的类型Content-Type
SpringMVC通过使用HandlerAdapter 配置的HttpMessageConverters来解析HttpEntity中的数据，然后绑定到相应的bean上。
```


### POST请求时
@RequestBody --> JSON字符串部分

@RequestParam --> 请求参数部分