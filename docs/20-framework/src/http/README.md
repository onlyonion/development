
- HttpUrlConnection
- Apache HttpClient
- Volley
- AsyncHttpClient
- OkHttp
- Retrofit

### asynchttpclient
- org.asynchttpclient:async-http-client:2.0.32
- org.asynchttpclient:async-http-client-netty-utils:2.0.32
- org.asynchttpclient:netty-codec-dns:2.0.32
- org.asynchttpclient:netty-resolver:2.0.32
- org.asynchttpclient:netty-resolver-dns:2.0.32

### httpcomponents


### Retrofit
Retrofit接口层封装请求参数、Header、Url等信息，之后由OkHttp完成后续的请求操作，服务端返回数据之后，OkHttp将原始结果交给Retrofit，后者根据用户需求对结果进行解析。