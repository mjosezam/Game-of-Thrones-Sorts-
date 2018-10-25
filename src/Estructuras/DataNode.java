package Estructuras;

public class DataNode {
    DataNode next;
    Object data;

    public DataNode(Object data){
        this.data=data;
        this.next=null;
    }

    public DataNode getNext() {
        return next;
    }

    public void setNext(DataNode next) {
        this.next = next;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

