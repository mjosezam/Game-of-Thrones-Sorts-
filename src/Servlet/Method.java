package Servlet;

import Trees.LinkedlistIS;
import Trees.Node;

public class Method {
    public static int x=0;

    public LinkedlistIS method(LinkedlistIS dragonList){
        Message request= new Message();
        LinkedlistIS newList= null;
        switch(x){
            case 0:
                newList= request.Method("insertion",dragonList);
                x++;
                break;
            case 1:
                newList = request.Method("insertion", dragonList);
                x++;
                break;

            case 2:
                newList =request.Method("quicksort",dragonList);
                x++;
                break;

            case 3:
                newList =request.Method("BinaryTree",dragonList);
                x++;
                break;

            case 4:
                newList =request.Method("avl",dragonList);
                x=0;
                break;
        }
        return newList;

    }
    public static void main(String[] args)
    {
        LinkedlistIS tree = new LinkedlistIS();
        Node node1= new Node(10,69,8,"com","a1",null);
        Node node2= new Node(30,2,8,"com","a2", node1);
        Node node3= new Node(20,1,8,"com","a3", node1);
        tree.push(node1);tree.push(node2);tree.push(node3);
        Method methos = new Method();
        LinkedlistIS x= methos.method(tree);
        System.out.println(x.head.getName());
    }


}
