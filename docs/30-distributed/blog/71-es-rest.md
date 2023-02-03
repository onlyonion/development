
### shards
分片数默认是5，分片数一旦确定就不能改变
```shell
PUT test/_settings
{
    "index": {
        "number_of_shards" : 1
    }
}
```

### mapping
PUT http://localhost:9200/product/_mapping  创建映射
GET http://localhost:9200/product/_mapping  查看映射

type
- String 类型
  - text 可分词
  - keword 不可分词
- Numerical ：数值类型，分两类
  - 基本数据类型：long 、 integer 、 short 、 byte 、 double 、 float 、 half_float
  - 浮点数的高精度类型：scaled_float
- Date ：日期类型
- Array ：数组类型
- Object ：对象
- index ：是否索引，默认为 true ，也就是说你不进行任何配置，所有字段都会被索引。
  - true：字段会被索引，则可以用来进行搜索
  - false：字段不会被索引，不能用来搜索
- store ：是否将数据进行独立存储，默认为 false
    原始的文本会存储在_source 里面，默认情况下其他提取出来的字段都不是独立存储
    的，是从 _source 里面提取出来的。当然你也可以独立的存储某个字段，只要设置
    "store": true 即可，获取独立存储的字段要比从 _source 中解析快得多，但是也会占用
    更多的空间，所以要根据实际业务需求来设置。
- analyzer ：分词器，这里的 ik_max_word 即使用 ik 分词器 后面会有专门的章节学习
- geo_point