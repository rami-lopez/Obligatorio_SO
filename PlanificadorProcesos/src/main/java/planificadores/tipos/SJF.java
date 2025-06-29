package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;
import java.util.ArrayList;
import java.util.List;

public class SJF extends Planificador {
    public List<List<String>> SJFPlanificador() {
        planificadorLista.clear();                              //Quitamos los procesos que quedaron de otros planificadores
        List<List<String>> listaARetornar = new ArrayList<>();
        List<Proceso> procesos = new ArrayList<>(colaProcesos);
        bubbleSortPorLlegada(procesos);
        int tiempo = 0;
        int ejecutados = 0;
        int total = procesos.size();

        // Se ejecuta mientras haya procesos pendientes
        for (int ejecutado = 0; ejecutado < Integer.MAX_VALUE && ejecutados < total; ejecutado++) {
            Proceso procesoActual = null;
            int menorRafaga = Integer.MAX_VALUE; //Se usa para encontrar el proceso con la menor ráfaga entre los disponibles

            // Buscar el proceso disponible con la ráfaga más corta
            for (Proceso p : procesos) {
                if (p.getLlegada() <= tiempo && p.getRafagaUltima() < menorRafaga) {
                    procesoActual = p;
                    menorRafaga = p.getRafagaUltima();
                }
            }
            if (procesoActual == null) {
                // No hay procesos disponibles todavía, CPU ociosa
                planificadorLista.add(" ");
                tiempo++;
                continue; // Volver al inicio del bucle
            }
                // Ejecutar el proceso seleccionado durante toda su ráfaga (ya que no es apropiativo)
                for (int j = 0; j < procesoActual.getRafagaUltima(); j++) {
                    planificadorLista.add(procesoActual.getNombre());
                    tiempo++;
                }
                procesoActual.ejecutar(procesoActual.getRafaga());
                procesos.remove(procesoActual);
                ejecutados++;
            }

            // imprimir resultados
            for (Proceso proceso : colaProcesos) {
                //agregar todas a la lista de listas
                List<String> lista = new ArrayList<>();
                lista.add("Proceso " + proceso.getNombre());
                lista.add("Tiempo de espera: " + tiempoDeEspera(proceso));
                lista.add("Tiempo de retorno: " + tiempoDeRetorno(proceso));
                lista.add("Tiempo de respuesta: " + tiempoDeRespuesta(proceso));

                listaARetornar.add(lista);
            }
            System.out.println("Diagrama: ");
            System.out.println(getPlanificadorLista());
            System.out.println("Informacion de los procesos:");

            return listaARetornar;
        }
    }

