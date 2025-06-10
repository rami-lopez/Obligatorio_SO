package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.ArrayList;
import java.util.List;

public class AsignacionPrioridades extends Planificador {
    public List<List<String>> asignacionPridoridadesPlanificador() {
        planificadorLista.clear();
        // lista original copiada para no modificar la cola original
        List<Proceso> copia = new ArrayList<>(colaProcesos);
        bubbleSortPorPrioridad(copia);
        List<List<String>> resultado = new ArrayList<>();
        for (Proceso proceso : copia) {
            planificadorLista.add(proceso.getNombre());
            //agregar todas a la lista de listas
            List<String> lista2 = new ArrayList<>();
            lista2.add(proceso.getNombre());
            lista2.add(proceso.getPrioridad().toString());
            lista2.add("Tiempo de espera: " + tiempoDeEspera(proceso));
            lista2.add("Tiempo de retorno: " + tiempoDeRetorno(proceso));
            lista2.add("Tiempo de respuesta: " + tiempoDeRespuesta(proceso));

            resultado.add(lista2);
        }
        return resultado;
    }
}
