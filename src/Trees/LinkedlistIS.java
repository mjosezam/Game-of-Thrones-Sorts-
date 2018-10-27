package Trees;

public class LinkedlistIS<T>
{
    Node head, sorted, root;
    int size;

    public LinkedlistIS(){
        this.head=null;
        this.size=0;
    }


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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    void push(Node newnode) {
        newnode.next = head;
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
            Node next = current.next;
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
        if (sorted == null || (int) sorted.speed >= (int) newnode.speed)
        {
            newnode.next = sorted;
            sorted = newnode;
        }
        else
        {
            Node current = sorted;
            /* Locate the node before the point of insertion */
            while (current.next != null && (int) current.next.speed < (int) newnode.speed)
            {
                current = current.next;
            }
            newnode.next = current.next;
            current.next = newnode;
        }
    }

    void printlist(Node head){
        while (head != null)
        {
            System.out.print(head.age + " ");
            head = head.next;
        }
    }


    Node lastNode(Node node){
        while(node.next!=null)
            node = node.next;
        return node;
    }

    Node partition(Node l,Node h)    {
        // set pivot as h element
        int x = (int) h.age;

        // similar to i = l-1 for array implementation
        Node i = l.prev;

        // Similar to "for (int j = l; j <= h- 1; j++)"
        for(Node j=l; j!=h; j=j.next)
        {
            if((int) j.age <= x)
            {
                // Similar to i++ for array
                i = (i==null) ? l : i.next;
                int temp = (int) i.age;
                i.age = j.age;
                j.age = temp;
            }
        }
        i = (i==null) ? l : i.next;  // Similar to i++
        int temp = (int) i.age;
        i.age = h.age;
        h.age = temp;
        return i;
    }

    void _quickSort(Node l,Node h) {
        if(h!=null && l!=h && l!=h.next){
            Node temp = partition(l,h);
            _quickSort(l,temp.prev);
            _quickSort(temp.next,h);
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
                if (min.age > node2.age) {
                    min = node2;
                }

            }
            Node temp = new Node(node1.getSpeed(),node1.getAge(),node1.getResistance(), node1.getClasse(), node1.getName());
            node1.setAge(min.getAge());
            min.setAge(temp.getAge());
        }
    }




    int height(Node N) {
        if (N == null)
            return 0;

        return (int) N.height;
    }


    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    Node insert(Node node, int speed,int age,int resistance,String classe, String name) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new Node(speed,age,resistance,classe,name));

        if (age < node.age)
            node.left = insert(node.left, speed,age,resistance,classe,name);
        else if (age > node.age)
            node.right = insert(node.right, speed,age,resistance,classe,name);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && age < node.left.age)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && age > node.right.age)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && age > node.left.age) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && age < node.right.age) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.age + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }



    // Driver program to test above functions
    public static void main(String[] args)
    {
        LinkedlistIS tree = new LinkedlistIS();
        Node node1= new Node(10,69,8,"com","a1");
        Node node2= new Node(30,2,8,"com","a2");
        Node node3= new Node(1,1,8,"com","a3");
        tree.root = tree.insert(tree.root, node1.speed,node1.age,node1.resistance,node1.classe,node1.name);
        tree.root = tree.insert(tree.root, node2.speed,node2.age,node2.resistance,node2.classe,node2.name);
        tree.root = tree.insert(tree.root, node3.speed,node3.age,node3.resistance,node3.classe,node3.name);
        System.out.println("Preorder traversal" +
                " of constructed tree is : ");
        tree.preOrder(tree.root);

    }
}

// This code is contributed by Rishabh Mahrsee
