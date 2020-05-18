

## number
1 bytes = 8 bit ,一个字节最多可以代表的数据长度是2的8次方 11111111 在计算机中也就是-128到127

1. BIT[M] 位字段类型，M表示每个值的位数，范围从1到64，如果M被忽略，默认为1
1. TINYINT[(M)] [UNSIGNED] [ZEROFILL]  M默认为4 很小的整数。带符号的范围是-128到127。无符号的范围是0到255。
2. BOOL，BOOLEAN 是TINYINT(1)的同义词。zero值被视为假。非zero值视为真。
3. SMALLINT[(M)] [UNSIGNED] [ZEROFILL] M默认为6 小的整数。带符号的范围是-32768到32767。无符号的范围是0到65535。
4. MEDIUMINT[(M)] [UNSIGNED] [ZEROFILL] M默认为9 中等大小的整数。带符号的范围是-8388608到8388607。无符号的范围是0到16777215。
5. INT[(M)] [UNSIGNED] [ZEROFILL]   M默认为11 普通大小的整数。带符号的范围是-2147483648到2147483647。无符号的范围是0到4294967295。
6. BIGINT[(M)] [UNSIGNED] [ZEROFILL] M默认为20