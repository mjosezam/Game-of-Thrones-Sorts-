package Trees;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ServerDragons<T> {
    SimpleList classes = new SimpleList();
    SimpleList ages = new SimpleList();
    LinkedlistIS dragons = new LinkedlistIS();
    int tmp=100, dragons_num=100;
    java.util.Random random = new java.util.Random();




    public String random_classe() {
        String[] available_classe = {"Comandante", "Infantería", "Capitanes"};
        int ran_classe = random.nextInt(available_classe.length);
        if (available_classe[ran_classe].equals("Comandante")) {
            if (in((T) "Comandante", classes)) {
                random_classe();
            } else {
                classes.push(available_classe[ran_classe]);
                return available_classe[ran_classe];
            }
        }return available_classe[ran_classe];
    }

    public int random_speed(){
        int speed = (int) (1 + (Math.random() * (100 - 1)));
        return speed;
    }

    public int random_resistance(){
        int resistance = (int) (1 + (Math.random() * (3 - 1)));
        return resistance;
    }

    public int random_age(){
        int age = (int) (1 + (Math.random() * (1000 - 1)));
        if (in(age, ages)){
            random_age();
        }else{
            ages.push(age);
            return age;
        }
        return age;
    }

    public boolean in(Object tosearch, SimpleList list){
        if (list.getSize()==0){
            return false;
        }else{
            for (int i = 0; i < list.getSize(); i++) {
                if (tosearch == list.getNode(i).getData()) {
                    return true;
                }
            }
        }
        return false;
    }



    public Node setparent(){
        int parent = (int) (1 + (Math.random() * (tmp - 1)));
        if (parent==0){
            setparent();
        }else{
            Node parentnode = dragons.getNode(parent);
            if (parentnode.son1!=null && parentnode.son2!=null){
                setparent();
            }else if (parentnode.son1!=null && parentnode.son2==null || parentnode.son2!=null && parentnode.son1==null){
                return parentnode;
            }else{
                return parentnode;
            }
        }
        return null;
    }


    public void generate(String wave){
        int dragons_num = ((20*tmp)/100)+tmp;
        this.tmp=dragons_num;
        for (int cont = 0; cont < dragons_num; cont++) {
            String name = wave  + String.valueOf(cont);
            System.out.println(name);
            Node dragon = new Node(random_speed(),random_age(),random_resistance(),random_classe(),name, setparent());
            dragons.push(dragon);
        }
    }
    
}





