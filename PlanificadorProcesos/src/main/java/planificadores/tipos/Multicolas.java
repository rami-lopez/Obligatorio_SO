package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.*;

public class Multicolas extends Planificador {

    private final Map<Integer, Queue<Proceso>> colas = new HashMap<>();
    private final Map<String, Integer> rafagasRestantes = new HashMap<>();
    private final Map<String, Integer> rafagasOriginales = new HashMap<>();
    private final List<Proceso> procesosPendientes = new ArrayList<>();

    private final int quantumRR = 2;

    public void MulticolasPlanificador() {
        planificadorLista.clear();
        for (Proceso p : colaProcesos) {
            rafagasRestantes.put(p.getNombre(), p.getRafaga());
            rafagasOriginales.put(p.getNombre(), p.getRafaga());
            procesosPendientes.add(p);
        }

        // Inicializar colas
        colas.put(0, new LinkedList<>()); // va a usar RR
        colas.put(1, new LinkedList<>()); // va a usar SJF
        colas.put(2, new LinkedList<>()); // va a usar FIFO

        int tiempoActual = 0;
        Map<String, Integer> tiempoPrimeraEjecucion = new HashMap<>();

        while (!procesosPendientes.isEmpty() || colas.values().stream().anyMatch(c -> !c.isEmpty())) {

            // Cargar nuevos procesos que llegan en este tiempo
            Iterator<Proceso> it = procesosPendientes.iterator();
            while (it.hasNext()) {
                Proceso p = it.next();
                if (p.getLlegada() <= tiempoActual) {
                    colas.get(p.getPrioridad()).add(p);
                    it.remove();
                }
            }

            // Seleccionar proceso desde cola de mayor prioridad
            Proceso seleccionado = null;

            // Prioridad 0: RR (lógica igual a la del planificador de RoundRobin)
            Queue<Proceso> colaRR = colas.get(0);
            if (!colaRR.isEmpty()) {
                Proceso p = colaRR.poll();
                seleccionado = p;

                for (int i = 0; i < quantumRR && rafagasRestantes.get(p.getNombre()) > 0; i++) {
                    planificadorLista.add(p.getNombre());
                    rafagasRestantes.put(p.getNombre(), rafagasRestantes.get(p.getNombre()) - 1);
                    tiempoActual++;

                    // Cargar procesos que llegaron mientras se ejecuta este proceso
                    Iterator<Proceso> itQ = procesosPendientes.iterator();
                    while (itQ.hasNext()) {
                        Proceso nuevo = itQ.next();
                        if (nuevo.getLlegada() <= tiempoActual) {
                            colas.get(nuevo.getPrioridad()).add(nuevo);
                            itQ.remove();
                        }
                    }

                    if (!tiempoPrimeraEjecucion.containsKey(p.getNombre()))
                        tiempoPrimeraEjecucion.put(p.getNombre(), tiempoActual - 1);
                }

                if (rafagasRestantes.get(p.getNombre()) > 0)
                    colaRR.add(p);

                continue; // saltar a siguiente ciclo
            }

            // Prioridad 1: SJF
            Queue<Proceso> colaSJF = colas.get(1);
            if (!colaSJF.isEmpty()) {
                Proceso menor = Collections.min(colaSJF, Comparator.comparingInt(p -> rafagasRestantes.get(p.getNombre())));
                colaSJF.remove(menor);
                seleccionado = menor;

                while (rafagasRestantes.get(menor.getNombre()) > 0) {
                    planificadorLista.add(menor.getNombre());
                    rafagasRestantes.put(menor.getNombre(), rafagasRestantes.get(menor.getNombre()) - 1);
                    tiempoActual++;

                    if (!tiempoPrimeraEjecucion.containsKey(menor.getNombre()))
                        tiempoPrimeraEjecucion.put(menor.getNombre(), tiempoActual - 1);

                    // Llegan procesos a colas
                    Iterator<Proceso> itLlegan = procesosPendientes.iterator();
                    while (itLlegan.hasNext()) {
                        Proceso nuevo = itLlegan.next();
                        if (nuevo.getLlegada() <= tiempoActual) {
                            colas.get(nuevo.getPrioridad()).add(nuevo);
                            itLlegan.remove();
                        }
                    }
                }

                continue;
            }

            // Prioridad 2: FIFO
            Queue<Proceso> colaFIFO = colas.get(2);
            if (!colaFIFO.isEmpty()) {
                Proceso fifo = colaFIFO.poll();
                seleccionado = fifo;

                while (rafagasRestantes.get(fifo.getNombre()) > 0) {
                    planificadorLista.add(fifo.getNombre());
                    rafagasRestantes.put(fifo.getNombre(), rafagasRestantes.get(fifo.getNombre()) - 1);
                    tiempoActual++;

                    if (!tiempoPrimeraEjecucion.containsKey(fifo.getNombre()))
                        tiempoPrimeraEjecucion.put(fifo.getNombre(), tiempoActual - 1);

                    // Llegan procesos a colas
                    Iterator<Proceso> itLlegan = procesosPendientes.iterator();
                    while (itLlegan.hasNext()) {
                        Proceso nuevo = itLlegan.next();
                        if (nuevo.getLlegada() <= tiempoActual) {
                            colas.get(nuevo.getPrioridad()).add(nuevo);
                            itLlegan.remove();
                        }
                    }
                }

                continue;
            }

            // Si no hay procesos listos, simulamos CPU ociosa
            planificadorLista.add(" ");
            tiempoActual++;
        }

        // imprimir resultados
        for (Proceso p : colaProcesos) {
            int tiempoRetorno = planificadorLista.lastIndexOf(p.getNombre()) + 1 - p.getLlegada();
            int tiempoEspera = tiempoRetorno - rafagasOriginales.get(p.getNombre());
            int tiempoRespuesta = tiempoPrimeraEjecucion.get(p.getNombre()) - p.getLlegada();

            System.out.println("Proceso " + p.getNombre());
            System.out.println("  - Tiempo de retorno: " + tiempoRetorno);
            System.out.println("  - Tiempo de espera: " + tiempoEspera);
            System.out.println("  - Tiempo de respuesta: " + tiempoRespuesta);
        }

        System.out.println("\nDiagrama:");
        System.out.println(planificadorLista);
    }
}
