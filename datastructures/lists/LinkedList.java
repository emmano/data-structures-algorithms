package datastructures.lists;

public class LinkedList<T> {

    /**
     * Simple LinkedList implementation. Note that this implementation adds new Nodes to the front of the list.
     */

    private Node<T> head;
    private int size;
    /**
        Here we create the fist Node. Its next variable is set to null. This will help us when looping through the list;
        it lets us know the we reached the end of the list.
    */
    public LinkedList() {
        head = new Node<T>();
        head.next = null;
    }

    public int size() {
        return size;
    }

    /**
     * We create a new Node and set the given value to its object variable. Then we set the new node's next variable to
     * point to the Node that is at the front of the list; head. Lastly, we update head to point to the new Node, so the
     * new Node is the first on the list.
     *
     * Now let's consider what exactly happens when we call add():
     *
     *  1. A variable called newNode is created and stored on the stack, but it points to a memory reference on the heap.
     *     This variable will hold references to Objects of type Node.
     *  2. We set the new Node's object variable to the value given to add().
     *  3. Then we set newNode's next variable to point to the Node that is currently at the front of the list.
     *  4. After we do this we update head to point to the newly added object so it is at the front of the list now.
     *
     * In order to make this more clear let's say that head started pointing to memory location 0x0, and the newly
     * created node point to memory location 0x1.
     *
     * At the end of add() (just before it returns) our memory allocations should look like this. Sometime after add()
     * returns, newNode will be garbage collected.
     *
     *
     *         STACK      |         HEAP
     *                    |
     *         -----------|-------->v0x1
     *        |           |           ^     ----
     *        |           |           |   |     |
     *     newNode.next --|--       head.next   |
     *                    |  |                  |
     *                    |  |                  |
     *                    |   ------>0x0 <------
     *                    |
     *
     * You might get confused with these lines of code:
     *
     *  newNode.next = head;
     *  head = newNode;
     *
     * If we change where head points to after we do newNode.next = head, will newNode.next point to newNode? You might ask.
     *
     * Let's remember that variables hold references to memory locations. When we do newNode.next = head we are
     * effectively setting newNode.next to point to memory location 0x0; the current memory location the variable head points to.
     * This does not mean that making head point to another memory location will affect where newNode.next points to.
     * When we do head = newNode, head now points to memory location 0x1; newNode.next still points to 0x0.
     *
     * In this case, we should think of variables as actors on a dark stage. When the light of the theater points (pun intended) to the
     * actor, we can see the actor and maybe interact with him (throw something at him if he is bad, etc).
     * If the light points to another actor on the stage we cannot longer see what the previous actor is doing, but he
     * is still there and probably is not doing the same things as the new actor.
     *
     * Variables are just a means for us to interact with memory locations and setting one variable to another does not
     * mean that these two variables are tied when it comes to changing where one of them points to.
     * They will, though, see changes made to the memory location they are both attached to as we will see next
     *
     * With all that said, here is time for the curve ball. If we were to do newNode.next.object = "test" after we do
     * newNode.next = head and before head = newNode, head will see this change. This means that head.object will be "test"
     * instead of the original null. Please note that this is not the same as changing where either head or newNode.next
     * are pointing to, in fact at this point they both point to memory location 0x0. This is the reason why head will
     * see the change; we are modifying the memory location they both point to via one of the two variables that have a reference of this location.
     *
     * @param value Item to add to the list
     */
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

        if (previous == null) {
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

    public void recursivePrint() {
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