[java8集合List排序、筛选、求和、求最大值、平均值](https://blog.csdn.net/winterking3/article/details/87163993)
```java

//求和
Long sum = list.stream().mapToLong(Message::getId).sum();

// 求最大值
Optional<Message> maxMassage = list.stream().collect(Collectors.maxBy(Comparator.comparing(Message::getId)));
Long maxId = maxMassage.get().getId();


//根据多条件组合排序,先根据msg(升序),再根据id(升序)
list.sort(Comparator.comparing(Message:: getMsg).thenComparing(Message::getId));

//根据多条件组合排序,先根据msg(升序),再根据id(降序)
list.sort(Comparator.comparing(Message:: getMsg).thenComparing(Comparator.comparing(Message::getId).reversed()));

//根据多条件组合排序,先根据msg(降序),再根据id(降序)
list.sort(Comparator.comparing(Message:: getMsg).thenComparing(Message::getId).reversed());

//根据多条件组合排序,先根据msg(降序),再根据id(升序)
list.sort(Comparator.comparing(Message:: getMsg).reversed().thenComparing(Message::getId));
```