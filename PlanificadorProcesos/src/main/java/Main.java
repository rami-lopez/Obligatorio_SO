import planificadores.tipos.*;
import planificadores.proceso.Proceso;

import java.util.Random;


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
        System.out.println();

        // Procesos autogenerados
        System.out.println("FIFO con procesos generados automaticamente:");
        System.out.println();
        for (int i = 0; i < 5; i++){
            Proceso p = generarProcesoRandom(false);
            fifoPlan.agregarAColaProcesos(p);
            System.out.println("Proceso " + p.getNombre() + " creado con rafaga de " + p.getRafaga() + " y llegada en " + p.getLlegada());
        }
        System.out.println();
        System.out.println(fifoPlan.FIFOPlanificador());
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
        System.out.println();


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
        System.out.println();

        // Procesos autogenerados
        System.out.println("Prioridades con procesos generados automaticamente:");
        System.out.println();
        for (int i = 0; i < 5; i++){
            Proceso p = generarProcesoRandom(true);
            asPriPlan.agregarAColaProcesos(p);
            System.out.println("Proceso " + p.getNombre() + " creado con rafaga de " + p.getRafaga() + ", prioridad de" + p.getPrioridad() + " y llegada en " + p.getLlegada());
        }
        System.out.println();
        System.out.println(asPriPlan.asignacionPridoridadesPlanificador());
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
        System.out.println();


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
        System.out.println();

        // Procesos autogenerados
        System.out.println("Round Robin con procesos generados automaticamente:");
        System.out.println();
        for (int i = 0; i < 5; i++){
            Proceso p = generarProcesoRandom(false);
            rrPlan.agregarAColaProcesos(p);
            System.out.println("Proceso " + p.getNombre() + " creado con rafaga de " + p.getRafaga() + " y llegada en " + p.getLlegada());
        }
        Random rand = new Random();
        int quantumRandom = rand.nextInt(10) + 1;
        System.out.println();
        System.out.println("Quantum de " + quantumRandom);
        System.out.println(rrPlan.RRPlanificador(quantumRandom));
        System.out.println();
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
        System.out.println();



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
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
        System.out.println();

        // Procesos autogenerados
        System.out.println("SJF con procesos generados automaticamente:");
        System.out.println();
        for (int i = 0; i < 5; i++){
            Proceso p = generarProcesoRandom(false);
            sjfPlan.agregarAColaProcesos(p);
            System.out.println("Proceso " + p.getNombre() + " creado con rafaga de " + p.getRafaga() + " y llegada en " + p.getLlegada());
        }
        System.out.println();
        System.out.println(sjfPlan.SJFPlanificador());
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
        System.out.println();


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
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
        System.out.println();

        // Procesos autogenerados
        System.out.println("SRTF con procesos generados automaticamente:");
        System.out.println();
        for (int i = 0; i < 5; i++){
            Proceso p = generarProcesoRandom(false);
            srtfPLan.agregarAColaProcesos(p);
            System.out.println("Proceso " + p.getNombre() + " creado con rafaga de " + p.getRafaga() + " y llegada en " + p.getLlegada());
        }
        System.out.println();
        System.out.println(srtfPLan.SRTFPlanificador());
        fifoPlan.setColaProcesosNull(); // setear la cola en null para otro tipo de procesos
    }

    public static Proceso generarProcesoRandom(boolean tienePrioridad) {
        Random rand = new Random();
        Integer prioridad;
        String[] nombresBase = { "Carga", "Lectura", "Compilacion", "Comparacion", "Analisis", "Validacion", "Actualizacion"};

        String nombre = nombresBase[rand.nextInt(nombresBase.length)];
        if (tienePrioridad) {
            prioridad = rand.nextInt(10) + 1;
        }
        else {
            prioridad = null;
        }
        int rafaga = rand.nextInt(9) + 2;
        int llegada = rand.nextInt(6);

        return new Proceso(nombre, prioridad, rafaga, llegada);
    }
}
