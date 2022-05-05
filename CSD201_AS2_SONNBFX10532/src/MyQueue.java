import java.util.EmptyStackException;

/**
 * Generic version of the Queue class.
 *
 * @param <T> the type of the value
 */
class MyQueue<T> {

    /**
     * Head node contains front node in the queue
     */
    Node<T> head;

    /**
     * Tail node contains last node in the queue
     */
    Node<T> tail;

    public boolean isEmpty(){
        return head==null;
    }
    public void enqueue(T item){

        Node<T> newNode= new Node<T>(item,null);
        if(isEmpty()){
            head=tail=newNode;
        }else {
            tail.next=newNode;
            tail=newNode;
        }



    }
    public T dequeue(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T value= head.info;
        head=head.next;
        if (head==null){
            tail=null;
        }
        return value;

    }
    public T peek(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T value= head.info;

        return value;

    }


}
