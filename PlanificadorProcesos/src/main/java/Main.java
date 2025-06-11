import planificadores.tipos.*;
import planificadores.proceso.Proceso;


public class Main {
    public static void main(String[] args){

        // Instanciar/crear los tipos
        FIFO fifoPlan = new FIFO();
        RoundRobin rrPlan = new RoundRobin();
        AsignacionPrioridades asPriPlan = new AsignacionPrioridades();
        SJF sjfPlan = new SJF();
        SRTF srtfPLan = new SRTF();

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

        Proceso a1 = new Proceso("Prioridad media", 5, 7, 5);
        Proceso a2 = new Proceso("Menor prioridad", 15, 7, 2);
        Proceso a3 = new Proceso("Mas prioridad", 1, 10, 8);
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


        //SJF
        System.out.println("\n");
        System.out.println("SJF:");
        System.out.println();
        Proceso p7 = new Proceso("Adaptacion", null, 2, 1);
        Proceso p8 = new Proceso("Finalizacion", null, 3, 2);
        Proceso p9 = new Proceso("Creacion", null, 4, 0);

        sjfPlan.agregarAColaProcesos(p9);
        sjfPlan.agregarAColaProcesos(p8);
        sjfPlan.agregarAColaProcesos(p7);

        System.out.println(sjfPlan.SJFPlanificador());


        //SRTF
        System.out.println("\n");
        System.out.println("SRTF:");
        System.out.println();
        Proceso p10 = new Proceso("Adaptación", null, 4, 5);
        Proceso p11 = new Proceso("Finalización", null, 7, 2);
        Proceso p12 = new Proceso("Creación", null, 10, 8);

        srtfPLan.agregarAColaProcesos(p11);
        srtfPLan.agregarAColaProcesos(p10);
        srtfPLan.agregarAColaProcesos(p12);

        System.out.println(srtfPLan.SRTFPlanificador());
    }
}
