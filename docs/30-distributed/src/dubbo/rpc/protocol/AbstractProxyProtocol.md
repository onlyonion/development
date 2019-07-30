com.alibaba.dubbo.rpc.protocol.AbstractProxyProtocol

* CopyOnWriteArrayList

## hierarchy
```
AbstractProtocol (com.alibaba.dubbo.rpc.protocol)
    AbstractProxyProtocol (com.alibaba.dubbo.rpc.protocol)
        HessianProtocol (com.alibaba.dubbo.rpc.protocol.hessian)
        HttpProtocol (com.alibaba.dubbo.rpc.protocol.http)
        RmiProtocol (com.alibaba.dubbo.rpc.protocol.rmi)
        WebServiceProtocol (com.alibaba.dubbo.rpc.protocol.webservice)
```
## define


## fileds
```java
    private final List<Class<?>> rpcExceptions = new CopyOnWriteArrayList<Class<?>>();
    private ProxyFactory proxyFactory;
```

## methods

### export
```java
    public <T> Exporter<T> export(final Invoker<T> invoker) throws RpcException {
        final String uri = serviceKey(invoker.getUrl());
        Exporter<T> exporter = (Exporter<T>) exporterMap.get(uri);
        if (exporter != null) {
            return exporter;
        }
        final Runnable runnable = doExport(proxyFactory.getProxy(invoker), invoker.getInterface(), invoker.getUrl());
        exporter = new AbstractExporter<T>(invoker) {
            public void unexport() {
                super.unexport();
                exporterMap.remove(uri);
                if (runnable != null) {
                    try {
                        runnable.run();
                    } catch (Throwable t) {
                        logger.warn(t.getMessage(), t);
                    }
                }
            }
        };
        exporterMap.put(uri, exporter);
        return exporter;
    }
    
    protected abstract <T> Runnable doExport(T impl, Class<T> type, URL url) throws RpcException;
```

### refer
```java
    public <T> Invoker<T> refer(final Class<T> type, final URL url) throws RpcException {
        final Invoker<T> tagert = proxyFactory.getInvoker(doRefer(type, url), type, url);
        Invoker<T> invoker = new AbstractInvoker<T>(type, url) {
            @Override
            protected Result doInvoke(Invocation invocation) throws Throwable {
                try {
                    Result result = tagert.invoke(invocation);
                    Throwable e = result.getException();
                    if (e != null) {
                        for (Class<?> rpcException : rpcExceptions) {
                            if (rpcException.isAssignableFrom(e.getClass())) {
                                throw getRpcException(type, url, invocation, e);
                            }
                        }
                    }
                    return result;
                } catch (RpcException e) {
                    if (e.getCode() == RpcException.UNKNOWN_EXCEPTION) {
                        e.setCode(getErrorCode(e.getCause()));
                    }
                    throw e;
                } catch (Throwable e) {
                    throw getRpcException(type, url, invocation, e);
                }
            }
        };
        invokers.add(invoker);
        return invoker;
    }
    
    protected abstract <T> T doRefer(Class<T> type, URL url) throws RpcException;
```