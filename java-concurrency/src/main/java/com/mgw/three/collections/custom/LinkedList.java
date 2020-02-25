package com.mgw.three.collections.custom;

public class LinkedList<E> {

    private static final String PLAIN_NULL = "null";

    private Node<E> first;

    private final Node<E> NULL = (Node<E>) null;

    private int size;

    public LinkedList() {
        this.first = NULL;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {

        return (size() == 0);
    }

    public static <E> LinkedList<E> of(E ...elements) {

        LinkedList<E> list = new LinkedList<>();
        if (elements.length != 0) {
            for (E e:elements) {
                list.addFirst(e);
            }
        }
        return list;
    }

    public LinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        newNode.next = first;
        this.size++;
        this.first = newNode;

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
            throw new NoElementException("The linked list is empty");
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
        final LinkedList<String> list = LinkedList.of("Hello", "Java", "Scale");
        list.addFirst("C").addFirst("C++");

        System.out.println(list.size());
        System.out.println(list.contains("Scale"));
        System.out.println(list);

        while (!list.isEmpty()) {
            System.out.println(list.removeFirst());
        }
        System.out.println(list.size());
    }

}
