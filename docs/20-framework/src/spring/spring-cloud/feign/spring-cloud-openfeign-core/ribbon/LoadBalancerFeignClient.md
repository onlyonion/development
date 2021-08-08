org.springframework.cloud.openfeign.ribbon.LoadBalancerFeignClient

## Hierarchy
```
LoadBalancerContext (com.netflix.loadbalancer)
    AbstractLoadBalancerAwareClient (com.netflix.client)
        FeignLoadBalancer (org.springframework.cloud.openfeign.ribbon)
            RetryableFeignLoadBalancer (org.springframework.cloud.openfeign.ribbon)
        AbstractLoadBalancingClient (org.springframework.cloud.netflix.ribbon.support)
            RibbonLoadBalancingHttpClient (org.springframework.cloud.netflix.ribbon.apache)
            OkHttpLoadBalancingClient (org.springframework.cloud.netflix.ribbon.okhttp)
        RestClient (com.netflix.niws.client.http)
            OverrideRestClient in RibbonClientConfiguration (org.springframework.cloud.netflix.ribbon)
```

## Define


## Methods

### execute
```java

	@Override
	public Response execute(Request request, Request.Options options) throws IOException {
		try {
			URI asUri = URI.create(request.url());
			String clientName = asUri.getHost();
			URI uriWithoutHost = cleanUrl(request.url(), clientName);
			FeignLoadBalancer.RibbonRequest ribbonRequest = new FeignLoadBalancer.RibbonRequest(
					this.delegate, request, uriWithoutHost);

			IClientConfig requestConfig = getClientConfig(options, clientName);
			return lbClient(clientName)
					.executeWithLoadBalancer(ribbonRequest, requestConfig).toResponse();
		}
		catch (ClientException e) {
			IOException io = findIOException(e);
			if (io != null) {
				throw io;
			}
			throw new RuntimeException(e);
		}
	}

```