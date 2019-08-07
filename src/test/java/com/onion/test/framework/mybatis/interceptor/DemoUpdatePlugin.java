package com.onion.test.framework.mybatis.interceptor;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import com.alibaba.fastjson.JSON;
import com.onion.test.common.id.SnowflakeIdWorker;
import com.onion.test.common.model.User;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Intercepts({
        // int update(MappedStatement ms, Object parameter) throws SQLException
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})
})
@Setter
@Getter
@Slf4j
public class DemoUpdatePlugin implements Interceptor {

    private Integer testProp;
    private Properties properties;
    SnowflakeIdWorker idWorker = new SnowflakeIdWorker(0, 0);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        log.info("拦截对象 {}", invocation.getTarget());
        log.info("拦截方法 {}", invocation.getMethod());
        log.info("方法参数 {}", invocation.getArgs());

        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        System.out.println(JSON.toJSONString(objects[1]));
        if (objects[1] instanceof User) {
            User user =(User) objects[1];
            //user.setLongId(idWorker.nextId());
            //user.setLongId(123L);
        }
        System.out.println(JSON.toJSONString(objects[1]));
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
        log.info("插件初始化属性 {}", properties);
    }
}
