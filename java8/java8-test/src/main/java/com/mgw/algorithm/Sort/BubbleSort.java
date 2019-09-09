package com.mgw.algorithm.Sort;

import com.mgw.algorithm.Sort.helper.SortHelper;

import java.util.Arrays;

public class BubbleSort implements ISort{


    @Override
    public <T extends Comparable> void sort(T[] arr) {

        int length = arr.length;

//        for (int i = 0; i < length - 1; i++) {
//
//            for (int j = 0 ; j < length-i-1 ; j++) {
//
//                if (arr[j].compareTo(arr[j+1]) > 0) {
//
//                    SortHelper.swap(arr,j,j+1);
//                }
//            }
//        }
        //优化算法:标志变量用于记录每趟冒泡排序是否发生数据元素位置交换。如果没有发生交换，说明序列已经有序了，不必继续进行下去了。
        int change = 1;
        for (int i = 0; i < length - 1 && change != 0; i++) {
            change = 0;
            for (int j = 0 ; j < length-i-1 ; j++) {

                if (arr[j].compareTo(arr[j+1]) > 0) {

                    SortHelper.swap(arr,j,j+1);
                    change = 1;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{

        int n = 10;

        Integer[] ints = SortHelper.generateArray(n, 0, 100);

        ISort bubbleSort = new BubbleSort();
        bubbleSort.sort(ints);

        if (!SortHelper.isSorted(ints)) {
            throw new Exception("排序错误");
        }

        System.out.println(Arrays.toString(ints));

    }
}
