package Trees;

public class BinaryTree {
    Node root;


    private void addNode(Node newNode) {
        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;
            while (true) {
                parent = focusNode;
                if (newNode.age < focusNode.age) {
                    focusNode = focusNode.left;
                    if (focusNode == null) {
                        parent.left = newNode;
                        return;}
                } else {
                    focusNode = focusNode.right;
                    if (focusNode == null) {
                        parent.right = newNode;
                        return;
                    }
                }
            }
        }
    }

    public void add(LinkedlistIS list, BinaryTree tree){
        for (int i = 0; i < list.getSize(); i++) {
            tree.addNode(list.getNode(i));
        }
    }

    public void preorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            System.out.println(focusNode);
            preorderTraverseTree(focusNode.left);
            preorderTraverseTree(focusNode.right);
        }
    }


    public static void main(String[] args) {
        Node node1= new Node(10,69,8,"com","a1");
        Node node2= new Node(30,2,8,"com","a2");
        Node node3= new Node(1,1,8,"com","a3");
        LinkedlistIS trial = new LinkedlistIS();
        trial.push(node1);
        trial.push(node2);
        trial.push(node3);
        BinaryTree tree = new BinaryTree();
        tree.add(trial, tree);
        tree.preorderTraverseTree(trial.head);
    }
}
