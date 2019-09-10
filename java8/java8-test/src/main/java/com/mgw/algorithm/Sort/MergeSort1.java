package com.mgw.algorithm.Sort;

import java.util.Arrays;

public class MergeSort1 {



    public  void sort(Comparable[] arr) {

        int length = arr.length;

        sort(arr,0,length - 1);



    }

    // 递归使用归并排序,对arr[l...r]的范围进行排序
    private void sort(Comparable[] arr, int l, int r) {

        if (l >= r) {
            return;
        }

        int mid = (l+r)/2;
        //左
        sort(arr,l,mid);
        //右
        sort(arr,mid+1,r);
        //合并
        merge(arr,l,mid,r);

    }

    //合并  将arr[l...mid]和arr[mid+1...r]两部分进行归并
    private void merge(Comparable[] arr, int l, int mid, int r) {

        //临时数组
        Comparable[] aux = Arrays.copyOfRange(arr, l, r+1);

        // 初始化，i指向左半部分的起始索引位置l；j指向右半部分起始索引位置mid+1
        int i = l, j = mid+1;
        for( int k = l ; k <= r; k ++ ){

            // 如果左半部分元素已经全部处理完毕
            if( i > mid ){
                arr[k] = aux[j-l];
                j ++;
            }
            // 如果右半部分元素已经全部处理完毕
            else if( j > r ){
                arr[k] = aux[i-l];
                i ++;
            }
            // 左半部分所指元素 < 右半部分所指元素
            else if( aux[i-l].compareTo(aux[j-l]) < 0 ){
                arr[k] = aux[i-l];
                i ++;
            }
            // 左半部分所指元素 >= 右半部分所指元素
            else{
                arr[k] = aux[j-l];
                j ++;
            }

        }
    }

}
