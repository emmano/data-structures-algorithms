package datastructures.lists;

public class LinkedList<T> {

    /**
     * Simple LinkedList implementation.
     */
    private Node<T> head;
    private Node<T> tail;
    private int size;


    public LinkedList() {
        head = tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addEnd(T value) {
        Node<T> newNode = new Node<T>();
        newNode.object = value;
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void addFirst(T value) {
        Node<T> newNode = new Node<T>();
        newNode.object = value;

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    public boolean remove(T t) {

        if (size == 0) {
            throw new UnsupportedOperationException("Trying to remove from an empty list");
        }

        Node<T> current = head;
        Node<T> previous = null;

        if (current.object.equals(t)) {
            head = head.next;
            size--;
            return true;
        }

        while (current.next != null && !current.object.equals(t)) {
            previous = current;
            current = current.next;
        }

        if(previous == null){
            return false;
        }

        if(!current.object.equals(t)){
            return false;
        }


        previous.next = current.next;
        tail = previous;
        size--;
        return true;
    }

    public Node<T> get(int position) {

        if (position < 0 || position >= size) {
            throw new IndexOutOfBoundsException(String.format("List size is %s. You attempted to access position %s", size, position));
        }

        if (position == 0) {
            return head;

        } else if (position == size - 1) {
            return tail;
        } else {
            Node<T> tempNode = head;
            for (int i = 0; i < position; i++) {
                tempNode = tempNode.next;
            }
            return tempNode;
        }
    }

    public void recursivePrint() {
        if (!isEmpty()) {
            head.recursive();
        }
    }

    public void reverseList() {
        Node<T> current = tail = head;
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
            if (next != null)
                next.recursive();
        }
    }

}