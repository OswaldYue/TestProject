package com.mgw.three.collections.custom;

/**
 * 非线程安全的自写queue
 * */
public class MyQueue<E> {

    private static class Node<E> {
        private E element;
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return (element==null)?"":element.toString();
        }
    }

    private Node<E> first,last;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public E peekFirst() {
        return (first == null) ? null : first.getElement();
    }

    public E peekLast() {
        return (last == null) ? null : last.getElement();
    }

    public void addLast(E element) {
        Node<E> newNode = new Node<>(element, null);
        if (size == 0) {
            first = newNode;
        }else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    public E removeFirst() {
        if (isEmpty()) {
            return null;
        }
        E element = first.getElement();
        first = first.next;
        size--;
        if (size==0) {
            last = null;
        }


        return element;
    }

    public static void main(String[] args) {
        MyQueue<String> myQueue = new MyQueue();

        myQueue.addLast("A");
        myQueue.addLast("B");
        myQueue.addLast("C");
        myQueue.addLast("D");

        System.out.println(myQueue.removeFirst());
        System.out.println(myQueue.removeFirst());
        System.out.println(myQueue.removeFirst());
        System.out.println(myQueue.removeFirst());

    }
}
