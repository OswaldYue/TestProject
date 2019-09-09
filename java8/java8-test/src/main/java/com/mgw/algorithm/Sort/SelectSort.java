package com.mgw.algorithm.Sort;

import com.mgw.algorithm.Sort.helper.SortHelper;

import java.util.Arrays;

public class  SelectSort implements ISort {


    @Override
    public <T extends Comparable> void  sort(T[] arr) {

        int length = arr.length;

        for (int i = 0; i < length -1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < length ;j++) {
                if (arr[j].compareTo(arr[minIndex]) <= 0 ) {
                    minIndex = j;
                }
            }
            SortHelper.swap(arr,i,minIndex);

        }
    }


    public static void main(String[] args) throws Exception {

        int n = 10;

        Integer[] ints = SortHelper.generateArray(n, 0, 100);

        ISort selectSort = new SelectSort();
        selectSort.sort(ints);

        if (!SortHelper.isSorted(ints)) {
            throw new Exception("排序错误");
        }

        System.out.println(Arrays.toString(ints));
        
    }

}
