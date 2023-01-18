com.alibaba.nacos.client.config.impl.CacheData


## methods

### checkListenerMd5
```java
    void checkListenerMd5() {
        for (ManagerListenerWrap wrap : listeners) {
            if (!md5.equals(wrap.lastCallMd5)) {
                safeNotifyListener(dataId, group, content, type, md5, encryptedDataKey, wrap);
            }
        }
    }
```

### safeNotifyListener
listener.receiveConfigInfo(contentTmp);

