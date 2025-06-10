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
        planificador.verProcesos();

        System.out.println(planificador.FIFO());
        System.out.println(p1.getRafagaUltima());
        planificador.setColaProcesosNull();

        // Asignacion por prioridades
        Proceso a1 = new Proceso("Prioridad media", 5, 7, 2);
        Proceso a2 = new Proceso("Menor prioridad", 15, 2, 5);
        Proceso a3 = new Proceso("Mas prioridad", 1, 10, 0);
        planificador.agregarAColaProcesos(a1);
        planificador.agregarAColaProcesos(a2);
        planificador.agregarAColaProcesos(a3);
        planificador.verProcesos();

        System.out.println(planificador.asignacionPridoridades());
        planificador.setColaProcesosNull();

        // RR

        Proceso p4 = new Proceso("Adaptacion", null, 3, 1);
        Proceso p5 = new Proceso("Finalizacion", null, 2, 2);
        Proceso p6 = new Proceso("Creacion", null, 2, 0);

        planificador.agregarAColaProcesos(p4);
        planificador.agregarAColaProcesos(p5);
        planificador.agregarAColaProcesos(p6);
        planificador.verProcesos();

        System.out.println(planificador.RR(2));
    }
}
