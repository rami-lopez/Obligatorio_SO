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

        // RR
        Planificador planificador2 = new Planificador();

        Proceso p4 = new Proceso("Adaptacion", null, 3, 1);
        Proceso p5 = new Proceso("Finalizacion", null, 2, 2);
        Proceso p6 = new Proceso("Creacion", null, 2, 0);

        planificador2.agregarAColaProcesos(p4);
        planificador2.agregarAColaProcesos(p5);
        planificador2.agregarAColaProcesos(p6);
        planificador2.bubbleSortPorLlegada(planificador2.getColaProcesos());
        planificador2.verProcesos();

        System.out.println(planificador2.RR(2));
    }
}
