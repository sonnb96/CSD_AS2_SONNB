public class Node<T> {

    /**
     * The info of this node
     */
    T info;

    /**
     * The next node
     */
    Node next;

    /**
     * Default constructor
     */
    public Node() {

        this.next=null;
    }

    /**
     * Constructor with info and next node
     *
     * @param info The info of this node
     * @param next The next Node of this node
     */
    public Node(T info, Node next) {
        this.info=info;
        this.next= next;
    }

    /**
     * Overriding to convert this node to String
     */
//    @Override
//    public String toString() {
//
//    }

}
