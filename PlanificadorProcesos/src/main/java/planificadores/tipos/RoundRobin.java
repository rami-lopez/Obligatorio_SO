package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin extends Planificador {
    public List<List<String>> RRPlanificador(int quantum){
        planificadorLista.clear();
        List<List<String>> listaARetornar = new ArrayList<>();
        bubbleSortPorLlegada(colaProcesos);
        Queue<Proceso> colaRR = new LinkedList<>(colaProcesos);

        //int tiempo = 0;
        while (!colaRR.isEmpty()) {
            Proceso proceso = colaRR.poll();

            int rafagaAUsar = Math.min(quantum, proceso.getRafaga());
            for (int i = 0; i < rafagaAUsar; i++) {
                planificadorLista.add(proceso.getNombre());
                //tiempo++;
            }

            proceso.ejecutar(rafagaAUsar);

            if (proceso.getRafaga() > 0) {
                colaRR.add(proceso);
            }
        }

        for (Proceso proceso : colaProcesos){
            List<String> lista = new ArrayList<>();
            lista.add(proceso.getNombre());
            lista.add("Tiempo de espera: " + tiempoDeEspera(proceso));
            lista.add("Tiempo de retorno: " + tiempoDeRetorno(proceso));
            lista.add("Tiempo de respuesta: " + tiempoDeRespuesta(proceso));
            listaARetornar.add(lista);
        }
        return listaARetornar;
    }
}
