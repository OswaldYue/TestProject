package com.mgw.leetcode;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * */
public class Problem3 {

    public int lengthOfLongestSubstring(String s) {


        List<Integer> maxLenth = new ArrayList<>();
        List<Character> chars = new ArrayList<>();
//        Map<Integer,List<Character>> map = new HashMap<>();

        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);

            if (chars.contains(c)) {
                maxLenth.add(chars.size());

                //map.put(chars.size(),chars);

                chars.clear();
                chars.add(c);
            }else {
                chars.add(c);
            }
        }

        int max = 0;
        for (int i = 0; i < maxLenth.size(); i++) {

            if (maxLenth.get(i) > max) {
                max = maxLenth.get(i);
            }
        }
//        Set<Integer> keys = map.keySet();
//        for (Integer key : keys) {
//
//            System.out.println("key = " + key + ",value = " + map.get(key));
//
//        }


        return max;
    }

    /**
     * 其他解法:滑动窗口
     *
     * */
    public int lengthOfLongestSubstring2(String s) {

        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                //重复的抛掉[i,j')之间的值
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    /**
     * 滑动窗口优化
     * */
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;

        // current index of character
        Map<Character, Integer> map = new HashMap<>();
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                //当[i,j)区间有重复时j'时,直接抛掉[i,j')区间,让i=j'+1
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }



    public static void main(String[] args) {

        String s = "pwawkew";

        Problem3 problem3 = new Problem3();
        int i = problem3.lengthOfLongestSubstring3(s);

        System.out.println(i);


    }


}
