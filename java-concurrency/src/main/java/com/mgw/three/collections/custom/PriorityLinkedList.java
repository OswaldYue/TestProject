package com.mgw.three.collections.custom;

public class PriorityLinkedList<E extends Comparable<E>> {

    private static final String PLAIN_NULL = "null";

    private Node<E> first;

    private final Node<E> NULL = (Node<E>) null;

    private int size;

    public PriorityLinkedList() {
        this.first = NULL;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {

        return (size() == 0);
    }

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E ...elements) {

        PriorityLinkedList<E> list = new PriorityLinkedList<>();
        if (elements.length != 0) {
            for (E e:elements) {
                list.addFirst(e);
            }
        }
        return list;
    }

    public PriorityLinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        Node<E> previous = NULL;
        Node<E> current = first;
        while (current != null && e.compareTo(current.value) > 0) {
            previous = current;
            current = current.next;
        }
        if (previous == NULL) {
            first = newNode;
            newNode.next = current;
        }else {
            previous.next = newNode;
            newNode.next = current;
        }

        this.size++;

        return this;
    }

    public boolean contains(E e) {
        Node<E> current = first;
        while (current != null) {
            if (current.value.equals(e)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public E removeFirst() {

        if (this.isEmpty()) {
            throw new LinkedList.NoElementException("The linked list is empty");
        }
        Node<E> temp = first;
        first = temp.next;
        this.size--;

        return temp.value;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        }else {
            StringBuilder builder = new StringBuilder();
            Node<E> current = first;
            builder.append("[");
            while (current != null) {
                builder.append(current.toString()).append(",");
                current = current.next;
            }
            builder.replace(builder.length() -1,builder.length(),"]");
            return builder.toString();
        }
    }

    static class NoElementException extends RuntimeException {
        public NoElementException(String message) {
            super(message);
        }
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if(null != value) {
                return value.toString();
            }
            return PLAIN_NULL;
        }
    }

    public static void main(String[] args) {
        final PriorityLinkedList<Integer> list = PriorityLinkedList.of(10, 1, -10, 0,100, 0, 2, 98, 75);
        System.out.println(list);

    }

}
