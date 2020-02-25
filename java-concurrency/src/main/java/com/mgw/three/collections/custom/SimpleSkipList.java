package com.mgw.three.collections.custom;

import java.util.Random;

public class SimpleSkipList {

    private static final byte HEAD_BIT = (byte)-1;
    private static final byte DATA_BIT = (byte)0;
    private static final byte TAIL_BIT = (byte)1;

    private Node head;
    private Node tail;

    private int size;
    private int height;
    private Random random;

    public SimpleSkipList() {
        this.head = new Node(null,HEAD_BIT);
        this.tail = new Node(null,TAIL_BIT);

        head.right = tail;
        tail.left = head;
        this.random = new Random(System.currentTimeMillis());
    }

    public boolean isEmpty() {
        return (size() == 0);
    }

    public int size() {
        return this.size;
    }

    private Node find(Integer element) {
        Node current = head;
        for (;;) {
            while (current.right.bit != TAIL_BIT && current.right.value <= element) {
                current = current.right;
            }

            if (current.down != null) {
                current = current.down;
            }else {
                break;
            }
        }

        return current; // the current <= the element < current.right(if exist)
    }

    public boolean contains(Integer element) {
        Node node = this.find(element);
        return (node.value == element);
    }

    public Integer get(Integer element) {
        Node node = this.find(element);
        return (node.value==element) ? node.value : null;
    }

    public void add(Integer element) {
        Node nearNode = this.find(element);
        Node newNode = new Node(element);

        newNode.left = nearNode;
        newNode.right = nearNode.right;
        nearNode.right.left = newNode;
        nearNode.right = newNode;

        int currentLevel = 0;
        while (random.nextDouble() < 0.2d) {
            // 向上提一层
            if (currentLevel >= height) {
                
                height++;
                Node dumyHead = new Node(null, HEAD_BIT);
                Node dumyTail = new Node(null,TAIL_BIT);

                dumyHead.right = dumyTail;
                dumyHead.down = head;
                head.up = dumyHead;

                dumyTail.left = dumyHead;
                dumyTail.down = tail;
                tail.up = dumyTail;

                head = dumyHead;
                tail = dumyTail;
            }
            

            while ((nearNode != null) && nearNode.up == null ) {
                nearNode = nearNode.left;
            }
            nearNode = nearNode.up;
            Node upNode = new Node(element);
            upNode.left = nearNode;
            upNode.right = nearNode.right;
            upNode.down = newNode;

            nearNode.right.left = upNode;
            nearNode.right = upNode;

            newNode.up = upNode;
            newNode = upNode;
            currentLevel++;
        }

        size++;
    }


    public void dumpSkipList() {
        Node temp = head;
        int i = height+1;
        while (temp != null) {
            System.out.printf("Total [%d] height [%d]",height+1,i--);
            Node node = temp.right;
            while(node.bit == DATA_BIT) {
                System.out.printf("->%d ",node.value);
                node = node.right;
            }
            System.out.printf("\n");
            temp = temp.down;
        }
    }


    private static class Node {
        private Integer value;
        private Node up,down,left,right;
        private byte bit;

        public Node(Integer value,byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value,DATA_BIT);

        }

    }

    public static void main(String[] args) {

        Random random = new Random();

        SimpleSkipList list = new SimpleSkipList();
        for(int i = 0 ; i < 100 ;i++) {
            list.add(random.nextInt(1000));
        }
        list.dumpSkipList();
    }
}
