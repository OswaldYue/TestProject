package com.mgw.algorithm;

import com.mgw.algorithm.helper.SortHelper;

public class InsertSort implements ISort {


    @Override
    public <T extends Comparable> void sort(T[] arr) {

        for (int i = 1; i < arr.length; i++) {

//            for (int j = i ; j > 0;j--) {
//
//                if (arr[j].compareTo(arr[j-1]) < 0) {
//                    SortHelper.swap(arr,j-1,j);
//                }else {
//                    break;
//                }
//
//            }
            for (int j = i ; j > 0 && arr[j].compareTo(arr[j-1]) < 0;j--) {
                SortHelper.swap(arr,j-1,j);
            }
        }

//        System.out.println(Arrays.toString(arr));
    }
}
