function mergeSort(arr) { // 采用自上而下的递归方法
    var len = arr.length;
    if (len < 2) {
        return arr;
    }
    var middle = Math.floor(len / 2),
        left = arr.slice(0, middle),
        right = arr.slice(middle);
    return merge(mergeSort(left), mergeSort(right));
}
function merge(left, right) {
    var result = [];
    while (left.length>0 && right.length>0) {
        if (left[0] <= right[0]) {
            result.push(left.shift());
        }else {
            result.push(right.shift());
        }
    }
    while (left.length)
        result.push(left.shift());
    while (right.length)
        result.push(right.shift());
    return result;
}
mergeSort([3,9,2,4,12,1])


function insertSort(arr) {
    for (var i=1; i<arr.length; i++){
        var mi = i;
        // mi-1>=0，表示前一个数字存在
        // arr[mi-1] > arr[mi]，表示前一个数字更大
        while(mi-1 >= 0 && arr[mi-1] > arr[mi]){
            [arr[mi], arr[mi-1]] = [arr[mi-1], arr[mi]]; // 交换
            mi--;// 下标向前移动
        }
    }
}