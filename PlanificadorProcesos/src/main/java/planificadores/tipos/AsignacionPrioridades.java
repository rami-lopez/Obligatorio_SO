package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.ArrayList;
import java.util.List;

public class AsignacionPrioridades extends Planificador {
    public List<List<String>> asignacionPridoridadesPlanificador() {
        planificadorLista.clear();
        bubbleSortPorPrioridad(colaProcesos);
        List<List<String>> resultado = new ArrayList<>();
        for(Proceso proceso : colaProcesos){
            for (int i = 0; i < proceso.getRafaga(); i++){
                planificadorLista.add(proceso.getNombre());
            }
            proceso.ejecutar(proceso.getRafaga());
        }
        for (Proceso proceso : colaProcesos) {
            //agregar todas a la lista de listas
            List<String> lista2 = new ArrayList<>();
            lista2.add("Proceso " + proceso.getNombre());
            lista2.add(proceso.getPrioridad().toString());
            lista2.add("Tiempo de espera: " + tiempoDeEspera(proceso));
            lista2.add("Tiempo de retorno: " + tiempoDeRetorno(proceso));
            lista2.add("Tiempo de respuesta: " + tiempoDeRespuesta(proceso));

            resultado.add(lista2);
        }
        System.out.println("Diagrama: ");
        System.out.println(getPlanificadorLista());
        System.out.println("Informacion de los procesos:");
        return resultado;
    }
}
