package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;
import java.util.ArrayList;
import java.util.List;

public class SJF extends Planificador {
    public List<List<String>> SJFPlanificador(){
        planificadorLista.clear();
        List<List<String>> listaARetornar = new ArrayList<>();
        List<Proceso> procesos = new ArrayList<>(colaProcesos);
        bubbleSortPorLlegada(colaProcesos);
        int tiempo = 0;
        for (int ejecutado = 0; ejecutado <procesos.size(); ejecutado++){
            Proceso procesoActual = null;
            int menorRafaga = Integer.MAX_VALUE; //Al poner esto, aseguramos que en la primera iteracion del segundo for, se tome la rafaga del primer proceso.
            for (int i = 0; i <procesos.size(); i++){
                if (colaProcesos.get(i).getLlegada() <= tiempo && colaProcesos.get(i).getRafagaUltima() < menorRafaga){
                    procesoActual = colaProcesos.get(i);
                    menorRafaga = colaProcesos.get(i).getRafagaUltima();
                }
            }
            for (int j = 0; j < procesoActual.getRafagaUltima(); j++) {
                planificadorLista.add(procesoActual.getNombre());
                tiempo++;
            }
            procesoActual.ejecutar(procesoActual.getRafaga());
            procesos.remove(procesoActual);
        }
        for (Proceso proceso : colaProcesos){
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
