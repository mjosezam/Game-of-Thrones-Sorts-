package Servlet;

import Trees.LinkedlistIS;

public class Method {
    public static int x=0;

    public LinkedlistIS method(LinkedlistIS dragonList){
        Message request= new Message();
        LinkedlistIS newList= null;
        switch(x){
            case 0:
                newList= request.Method("selection",dragonList);
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


}
