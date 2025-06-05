import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        Queue<Integer> cola = new LinkedList<>();
        cola.add(1);
        cola.add(2);
        cola.add(3);
        for (int i : cola){
            System.out.println(i);
        }
    }
}
