
## Cluster

```yuml
// {type:class}

[Cluster||+join(directory)]

[Cluster]^-.-[FailfastCluster]
[Cluster]^-.-[FailoverCluster]
[Cluster]^-.-[FailsafeCluster]
[Cluster]^-.-[FailbackCluster]
[Cluster]^-.-[BroadcastCluster]
[Cluster]^-.-[ForkingCluster]

```
