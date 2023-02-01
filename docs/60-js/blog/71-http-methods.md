- get
- put
- post
- delete
- option
- head

功能上：post是向服务器申请URI所在位置更新。put是直接向服务器发送URI就是创建或更新本身的申请

安全性：put每次更新都要发送完整的属性，所以put是幂等的。post更新可以只发送一部分属性或完整的属性，所以post是非幂等的