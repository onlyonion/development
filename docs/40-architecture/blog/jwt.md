JWT = header + playload + encryp

生成
1. header 包含算法/token类型
2. palyload 自定义值，json转换成字符串，然后base64url编码
3. 1、2部分密文拼接后加盐加密，之后再做base64

服务端校检
1. 对token进行切割
2. 对第二段进行base64url解码，获取playload，检测token是否超时
3. 把1、2端拼接，再次进行sha256加密，对比加密后的与原3段

