package com.onion.test.framework.mybatis.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Intercepts({
        // List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler)
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
        // void close(boolean forceRollback)
        // @Signature(type = Executor.class, method = "close", args = {boolean.class})
})
@Setter
@Getter
@Slf4j
public class DemoPlugin implements Interceptor {

    private Integer testProp;
    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        log.info("拦截对象 {}", invocation.getTarget());
        log.info("拦截方法 {}", invocation.getMethod());
        log.info("方法参数 {}", invocation.getArgs());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
