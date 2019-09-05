package com.mgw.algorithm;

public class InsertSort implements ISort {


    @Override
    public <T extends Comparable> void sort(T[] arr) {

        for (int i = 1; i < arr.length; i++) {

            // 写法1
//            for (int j = i ; j > 0;j--) {
//
//                if (arr[j].compareTo(arr[j-1]) < 0) {
//                    SortHelper.swap(arr,j-1,j);
//                }else {
//                    break;
//                }
//
//            }
            // 写法2
//            for (int j = i ; j > 0 && arr[j].compareTo(arr[j-1]) < 0;j--) {
//                SortHelper.swap(arr,j-1,j);
//            }
            // 写法3
            T t = arr[i];
            int j; // j保存元素t应该插入的位置
            for (j = i; j > 0 && arr[j-1].compareTo(t) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = t;

        }
    }
}
