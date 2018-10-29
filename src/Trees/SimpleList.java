package Trees;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleList {
    DataNode head;
    int size;

    public SimpleList(){
        this.head=null;
        this.size=0;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public DataNode getNode(int index){
        DataNode current = head;
        if (index < size) {
            for (int j = 0; j < size; j++) {
                if (index == j) {
                    return current;
                } else {
                    current = current.next;
                }
            }
        }
        return null;
    }

    void push(Object data) {
        DataNode newnode = new DataNode(data);
        newnode.next = head;
        head = newnode;
        size++;
    }
}
