package datastructures.lists;


public class Stack<T> {

    /**
     * Simple Stack implementation based on a LinkedList to which we add items to the front.
     * We could have used a simple LinkedList to implement the Stack, but this was re-implemented for practice.
     */

    private Node<T> head;
    private int size;

    /**
     * Here we create the fist Node. Its next variable is set to null. This will help us when looping through the stack;
     * it lets us know the we reached the end.
     */
    public Stack() {
        head = new Node<T>();
        head.next = null;
    }

    public int size() {
        return size;
    }

    /**
     * We create a new Node and set the given value to its object variable. Then we set the new node's next variable to
     * point to the Node that is at the front of the list; head. Lastly, we update head to point to the new Node, so the
     * new Node is the first one on the list.
     *
     * Now let's consider what exactly happens when we call push():
     *
     *  1. A variable called newNode is created and stored on the stack, but it points to a memory reference on the heap.
     *     This variable will hold references to Objects of type Node.
     *  2. We set the new Node's object variable to the value given to push().
     *  3. Then we set newNode's next variable to point to the Node that is currently at the front of the list.
     *  4. After we do this we update head to point to the newly pushed object so it is at the front of the list now.
     *
     * In order to make this more clear let's say that head started pointing to memory location 0x0, and the newly
     * created node point to memory location 0x1.
     *
     * At the end of push() (just before it returns) our memory allocations should look like this. Sometime after push()
     * returns, newNode will be poped off the stack, and it will be no longer accessible.
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
     * @param value Item to push to the stack.
     */
    public void push(T value) {
        Node<T> newNode = new Node<T>();
        newNode.object = value;
        newNode.next = head;
        head = newNode;
        size++;
    }

    public boolean empty() {
        return size == 0;
    }

    public int search(T t) {
        Node<T> current = head;
        int offset = 0;
        while (current.next != null && !current.object.equals(t)) {
            current = current.next;
            offset++;
        }
        if (!current.object.equals(t)) {
            return -1;
        } else {
            return offset;
        }
    }

    public T pop() {

        if (empty()) {
            throw new UnsupportedOperationException("Trying to pop from empty Stack");
        }
        Node<T> current = head;
        head = head.next;
        size--;
        return current.object;
    }

    public T peek() {
        return head.object;
    }

    public void recursivePrint() {
        head.recursive();
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
