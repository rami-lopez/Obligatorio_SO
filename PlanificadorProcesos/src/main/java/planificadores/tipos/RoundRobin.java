package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.*;

public class RoundRobin extends Planificador {
    public List<List<String>> RRPlanificador(int quantum){
        planificadorLista.clear();
        List<List<String>> listaARetornar = new ArrayList<>();
        bubbleSortPorLlegada(colaProcesos);

        Queue<Proceso> colaListos = new LinkedList<>(); // cola de listos del RR
        List<Proceso> procesosPorLlegar = new ArrayList<>(colaProcesos); // Auxiliar para los procesos que no llegan en el t=0
        int tiempoActual = 0;

        Map<String, Integer> rafagasRestantes = new HashMap<>(); // Mapa que guarda las rafagas restantes de cada proceso
        for (Proceso p : colaProcesos) {
            rafagasRestantes.put(p.getNombre(), p.getRafaga());
        }

        // agrego los procesos que llegan en t = 0 (al inicio)
        Iterator<Proceso> itInicio = procesosPorLlegar.iterator();
        while (itInicio.hasNext()) {
            Proceso p = itInicio.next();
            if (p.getLlegada() <= tiempoActual) {
                colaListos.add(p);
                itInicio.remove();
            }
        }

        while (!colaListos.isEmpty()) {
            // Saca el siguiente proceso listo
            Proceso proceso = colaListos.poll();
            String nombre = proceso.getNombre();
            int rafagaRestante = rafagasRestantes.get(nombre);

            // Ejecuto el proceso hasta que se le termine el quantum o hasta q no le queden rafagas
            int rafagaAUsar = Math.min(quantum, rafagaRestante);
            for (int i = 0; i < rafagaAUsar; i++) {
                planificadorLista.add(nombre);
                tiempoActual++;

                // reviso si llegó algun proceso en medio del cuantum actual y lo agrego
                Iterator<Proceso> itLlegan = procesosPorLlegar.iterator();
                while (itLlegan.hasNext()) {
                    Proceso nuevo = itLlegan.next();
                    if (nuevo.getLlegada() <= tiempoActual) {
                        colaListos.add(nuevo);
                        itLlegan.remove();
                    }
                }
            }

            //Actualizo valores
            rafagaRestante -= rafagaAUsar;
            rafagasRestantes.put(nombre, rafagaRestante);

            if (rafagaRestante > 0) {
                colaListos.add(proceso); // Si todavía le quedan ráfagas entonces vuelve a la cola
            }
        }


        for (Proceso proceso : colaProcesos){
            List<String> lista = new ArrayList<>();
            lista.add("Proceso " + proceso.getNombre());
            lista.add("Tiempo de espera: " + tiempoDeEspera(proceso));
            lista.add("Tiempo de retorno: " + tiempoDeRetorno(proceso));
            lista.add("Tiempo de respuesta: " + tiempoDeRespuesta(proceso));
            listaARetornar.add(lista);
        }

        System.out.println("Diagrama:");
        System.out.println(getPlanificadorLista());
        System.out.println("Información de los procesos:");
        return listaARetornar;
    }
}
