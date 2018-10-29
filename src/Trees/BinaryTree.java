package Trees;

import sun.awt.image.ImageWatched;

public class BinaryTree {
    Node root;
    LinkedlistIS dragons = new LinkedlistIS();


    /**
     *
     * @param newNode
     */
    private void addNode(Node newNode) {
        if (root == null) {
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;
            while (true) {
                parent = focusNode;
                if (newNode.getAge() < focusNode.getAge()) {
                    focusNode = focusNode.getLeft();
                    if (focusNode == null) {
                        parent.setLeft(newNode) ;
                        return;}
                } else {
                    focusNode = focusNode.getRight();
                    if (focusNode == null) {
                        parent.setRight(newNode);
                        return;
                    }
                }
            }
        }
    }

    /**
     *
     * @param list
     * @param tree
     * @return
     */
    public LinkedlistIS add(LinkedlistIS list, BinaryTree tree){
        for (int i = 0; i < list.getSize(); i++) {
            tree.addNode(list.getNode(i));
        }
        return preorderTraverseTree(tree.root);
    }

    /**
     * 
     * @param focusNode
     * @return
     */
    public LinkedlistIS preorderTraverseTree(Node focusNode) {
        if (focusNode != null) {
            dragons.push(focusNode);
            System.out.println(focusNode);
            preorderTraverseTree(focusNode.getLeft());
            preorderTraverseTree(focusNode.getRight());
        }
        return dragons;
    }


    public static void main(String[] args) {
        //Node node1= new Node(10,69,8,"com","a1");
        //Node node2= new Node(30,2,8,"com","a2");
        //Node node3= new Node(1,1,8,"com","a3");
        //LinkedlistIS trial = new LinkedlistIS();
        //trial.push(node1);
        //trial.push(node2);
        //trial.push(node3);
        //BinaryTree tree = new BinaryTree();
        //tree.add(trial, tree);
        //tree.preorderTraverseTree(trial.head);
    }
}
