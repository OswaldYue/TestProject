package com.mgw.leetcode;


import com.mgw.leetcode.helpclass.ListNode;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 *
 * */
public class Problem2 {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {


         return null;
    }

    public static void main(String[] args) {

        int[] arr = {2,4,3};
        int[] arr2 = {5,6,4};
        ListNode l1 = new ListNode(arr);
        ListNode l2 = new ListNode(arr2);

        Problem2 problem2 = new Problem2();
        problem2.addTwoNumbers(l1,l2);


    }


}

