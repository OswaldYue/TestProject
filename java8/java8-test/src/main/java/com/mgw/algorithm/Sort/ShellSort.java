package com.mgw.algorithm.Sort;

import com.mgw.algorithm.Sort.helper.SortHelper;

import java.util.Arrays;

public class ShellSort implements ISort {
    @Override
    public <T extends Comparable> void sort(T[] arr) {


        int length = arr.length;

        // 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
        int h = 1;

        //找出shell值
        while (h < length/3) {

            h = 3*h +1;
        }

        while (h>=1) {

            for (int i = h; i < length ;i++) {

                // 对 arr[i], arr[i-h], arr[i-2*h], arr[i-3*h]... 使用插入排序
                T t = arr[i];
                int j = i;

                //当前元素与其同组的前一个元素进行比较
                for (;j>=h && arr[j-h].compareTo(t) > 0 ; j -= h) {

                    arr[j] = arr[j-h];

                }
                arr[j] = t;

            }

            h/=3;
        }

    }

    public <T extends Comparable> void sort1(T[] arr) {


        int length = arr.length;

        // 计算 increment sequence: 1, 4, 13, 40, 121, 364, 1093...
        int h = 1;

        //找出shell值
        while (h < length / 3) {

            h = 3 * h + 1;
        }

        while (h >= 1) {

            for (int i = h; i < length ;i++) {

            }

        }
    }


    public static void main(String[] args) throws Exception{
        int n = 100;

        Integer[] ints = SortHelper.generateArray(n, 0, 100);

        ISort shellSort = new ShellSort();
        shellSort.sort(ints);

        if (!SortHelper.isSorted(ints)) {
            throw new Exception("排序错误");
        }

        System.out.println(Arrays.toString(ints));

    }
}
