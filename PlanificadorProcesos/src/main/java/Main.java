import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args){
        Planificador planificador = new Planificador();

        //FIFO
        Proceso p1 = new Proceso("Adaptacion", null, 7, 2);
        Proceso p2 = new Proceso("Finalizacion", null, 2, 5);
        Proceso p3 = new Proceso("Creacion", null, 10, 0);

        planificador.agregarAColaProcesos(p1);
        planificador.agregarAColaProcesos(p2);
        planificador.agregarAColaProcesos(p3);
        planificador.bubbleSortPorLlegada(planificador.getColaProcesos());
        planificador.verProcesos();

        System.out.println(planificador.FIFO());
        System.out.println(p1.getRafagaUltima());
    }
}
