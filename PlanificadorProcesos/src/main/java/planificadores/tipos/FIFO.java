package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.ArrayList;
import java.util.List;

public class FIFO extends Planificador {
    public List<List<String>> FIFOPlanificador(){
        planificadorLista.clear();
        List<List<String>> listaARetornar = new ArrayList<>();
        bubbleSortPorLlegada(colaProcesos);
        for(Proceso proceso : colaProcesos){
            for (int i = 0; i < proceso.getRafaga(); i++){
                planificadorLista.add(proceso.getNombre());
            }
            proceso.ejecutar(proceso.getRafaga());
        }
        for (Proceso proceso : colaProcesos){
            //agregar todas a la lista de listas
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
