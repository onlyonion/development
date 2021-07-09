RxJava – Reactive Extensions for the JVM – 

- a library for composing asynchronous and event-based programs using observable sequences for the Java VM.
- 响应式编程是一种基于**异步数据流**概念的编程模式
- JVM响应式扩展Reactive Extensions 用于使用Java VM的可观察序列编写**异步**和**基于事件**的程序的库。
- 一个基于**事件流**、实现**异步操作**的库


| 角色                   | 作用                         | 类比   |
| :--------------------- | :--------------------------- | :----- |
| 被观察者（Observable） | 产生事件                     | 顾客   |
| 观察者（Observer）     | 接收事件，并给出响应动作     | 厨房   |
| 订阅（Subscribe）      | 连接 被观察者 & 观察者       | 服务员 |
| 事件（Event）          | 被观察者 & 观察者 沟通的载体 | 菜式   |

RxJava原理可总结为：
- 被观察者 （Observable） 通过 订阅（Subscribe） 按顺序发送事件 给观察者 （Observer）
- 观察者（Observer） 按顺序接收事件 & 作出对应的响应动作。

https://www.jianshu.com/p/d52ef3ad7460
