import planificadores.tipos.AsignacionPrioridades;
import planificadores.tipos.FIFO;
import planificadores.proceso.Proceso;
import planificadores.tipos.RoundRobin;


public class Main {
    public static void main(String[] args){

        // Instanciar/crear los tipos
        FIFO fifoPlan = new FIFO();
        RoundRobin rrPlan = new RoundRobin();
        AsignacionPrioridades asPriPlan = new AsignacionPrioridades();

        //FIFO
        System.out.println("\n");
        System.out.println("FIFO:");
        System.out.println();

        Proceso p1 = new Proceso("Adaptacion", null, 7, 2);
        Proceso p2 = new Proceso("Finalizacion", null, 2, 5);
        Proceso p3 = new Proceso("Creacion", null, 10, 0);

        fifoPlan.agregarAColaProcesos(p1);
        fifoPlan.agregarAColaProcesos(p2);
        fifoPlan.agregarAColaProcesos(p3);

        System.out.println(fifoPlan.FIFOPlanificador());
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos


        // Asignacion por prioridades

        System.out.println("\n");
        System.out.println("Asignacion por prioridades:");
        System.out.println();

        Proceso a1 = new Proceso("Prioridad media", 5, 7, 2);
        Proceso a2 = new Proceso("Menor prioridad", 15, 2, 5);
        Proceso a3 = new Proceso("Mas prioridad", 1, 10, 0);
        asPriPlan.agregarAColaProcesos(a1);
        asPriPlan.agregarAColaProcesos(a2);
        asPriPlan.agregarAColaProcesos(a3);

        System.out.println(asPriPlan.asignacionPridoridadesPlanificador());
        asPriPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos


        // RR
        System.out.println("\n");
        System.out.println("Round Robin:");
        System.out.println();
        Proceso p4 = new Proceso("Adaptacion", null, 3, 1);
        Proceso p5 = new Proceso("Finalizacion", null, 2, 2);
        Proceso p6 = new Proceso("Creacion", null, 2, 0);

        rrPlan.agregarAColaProcesos(p4);
        rrPlan.agregarAColaProcesos(p5);
        rrPlan.agregarAColaProcesos(p6);

        System.out.println(rrPlan.RRPlanificador(2));
        rrPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
    }
}
