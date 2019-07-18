java.util.Arrays

查找与内部排序
* 查找
  * binarySearch
* 内部排序
  * 插入
    * 直接插入
    * 拆半插入
    * 希尔
  * 交换
    * 冒泡
    * 快速
  * 选择
    * 简单
    * 树形
    * 堆
  * 归并
  * 基数

## java.util.Arrays

* mergeSort
* swap
* equals
* fill
* copyOf
* asList
* hashCode

### mergeSort
```java
private static final int INSERTIONSORT_THRESHOLD = 7;

private static void mergeSort(Object[] src,
                                  Object[] dest,
                                  int low,
                                  int high,
                                  int off) {
        int length = high - low;

        // Insertion sort on smallest arrays
        if (length < INSERTIONSORT_THRESHOLD) {
            for (int i=low; i<high; i++)
                for (int j=i; j>low &&
                         ((Comparable) dest[j-1]).compareTo(dest[j])>0; j--)
                    swap(dest, j, j-1);
            return;
        }

        // Recursively sort halves of dest into src
        int destLow  = low;
        int destHigh = high;
        low  += off;
        high += off;
        int mid = (low + high) >>> 1;
        mergeSort(dest, src, low, mid, -off);
        mergeSort(dest, src, mid, high, -off);

        // If list is already sorted, just copy from src to dest.  This is an
        // optimization that results in faster sorts for nearly ordered lists.
        if (((Comparable)src[mid-1]).compareTo(src[mid]) <= 0) {
            System.arraycopy(src, low, dest, destLow, length);
            return;
        }

        // Merge sorted halves (now in src) into dest
        for(int i = destLow, p = low, q = mid; i < destHigh; i++) {
            if (q >= high || p < mid && ((Comparable)src[p]).compareTo(src[q])<=0)
                dest[i] = src[p++];
            else
                dest[i] = src[q++];
        }
    }
```

## links
* [《数据结构（C语言版）》严蔚敏](/99-book/notes/00-base/数据结构.md)