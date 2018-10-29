package Trees;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso({Node.class, ServerDragons.class, SimpleList.class})
public class LinkedlistIS<T>
{
    LinkedlistIS dragons = new LinkedlistIS();
    public Node head, sorted, root;
    int size;


    public LinkedlistIS(){
        this.head=null;
        this.size=0;
    }

    public LinkedlistIS validate_parent(LinkedlistIS dragons){
        ServerDragons server = new ServerDragons();
        for (int i = 0; i < dragons.getSize(); i++) {
            if (dragons.getNode(i).getParent()==null){
                dragons.getNode(i).setParent(server.setparent());
            }
        }
        return dragons;
    }


    public Node<T> getNode(int index){
        Node<T> current = head;
        if (index < size) {
            for (int j = 0; j < size; j++) {
                if (index == j) {
                    return current;
                } else {
                    current = current.getNext();
                }
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void push(Node newnode) {
        newnode.setNext(head);
        head = newnode;
        size++;
    }


    // function to sort a singly linked list using insertion sort
    public void insertionSort(Node headref) {
        // Initialize sorted linked list
        sorted = null;
        Node current = headref;
        // Traverse the given linked list and insert every
        // node to sorted
        while (current != null)
        {
            // Store next for next iteration
            Node next = current.getNext();
            // insert current in sorted linked list
            sortedInsert(current);
            // Update current
            current = next;
        }
        // Update head_ref to point to sorted linked list
        head = sorted;
    }

    void sortedInsert(Node newnode){
        /* Special case for the head end */
        if (sorted == null || (int) sorted.getSpeed() >= (int) newnode.getSpeed())
        {
            newnode.setNext(sorted);
            sorted = newnode;
        }
        else
        {
            Node current = sorted;
            /* Locate the node before the point of insertion */
            while (current.getNext() != null && (int) current.getNext().getSpeed() < (int) newnode.getSpeed())
            {
                current = current.getNext();
            }
            newnode.setNext(current.getNext());
            current.setNext(newnode);
        }
    }

    void printlist(Node head){
        while (head != null)
        {
            System.out.print(head.getAge() + " ");
            head = head.getNext();
        }
    }


    Node lastNode(Node node){
        while(node.getNext()!=null)
            node = node.getNext();
        return node;
    }

    Node partition(Node l, Node h)    {
        // set pivot as h element
        int x = (int) h.getAge();

        // similar to i = l-1 for array implementation
        Node i = l.getPrev();

        // Similar to "for (int j = l; j <= h- 1; j++)"
        for(Node j = l; j!=h; j=j.getNext())
        {
            if((int) j.getAge() <= x)
            {
                // Similar to i++ for array
                i = (i==null) ? l : i.getNext();
                int temp = (int) i.getAge();
                i.setAge(j.getAge());
                j.setAge(temp);
            }
        }
        i = (i==null) ? l : i.getNext();  // Similar to i++
        int temp = (int) i.getAge();
        i.setAge(h.getAge());
        h.setAge(temp);
        return i;
    }

    void _quickSort(Node l, Node h) {
        if(h!=null && l!=h && l!=h.getNext()){
            Node temp = partition(l,h);
            _quickSort(l,temp.getPrev());
            _quickSort(temp.getNext(),h);
        }
    }



    public void quickSort(Node node){
        // Find last node
        Node head = lastNode(node);

        // Call the recursive QuickSort
        _quickSort(node,head);
    }

    public void selectionSort(Node head) {
        for (Node node1 = head; node1 != null; node1 = node1.getNext()) {
            Node min = node1;
            for (Node node2 = node1; node2 != null; node2 = node2.getNext()) {
                if (min.getAge() > node2.getAge()) {
                    min = node2;
                }

            }
            Node temp = new Node(node1.getSpeed(),node1.getAge(),node1.getResistance(), node1.getClasse(), node1.getName(), node1.getParent());
            node1.setAge(min.getAge());
            min.setAge(temp.getAge());
        }
    }




    int height(Node N) {
        if (N == null)
            return 0;

        return (int) N.getHeight();
    }


    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.getLeft();
        Node T2 = x.getRight();

        // Perform rotation
        x.setRight(y);
        y.setLeft(T2);

        // Update heights
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.getRight();
        Node T2 = y.getLeft();

        // Perform rotation
        y.setLeft(x);
        x.setRight(T2);

        //  Update heights
        x.setHeight(max(height(x.getLeft()), height(x.getRight())) + 1);
        y.setHeight(max(height(y.getLeft()), height(y.getRight())) + 1);

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.getLeft()) - height(N.getRight());
    }


    public LinkedlistIS insertarAux(LinkedlistIS dragon) {
        for (int i = 0; i == dragon.size; i++) {
            insert(dragon.root, dragon.getNode(i).getSpeed(), dragon.getNode(i).getAge(),
                    dragon.getNode(i).getResistance(), dragon.getNode(i).getName(), dragon.getNode(i).getClasse(),dragon.getNode(i).getParent());
        }
        return preorderTraverseTree(dragon.root);
    }

    public LinkedlistIS preorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            dragons.push(focusNode);
            preorderTraverseTree(focusNode.getLeft());
            preorderTraverseTree(focusNode.getRight());
        }
        return dragons;
    }

    //AVL
    public Node insert(Node node, int speed, int age, int resistance, String classe, String name, Node parent) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node(speed,age,resistance,classe,name, parent));

        if (age < node.getAge())
            node.setLeft(insert(node.getLeft(), speed,age,resistance,classe,name, parent));
        else if (age > node.getAge())
            node.setRight(insert(node.getRight(), speed,age,resistance,classe,name, parent));
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.setHeight(1 + max(height(node.getLeft()),
                height(node.getRight())));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && age < node.getLeft().getAge())
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && age > node.getRight().getAge())
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && age > node.getLeft().getAge()) {
            node.setLeft(leftRotate(node.getLeft()));
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && age < node.getRight().getAge()) {
            node.setRight(rightRotate(node.getRight()));
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node




    // Driver program to test above functions
    public static void main(String[] args)
    {
        LinkedlistIS tree = new LinkedlistIS();
        /**Node node1= new Node(10,69,8,"com","a1",tree.);
         //Node node2= new Node(30,2,8,"com","a2");
         //Node node3= new Node(1,1,8,"com","a3");
         tree.root = tree.insert(tree.root, node1.speed,node1.age,node1.resistance,node1.classe,node1.name, node2);
         tree.root = tree.insert(tree.root, node2.speed,node2.age,node2.resistance,node2.classe,node2.name, node3);
         tree.root = tree.insert(tree.root, node3.speed,node3.age,node3.resistance,node3.classe,node3.name, node1);
         System.out.println("Preorder traversal" +
         " of constructed tree is : ");
         tree.preOrder(tree.root);**/

    }
}

// This code is contributed by Rishabh Mahrsee

