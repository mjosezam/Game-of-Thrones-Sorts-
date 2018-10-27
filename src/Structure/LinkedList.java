package Structure;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LinkedList<T> {
        private Node<T> head;
        private int size;
        public T data;

        /**
         * Lista enlazada simple
         */
        public LinkedList() {
            this.head = null;
            this.size = 0;
        }

        public int getSize() {
            return size;
        }


        /**
         * agregar elemento
         * @param data
         */
        public void append(T data) {
            Node<T> node = new Node(data);
            Node<T> tmp = this.head;
            if (this.head == null) {
                this.head = node;
                this.size++;
            }
            else {
                while (tmp.getNext() != null) {
                    tmp = tmp.getNext();
                }
                tmp.setNext(node);
                node.setNext(null);
                this.size++;
            }
        }

        /**
         *Se busca nodo por medio de un incdice
         * @param index
         * @return nodo
         */
        public Node<T> getNode(int index){
            Node<T> current = head;
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
    }

