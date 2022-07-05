表字段大小写问题


ROWNUM

```sql
select * from
  (select t.*, rownum num from DATA_TABLE_SQL t where rownum<=100 )
where num>50
```
