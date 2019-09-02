package com.mgw.leetcode.helpclass;

/**
 * Definition for singly-linked list.
 */
public class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    /**
     * 链表节点的构造函数
     * 使用arr为参数 创建一个链表 当前的ListNode为链表头节点
     * */
    public ListNode(int[] arr) {

        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("数组不能为空");
        }

        this.val  = arr[0];
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {

            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }


    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {

            stringBuilder.append(cur.val + "->");
            cur = cur.next;
        }
        stringBuilder.append("NULL");
        return stringBuilder.toString();

    }
}
