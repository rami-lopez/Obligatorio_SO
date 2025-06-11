package planificadores.tipos;

import planificadores.Planificador;
import planificadores.proceso.Proceso;

import java.util.ArrayList;
import java.util.List;

public class SRTF extends Planificador {
    public List<List<String>> SRTFPlanificador(){
        planificadorLista.clear();
        List<List<String>> resultado = new ArrayList<>();

        int tiempoActual = 0;
        int totalProcesos = colaProcesos.size();
        int procesosFinalizados = 0;
        Proceso ultimoEjecutado = null;

        while (procesosFinalizados < totalProcesos) {
            Proceso elegido = null;

            for (Proceso p : colaProcesos) {
                if (p.getLlegada() <= tiempoActual && p.getRafaga() > 0) {
                    if (elegido == null || p.getRafaga() < elegido.getRafaga()) {
                        elegido = p;
                    } else if (p.getRafaga() == elegido.getRafaga()) {
                        if (p == ultimoEjecutado) {
                            elegido = p;
                        }
                    }
                }
            }

            if (elegido != null) {
                elegido.ejecutar(1);
                planificadorLista.add(elegido.getNombre());
                ultimoEjecutado = elegido;

                if (elegido.getRafaga() == 0) {
                    procesosFinalizados++;
                }
            } else {
                planificadorLista.add(" "); // nada ejecutando
                ultimoEjecutado = null;
            }

            tiempoActual++;
        }

        for (Proceso proceso : colaProcesos) {
            List<String> lista2 = new ArrayList<>();
            lista2.add("Proceso " + proceso.getNombre());
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
