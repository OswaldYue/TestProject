package com.mgw.algorithm.Sort;

import com.mgw.algorithm.Sort.helper.SortHelper;

import java.util.Arrays;

public class MergeSort  implements ISort {


    @Override
    public <T extends Comparable> void sort(T[] arr) {

        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        Comparable[] temp = new Comparable[arr.length];

        sort(arr,0,arr.length-1,temp);



    }

    private <T extends Comparable> void sort(T[] arr, int left, int right, Comparable[] temp) {

        int mid = (left + right)/2;

        if(left<right) {
            //左  左边归并排序，使得左子序列有序
            sort(arr, left, mid, temp);
            //右  右边归并排序，使得右子序列有序
            sort(arr, mid + 1, right, temp);
            //合并  将两个有序子数组合并操作
            merge(arr, left, mid, right, temp);
        }
    }

    private <T extends Comparable> void merge(T[] arr, int left, int mid, int right, Comparable[] temp) {

        //左指针
        int i = left;
        //右指针
        int j = mid + 1;
        //临时指针
        int t = 0;

        while (i <= mid && j <= right) {

            if (arr[i].compareTo(arr[j]) <= 0) {
                temp[t++] = arr[i++];
            }else {
                temp[t++] = arr[j++];
            }
        }

        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        while (j <= right) {
            temp[t++] = arr[j++];
        }

        t = 0;
        //将temp中的元素全部拷贝到原数组中
        while (left <= right) {
            arr[left++] = (T)temp[t++];
        }
    }


    public static void main(String[] args) throws Exception{

        int n = 10;

        Integer[] ints = SortHelper.generateArray(n, 0, 100);

        ISort mergeSort = new MergeSort();
        mergeSort.sort(ints);

        if (!SortHelper.isSorted(ints)) {
            throw new Exception("排序错误");
        }

        System.out.println(Arrays.toString(ints));

    }
}
