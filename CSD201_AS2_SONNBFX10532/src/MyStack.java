import java.util.EmptyStackException;

/**
 * Generic version of the Stack class.
 *
 * @param <T> the type of the value
 */
class MyStack<T> {

    /**
     * Head node contains front node in the stack
     */
    Node<T> head;

    public void addStack(T item){
        Node<T> newNode= new Node<>(item,head);
        head=newNode;
    }
    public boolean isEmpty(){
        return head==null;
    }
    public T pop(){
        if(isEmpty()){
            throw new  EmptyStackException();
        }
        T value= head.info;
        head=head.next;
        return value;
    }
    public T top(){
        if(isEmpty()){
            throw new  EmptyStackException();
        }
        T value= head.info;
        return value;
    }

}
