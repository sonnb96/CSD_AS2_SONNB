public class MyList<T> {

    /**
     * Head node, default is null
     */
    Node<T> head;

    /**
     * Tail node, default is null
     */
    Node<T> tail;

    /**
     * Default constructor
     */
    public MyList() {
        this.head=null;
        this.tail=null;
    }

    /**
     * Constructor with head and tail
     *
     * @param head Head node of this list
     * @param tail Tail node of this list
     */
    public MyList(Node head, Node tail) {
        this.head=head;

        this.tail=tail;

        this.head.next=this.tail;


    }

    /**
     * Checking if this list is empty
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return this.head==null;
    }

    /**
     * Returning the length of this list
     *
     * @return The length of this list
     */
    public int length() {
        if(this.head==null){
            return -1;
        }
        Node current= this.head;
        int len=0;
        while (current!=null){
            len++;
            current=current.next;
        }
        return len;
    }

    /**
     * Insert an item to the head of this list
     *
     * @param item The item to be inserted
     */
    public void insertToHead(T item) {

        Node newNode= new Node<T>(item, head);
        if(isEmpty()){
            tail=newNode;
        }
        head=newNode;
    }

    /**
     * Insert an item to the last of this list
     *
     * @param item The item to be inserted
     */
    public void insertToTail(T item) {

        Node newNode= new Node<T>(item, null);
        if(isEmpty()){
            head=tail=newNode;
        }else {
            tail.next=newNode;
            tail=newNode;
        }

    }
    /**
     * Insert an item at position to this list
     *
     * @param position The position of new item
     * @param item     The item to be inserted
     */
    public void insertAfterPosition(int position, T item) {
        if(position==0){
            insertToHead(item);
        }else{
            if(this.head==null){
                return;
            }
            if(position>length()){
                return;
            }
            Node currentNode=this.head;
            int count=0;
            while (currentNode!=null){
                if(count==position){
                    Node newNode= new Node(item,currentNode.next);
                    currentNode.next=newNode;
                    break;
                }
                currentNode=currentNode.next;
                count++;
            }
        }
    }

    /**
     * Deleting the tail of this list
     */
    public void deleteTail() {
        if(isEmpty()){
            return;
        }
        if(head.next==null){
            head=tail=null;
        }else {
            tail=null;
            Node currentNode =this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            tail=currentNode;
        }
        System.out.println("Successfully!");
    }

    /**
     * Searching and deleting an item from this list by comparing the ID of items
     *
     * @param item The item to be deleted
     */
//    public void deleteElement(T item) {
//        if(isEmpty()){
//            return;
//        }
//        Node currentNode=this.head;
//        while (currentNode!=null){
//            if(){
//
//            }
//            currentNode=currentNode.next;
//        }
//    }

    /**
     * Swaping two nodes [firstNode] and [secondNode]
     *
     * @param parX node parent of X
     * @param parY node parent of X
     * @param curX node child of X
     * @param curY node child of Y
     */
    public void swap(Node<T> parX, Node<T> curX, Node<T> parY, Node<T> curY) {

       if(parX!=null){
           parX.next=curY;
       }else{
           head=curY;
       }

       if(parY!=null){
           parY.next=curX;
       }else{
           head=curX;
       }
       Node<T> temp= curX.next;
       curX.next=curY.next;
       curY.next=temp;

    }



    /**
     * Deleting all items in the list
     */
    public void clear() {
        head=tail=null;

    }


    }



