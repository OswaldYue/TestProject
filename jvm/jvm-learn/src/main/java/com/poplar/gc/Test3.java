package com.poplar.gc;

/**
 * 内存泄漏：集合数据管理不当
 *
 * 当使用Array-based的数据结构(ArrayList,HashMap等)时，尽量减少resize
 *  比如:new ArrayList时，尽量估算size，尽量在创建的时候把size确定
 *  减少resize可以避免没有必要的array copying,gc碎片等问题
 *
 * 如果一个List只需要顺序访问，不需要要随机访问(Random Access)，用LinkedList代替ArrayList
 *  LinkedList本质是链表，不需要resize，但只适用于顺序访问
 * */
public class Test3 {
}
