package Structure;

public class Node<T> {
    public Node next;
    private T data;
    Object  key, height;
    Node<T> left, right;


    public Node(T data) {
        this.next=null;
        this.data = data;
        key = data;
        height = 1;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
