com.orbitz.consul.Consul

## define
```java
public class Consul {
    public static final String DEFAULT_HTTP_HOST = "localhost";
    public static final int DEFAULT_HTTP_PORT = 8500;

    private final AgentClient agentClient;
    private final HealthClient healthClient;
    private final KeyValueClient keyValueClient;
    private final CatalogClient catalogClient;
    private final StatusClient statusClient;
    private final SessionClient sessionClient;
    private final EventClient eventClient;
    private final PreparedQueryClient preparedQueryClient;
    private final CoordinateClient coordinateClient;
    private final OperatorClient operatorClient;
}
```