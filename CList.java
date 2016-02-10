/**
 * Doubly linked circular List implementation of the List interface.
 * Uses an inner Node class.
 *
 * This version differs notably from the DSA version in that the curr 
 * data member refers to the node *before* the cursor, whereas in OpenDSA
 * the curr data member refers to the node *after* the cursor.
 *
 * @author Lawrence Wolf-Sonkin & Sidney Jackson
 * @param <T> the type of the List
 */

class CList<T> implements List<T> {
    /**
     * Inner doubly linked circular Node class for convenience.
     * Note that the generic type is implied since we are within CList<T>.
     */
    public class Node {

        /** The data in the element. */
        private T data;
        /** The left neighbor node. */
        private Node prev;
        /** The right neighbor node. */
        private Node next;

        /**
         * Make a node.
         * @param item the data to put in it
         * @param p the link to the previous node
         * @param n the link to the next node
         */
        public Node(T item, Node p, Node n) {
            this.data = item;
            this.prev = p;
            this.next = n;
    }
    /** Head node. */
    private Node head;
    /** Tail node. */
    private Node tail;
    /** Number of actual data nodes in list. */
    private int size;
    /** Current node (think of as a cursor between nodes). */
    private Node curr;





    /**
     * Create an empty list.
     */
    public CList() {
        this.clear();  // code reuse!
    }
    
    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        this.head = null;
        this.tail = null;
        this.curr = null;  // because insert will insert after curr
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        if (this.size == 0) {
            Node n = new Node(t, null, null);
            n.prev = n;
            n.next = n;
            this.head = n;
            this.tail = n;
            this.curr = n;
            this.size = 1;
            
        } else if (this.size == 1) {
            
        }
         else {
            Node n = new Node(t, this.curr, this.curr.next);
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            this.size++;
            return true;

        }
    }

    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        Node temp = this.curr;        // hold onto original position
        this.curr = this.tail.prev;   // move to before the tail sentinel
        this.insert(t);               // code reuse!
        this.curr = temp;             // restore cursor to original position
        return true;
    }

    /**
     * Remove and return the current element (one to right of cursor). 
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        if (this.curr.next == this.tail) {
            return null;
        }
        T val = this.curr.next.data;
        this.curr.next = this.curr.next.next;  // bypass node being deleted
        this.curr.next.prev = this.curr;       // bypass it in other direction
        this.size--;
        return val;
    }

    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue() {
        if (this.curr.next == this.tail) {
            return null;
        }
        return this.curr.next.data;
    }

    /**
     * Return the number of elements in the list.
     * @return the length of the list
     */
    public int length() {
        return this.size;
    }

    /* ---------- METHODS BELOW THIS LINE ARE NOT IMPLEMENTED ------------ */

    /**
     * Set the current position to the start of the list.
     */
    public void moveToStart() {
    }

    /**
     * Set the current position to the end of the list.
     */
    public void moveToEnd() {
    }

    /**
     * Move the current position one step left,
     * no change if already at beginning.
     */
    public void prev() {
    }

    /**
     * Move the current position one step right, no change if already at end.
     */
    public void next() {
    }

    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    public int currPos() {
        // dummy implementation
        return 0;
    }

    /**
     * Set the current position.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int pos) {
        // dummy implementation
        return false;
    }

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list
     */
    public boolean isAtEnd() {
        // dummy implementation
        return false;
    }



}