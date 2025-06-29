package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.ArrayList;
import java.util.List;

public class AsignacionPrioridades extends Planificador {
    public List<List<String>> asignacionPridoridadesPlanificador() {
        planificadorLista.clear();
        List<List<String>> resultado = new ArrayList<>();

        int tiempoActual = 0;
        int totalProcesos = colaProcesos.size();
        int procesosFinalizados = 0;

        while (procesosFinalizados < totalProcesos) {
            Proceso elegido = null;

            //Siempre que llegue un proceso con mayor prioridad, el mismo se va a ejecutar
            for (Proceso p : colaProcesos) {
                if (p.getLlegada() <= tiempoActual && p.getRafaga() > 0) {
                    if (elegido == null || p.getPrioridad() < elegido.getPrioridad()) {
                        elegido = p;
                    }
                }
            }

            if (elegido != null) {
                elegido.ejecutar(1);  //Ejecuta de a una ráfaga para poder verificar si llega otro proceso de mayor prioridad
                planificadorLista.add(elegido.getNombre());

                if (elegido.getRafaga() == 0) {
                    procesosFinalizados++;
                }
            } else {
                planificadorLista.add(" "); // nada ejecutando
            }

            tiempoActual++;
        }

        //imprimir resultados
        for (Proceso proceso : colaProcesos) {
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
