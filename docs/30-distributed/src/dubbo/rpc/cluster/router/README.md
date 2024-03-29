com.alibaba.dubbo.rpc.cluster.router
## package
```
condition
    ConditionRouter
    ConditionRouterFactory
file
    FileRouterFactory
script
    ScriptRouter
    ScriptRouterFactory
MockInvokersSelector
```

## condition

## file

## script

### ScriptRouter

#### define
```java
public class ScriptRouter implements Router {
    private static final Logger logger = LoggerFactory.getLogger(ScriptRouter.class);
    private static final Map<String, ScriptEngine> engines = new ConcurrentHashMap<String, ScriptEngine>();
    private final ScriptEngine engine;
    private final int priority;
    private final String rule;
    private final URL url;
}    
```

#### methods
```java

    public ScriptRouter(URL url) {
            this.url = url;
            String type = url.getParameter(Constants.TYPE_KEY);
            this.priority = url.getParameter(Constants.PRIORITY_KEY, 0);
            String rule = url.getParameterAndDecoded(Constants.RULE_KEY);
            if (type == null || type.length() == 0) {
                type = Constants.DEFAULT_SCRIPT_TYPE_KEY;
            }
            if (rule == null || rule.length() == 0) {
                throw new IllegalStateException(new IllegalStateException("route rule can not be empty. rule:" + rule));
            }
            ScriptEngine engine = engines.get(type);
            if (engine == null) {
                engine = new ScriptEngineManager().getEngineByName(type);
                if (engine == null) {
                    throw new IllegalStateException(new IllegalStateException("Unsupported route rule type: " + type + ", rule: " + rule));
                }
                engines.put(type, engine);
            }
            this.engine = engine;
            this.rule = rule;
        }

    public <T> List<Invoker<T>> route(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        try {
            List<Invoker<T>> invokersCopy = new ArrayList<Invoker<T>>(invokers);
            Compilable compilable = (Compilable) engine;
            Bindings bindings = engine.createBindings();
            bindings.put("invokers", invokersCopy);
            bindings.put("invocation", invocation);
            bindings.put("context", RpcContext.getContext());
            CompiledScript function = compilable.compile(rule);
            Object obj = function.eval(bindings);
            if (obj instanceof Invoker[]) {
                invokersCopy = Arrays.asList((Invoker<T>[]) obj);
            } else if (obj instanceof Object[]) {
                invokersCopy = new ArrayList<Invoker<T>>();
                for (Object inv : (Object[]) obj) {
                    invokersCopy.add((Invoker<T>) inv);
                }
            } else {
                invokersCopy = (List<Invoker<T>>) obj;
            }
            return invokersCopy;
        } catch (ScriptException e) {
            //fail then ignore rule .invokers.
            logger.error("route error , rule has been ignored. rule: " + rule + ", method:" + invocation.getMethodName() + ", url: " + RpcContext.getContext().getUrl(), e);
            return invokers;
        }
    }
```