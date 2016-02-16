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

        /** The data in the element.
         */
        private T data;
        /** The left neighbor node.
         */
        private Node prev;
        /** The right neighbor node.
         */
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
        /**
         * Copy constructor for Node's.
         * @param  n The Node to copy
         */
        public Node(Node n) {
            this.data = n.data;
            this.prev = n.prev;
            this.next = n.next;
        }
        /**
         * Creates a string representation of a Node.
         * @return String representing Node.
         */
        public String toString() {
            return this.data.toString();
        }

    }
    /** Head node.
     */
    private Node head;
    /** Number of actual data nodes in list.
     */
    private int size;
    /** Current node (think of as a cursor between nodes).
     */
    private Node curr;





    /**
     * Create an empty list.
     */
    CList() {
        this.clear();  // code reuse!
    }
    
    /**
     * Remove all contents from the list, so it is once again empty.
     */
    public void clear() {
        this.size = 0;
        this.head = null;
        this.curr = null;  // because insert will insert after curr
    }

    /**
     * Insert a value at (after) the current location.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to insert
     * @return true if successfully inserted, false otherwise
     */
    public boolean insert(T t) {
        if (this.isEmpty()) {
            Node n = new Node(t, null, null);
            n.prev = n;
            n.next = n;
            this.head = n;
            this.curr = n;
            this.size = 1;

        } else {
            boolean resetHead = this.curr == this.head;
            Node n = new Node(t, this.curr.prev, this.curr);
            n.prev.next = n;   // connect left neighbor
            n.next.prev = n;   // connect right neighbor
            this.size++;
            if (resetHead) {
                this.head = n;
            }
        }
        return true;
    }

    /**
     * Append a value at the end of the list.
     * The client must ensure that the list's capacity is not exceeded.
     * @param t the value to append
     * @return true if successfully appended, false otherwise
     */
    public boolean append(T t) {
        if (this.isEmpty()) {
            this.insert(t);
        } else {
            Node n = new Node(t, this.head.prev, this.head);
            this.head.prev = n;
            n.prev.next = n;
            this.size++;

        }
        return true;
    }

    /**
     * Remove and return the current element (one to right of cursor). 
     * @return the value of the element removed, null if list is empty
     */
    public T remove() {
        if (this.isEmpty()) { // If the list is empty, return null
            return null;
        } else if (this.length() == 1) {
            T val = this.curr.data;
            this.clear();
            return val;
        } else {
            boolean resetHead = this.curr == this.head;
            T val = this.curr.data;
            this.curr.prev.next = this.curr.next;  // bypass node being deleted
            this.curr.next.prev = this.curr.prev;  // bypass it in other direction
            this.size--;
            this.curr = this.curr.next;
            if (resetHead) {
                this.head = this.curr;
            }
            return val;
        }
    }

    /**
     * Return the current element (data to right of cursor).
     * @return the value of the current element, null if none
     */
    public T getValue() {
        if (this.isEmpty()) {
            return null;
        }
        return this.curr.data;
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
        if (!this.isEmpty()) {
            this.curr = this.head; 
        }
    }

    /**
     * Set the current position to the end of the list.
     */
    public void moveToEnd() {
        if (!this.isEmpty()) {
            this.curr = this.head.prev;
        }
        
    }

    /**
     * Move the current position one step left,
     * no change if already at beginning.
     */
    public void prev() {
        if (this.curr != this.head) {
            this.back();
        }
    }

    /**
     * Move the current position one step right, no change if already at end.
     */
    public void next() {
        if (this.curr != this.head.prev) {
            this.cont();
        }
    }

    /**
     * Return the position of the current element.
     * @return the current position in the list
     */
    public int currPos() {
        if (this.isEmpty() || this.length() == 1) {
            return 0;
        }
        Node n = new Node(null, this.head.prev, this.head.next);
        int pos = 0;
        while (n != this.curr) {
            n = n.next;
            pos++;
        }
        return pos;
    }

    /**
     * Set the current position.
     * @param pos the value to set the position to
     * @return true if successfully changed position, false otherwise
     */
    public boolean moveToPos(int pos) {
        if (pos >= 0 && pos < this.size) { //If position (0,size]
            this.curr = this.head; //Reset curr to head before algorithm
            if (pos <= this.size / 2) {
                for (int i = 0; i < pos; ++i) { //Move forward pos times
                    this.cont();
                }
            } else {
                int revNum = this.size - pos;  // move back from head
                for (int i = 0; i < revNum; ++i) { // (this.size - pos) times
                    this.back();
                }
            }
            return true;
        } else {
            return false; //outside acceptable range for a position
        }
    }

    /**
     * Return true if current position is at end of the list.
     * @return true if the current position is the end of the list.
     */
    public boolean isAtEnd() {
        if (this.isEmpty()) {
            return true;
        }
        return this.curr == this.head.prev;
    }

    /**
     * Returns true if list size is zero.
     * @return Returns true if list size is zero.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }
    /**
     * Moves curr to next element, ignoring head-tail boundary.
     */
    public void cont() {
        this.curr = this.curr.next;
    }
    /**
     * Moves curr to back to previous element, ignoring head-tail boundary.
     */
    public void back() {
        this.curr = this.curr.prev;
    }
    /**
     * Creates a String representation of a CList object.
     * @return String representationl of CList
     */
    public String toString() {
        String s = new String();
        s += "[";
        if (!this.isEmpty()) {
            Node n = new Node(this.head);
            Node tail = this.head.prev;
            while (n != tail) {
                s += " " + n;
                n = n.next;
            }
            s += " " + n + " "; //print the tail
        }
        s += "]";
        return s;
    }

}