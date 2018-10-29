package Trees;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Node<T> {
    private Node next, prev, left, right, parent, son1, son2;
    private int height;
    private String name, classe;
    private int resistance,speed, age;


    public Node(int speed,int age,int resistance,String classe, String name, Node parent) {
        this.age = age;
        this.speed = speed;
        this.resistance = resistance;
        this.classe = classe;
        this.name = name;
        this.parent = parent;
        height=1;
    }
    public Node(){

    }

    public Node getSon1() {
        return son1;
    }

    public void setSon1(Node son1) {
        this.son1 = son1;
    }

    public Node getSon2() {
        return son2;
    }

    public void setSon2(Node son2) {
        this.son2 = son2;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}