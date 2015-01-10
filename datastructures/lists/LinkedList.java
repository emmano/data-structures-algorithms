package datastructures.lists;

public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        head = new Node<T>();
        head.next = null;
    }

    public int size() {
        return size;
    }

    public void add(T value) {
        Node<T> newNode = new Node<T>();
        newNode.object = value;
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void remove(T t) {
        Node<T> current = head;
        Node<T> previous = null;

        while (!current.object.equals(t)) {
            previous = current;
            current = current.next;
        }

        if(previous == null){
            return;
        }

        previous.next = current.next;
        size--;
    }

    public T get(int position) {

        if (position >= size) {
            throw new IndexOutOfBoundsException(String.format("List size is %s. You attempted to access position %s", size, position));
        }

        Node<T> tempNode = head;

        if (position == 0) {
            return head.object;
        } else {
            for (int i = 0; i < position; i++) {
                tempNode = tempNode.next;
            }
            return tempNode.object;
        }
    }

    public void recursivePrint(){
        head.recursive();
    }

    public void reverseList() {
        Node<T> current = head;
        Node<T> previousNode = null;
        Node<T> nextNode;

        while (current != null) {
            nextNode = current.next;
            current.next = previousNode;
            previousNode = current;
            current = nextNode;
        }

        head = previousNode;
    }

    private static class Node<T> {
        public Node<T> next;
        public T object;

        public void recursive() {
            System.out.println(object.toString());
            if (next.next != null)
                next.recursive();
        }
    }

}