mybatis

```xml
select @@identity

<insert id="insert" parameterType="UserInfo" useGeneratedKeys="true" keyProperty="id">

<selectKey resultType="java.lang.Integer" keyProperty="id" order="AFTER">
	SELECT @@IDENTITY
</selectKey>

```


[mysql-id](https://mp.weixin.qq.com/s/iBAhnsvAvW0LVGVZcsbJ0Q)
```xml
<!-- SELECT LAST_INSERT_ID() -->
<insert id="insert" parameterType="map">
    insert into table1 (name) values (#{name})
    <selectKey resultType="java.lang.Integer" keyProperty="id">
      CALL IDENTITY()
    </selectKey>
</insert>

<insert id="insert" parameterType="Spares" useGeneratedKeys="true" keyProperty="id">
        insert into system(name) values(#{name})
</insert>
```