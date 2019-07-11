java.util.DualPivotQuicksort

[JDK的快速排序算法实现DualPivotQuicksort](https://blog.csdn.net/Lnho2015/article/details/50669816)

具体流程如下：
1. 需要排序的数组为a,判断数组的长度是否大于286，大于使用归并排序（merge sort），否则执行2。 
2. 判断数组长度是否小于47，小于则采用插入排序，否则执行3。 
3. 采用近似算法计算数组长度的1/7
4. 取出5个点
5. 将这5个元素进行插入排序 


## fields
插入 -- 47 -- 快排 -- 286 -- 归并

* QUICKSORT_THRESHOLD = 286
* INSERTION_SORT_THRESHOLD = 47