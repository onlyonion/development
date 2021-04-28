## http
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
- org.apache.httpcomponents:httpasyncclient:4.1.4
- org.apache.httpcomponents:httpclient:4.5.9
- org.apache.httpcomponents:httpcore:4.4.11
- org.apache.httpcomponents:httpcore-nio:4.4.11


### Retrofit
Retrofit接口层封装请求参数、Header、Url等信息，之后由OkHttp完成后续的请求操作，服务端返回数据之后，OkHttp将原始结果交给Retrofit，后者根据用户需求对结果进行解析。

- com.squareup.okhttp3:logging-interceptor:3.14.9
- com.squareup.okhttp3:okhttp:3.14.9
- com.squareup.okio:okio:1.17.2
- com.squareup.retrofit2:converter-gson:2.4.0
- com.squareup.retrofit2:converter-jackson:2.9.0
- com.squareup.retrofit2:retrofit:2.4.0
