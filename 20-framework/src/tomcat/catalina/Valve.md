```
Valve (org.apache.catalina)
    ValveBase (org.apache.catalina.valves)
        RemoteIpValve (org.apache.catalina.valves)
        JDBCAccessLogValve (org.apache.catalina.valves)
        AbstractAccessLogValve (org.apache.catalina.valves)
        ErrorReportValve (org.apache.catalina.valves)
        StandardWrapperValve (org.apache.catalina.core)
        RewriteValve (org.apache.catalina.valves.rewrite)
        SemaphoreValve (org.apache.catalina.valves)
        AuthenticatorBase (org.apache.catalina.authenticator)
        StandardContextValve (org.apache.catalina.core)
        SingleSignOn (org.apache.catalina.authenticator)
        CrawlerSessionManagerValve (org.apache.catalina.valves)
        RequestFilterValve (org.apache.catalina.valves)
            RemoteHostValve (org.apache.catalina.valves)
            RemoteAddrValve (org.apache.catalina.valves)
        StandardEngineValve (org.apache.catalina.core)
        StuckThreadDetectionValve (org.apache.catalina.valves)
        SSLValve (org.apache.catalina.valves)
        PersistentValve (org.apache.catalina.valves)
        StandardHostValve (org.apache.catalina.core)
```

```
@startuml
interface Valve {
    + Valve getNext()
    + void setNext(Valve valve)

    + void backgroundProcess()
    + void invoke(Request request, Response response)
    + boolean isAsyncSupported()
}


@enduml
```