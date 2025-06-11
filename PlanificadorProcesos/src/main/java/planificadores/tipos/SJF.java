package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;
import java.util.ArrayList;
import java.util.List;

public class SJF extends Planificador {
    public List<List<String>> SJFPlanificador() {
        planificadorLista.clear();
        List<List<String>> listaARetornar = new ArrayList<>();
        List<Proceso> procesos = new ArrayList<>(colaProcesos);
        bubbleSortPorLlegada(procesos);
        int tiempo = 0;
        int ejecutados = 0;
        int total = procesos.size();

        for (int ejecutado = 0; ejecutado < Integer.MAX_VALUE && ejecutados < total; ejecutado++) {
            Proceso procesoActual = null;
            int menorRafaga = Integer.MAX_VALUE; //Al poner esto, aseguramos que en la primera iteracion del segundo for, se tome la rafaga del primer proceso.
            for (Proceso p : procesos) {
                if (p.getLlegada() <= tiempo && p.getRafagaUltima() < menorRafaga) {
                    procesoActual = p;
                    menorRafaga = p.getRafagaUltima();
                }
            }
            if (procesoActual == null) {
                // No hay procesos disponibles aún → CPU ociosa
                planificadorLista.add(" ");
                tiempo++;
                continue;
            }

                for (int j = 0; j < procesoActual.getRafagaUltima(); j++) {
                    planificadorLista.add(procesoActual.getNombre());
                    tiempo++;
                }
                procesoActual.ejecutar(procesoActual.getRafaga());
                procesos.remove(procesoActual);
                ejecutados++;
            }
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

