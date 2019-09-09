package com.mgw.algorithm.Sort;

import com.mgw.algorithm.Sort.helper.SortHelper;

import java.util.Arrays;

public class TestSort {


    public <T extends Comparable> void testSort(String name, T[] arr, ISort sort) throws Exception{

        long beginTime = System.nanoTime();

        sort.sort(arr);

        long endTime = System.nanoTime();


        if (!SortHelper.isSorted(arr)) {
            throw new Exception(name + " 发生错误.");
        }

        double time = (endTime - beginTime)/100000000.0 ;

        System.out.println(name + "使用了"+ time + "s");
    }


    public static void main(String[] args) throws Exception {

        int n = 10000;

//        Integer[] ints = SortHelper.generateArray(n, 0, n);
        Integer[] ints = SortHelper.generateNealyOrderArray(n,100);
        Integer[] ints2 = Arrays.copyOf(ints, n);
        Integer[] ints3 = Arrays.copyOf(ints, n);

        TestSort testSort = new TestSort();
        testSort.testSort("selectSort",ints,new SelectSort());
        testSort.testSort("bubbleSort",ints2,new BubbleSort());
        testSort.testSort("insertSort",ints2,new InsertSort());


    }


}
