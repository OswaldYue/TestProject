package com.mgw.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 *
 * */
public class Problem1 {

    public int[] twoSum(int[] nums, int target) {

        int[] ret = new int[2];

        for (int i = 0; i < nums.length-1; i++) {

            for (int j = i+1;j < nums.length;j++) {
                if (nums[i] + nums[j] == target) {
                    ret[0] = i;
                    ret[1] = j;

                    break;
                }
            }

        }

        return ret;
    }

    public int[] twoSum2(int[] nums, int target) throws Exception{

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] {map.get(complement),i};
            }
            map.put(nums[i],i);

        }

        throw new Exception("没找到");

    }

    public static void main(String[] args) throws Exception{

        int[] nums = {2, 7, 11, 15};
        int target = 9;

        Problem1 solution = new Problem1();
        int[] ret = solution.twoSum2(nums, target);

        System.out.println(Arrays.toString(ret));


        int[] array = {1,2,3,4,5};
        System.out.println(Arrays.toString(array));

    }
}
