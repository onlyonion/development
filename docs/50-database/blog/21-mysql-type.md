https://www.runoob.com/mysql/mysql-data-types.html

1 bytes = 8 bit ,一个字节最多可以代表的数据长度是2的8次方 11111111 在计算机中也就是-128到127

## 数值
- BIT[M] 位字段类型，M表示每个值的位数，范围从1到64，如果M被忽略，默认为1
- TINYINT[(M)] [UNSIGNED] [ZEROFILL]  M默认为4 很小的整数。带符号的范围是-128到127。无符号的范围是0到255。
- BOOL，BOOLEAN 是TINYINT(1)的同义词。zero值被视为假。非zero值视为真。
- SMALLINT[(M)] [UNSIGNED] [ZEROFILL] M默认为6 小的整数。带符号的范围是-32768到32767。无符号的范围是0到65535。
- MEDIUMINT[(M)] [UNSIGNED] [ZEROFILL] M默认为9 中等大小的整数。带符号的范围是-8388608到8388607。无符号的范围是0到16777215。
- INT[(M)] [UNSIGNED] [ZEROFILL]   M默认为11 普通大小的整数。带符号的范围是-2147483648到2147483647。无符号的范围是0到4294967295。
- BIGINT[(M)] [UNSIGNED] [ZEROFILL] M默认为20
- float
- double
- decimal

## 日期与时间
- date
- time
- year
- datetime '1000-01-01 00:00:00' 到 '9999-12-31 23:59:59'
- timestamp '1970-01-01 00:00:01' UTC 到 '2038-01-19 03:14:07' UTC

## 字符
utf8mb4
- char
- varchar
- tinytext
- text
- mediumtext
- longtext
- tinyblob
- blob
- mediumblog
- longblob