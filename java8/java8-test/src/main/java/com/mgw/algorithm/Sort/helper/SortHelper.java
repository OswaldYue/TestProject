package com.mgw.algorithm.Sort.helper;

import java.util.Arrays;
import java.util.Random;

public class SortHelper {


    public static Integer[] generateArray(int n,int rangL,int rangR) {

        Integer[] arr = new Integer[n];

        Random random = new Random();
        for (int i = 0; i < n; i++) {

            arr[i] = random.nextInt(rangR)%(rangR-rangL+1) + rangL;
        }

        return arr;
    }

    public static Integer[] generateNealyOrderArray(int n,int swapTimes) {

        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {

            arr[i] = i;
        }

        Random random = new Random();
        for (int i = 0; i < swapTimes; i++) {

            int randomx = random.nextInt(n) ;
            int randomy = random.nextInt(n) ;

            swap(arr,randomx,randomy);
        }
        
        return arr;
    }

    public static <T> void swap(T[] arr,int sIndex,int tIndex) {

        T t = arr[sIndex];

        arr[sIndex] = arr[tIndex];
        arr[tIndex] = t;

    }

    public static <T extends Comparable> boolean isSorted(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {

            if (arr[i].compareTo(arr[i+1]) > 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

//        int n = 100;
//        int[] arr = new int[n];
//
//        Random random = new Random();
//
//        for (int i = 0; i < n; i++) {
//
//            arr[i] = random.nextInt(100);
//
//        }
//
//        System.out.println(Arrays.toString(arr));
        Integer[] integers = generateNealyOrderArray(100, 10);
        System.out.println(Arrays.toString(integers));
    }
    
}
