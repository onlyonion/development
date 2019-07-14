
## Dispatcher

```yuml
// {type:class}
[Dispatcher]^-.-[ExecutionDispatcher]
[Dispatcher]^-.-[DirectDispatcher]
[Dispatcher]^-.-[MessageOnlyDispatcher]
[Dispatcher]^-.-[ConnectionOrderedDispatcher]
[Dispatcher]^-.-[AllDispatcher]

// 依赖
[Dispatcher]uses->[ChannelHandler]
[Dispatcher]uses->[URL]

```