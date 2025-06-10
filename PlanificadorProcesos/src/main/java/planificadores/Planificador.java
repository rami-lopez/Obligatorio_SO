package planificadores;

import planificadores.proceso.Proceso;

import java.util.*;

public class Planificador {
    protected List<Proceso> colaProcesos = new ArrayList<>();
    protected List<String> planificadorLista = new ArrayList<>(setSumaDeProcesos());

    // Agregar procesos
    public void agregarAColaProcesos(Proceso proceso){
        colaProcesos.add(proceso);
    }

    public void verProcesos(){
        System.out.println("\n");
        for (Proceso proceso : colaProcesos){
            System.out.println(proceso.getNombre());
        }
        System.out.println("\n");
    }

    // Metodo para limpiar la cola de prioridades (para nuevos tipos de planificadores)
    public void setColaProcesosNull() {
        this.colaProcesos.clear();
    }

    // para establecer la capacidad de la lista
    public int setSumaDeProcesos() {
        int suma = 0;
        for (Proceso proceso : colaProcesos){
            suma += proceso.getRafaga();
        }
        return suma;
    }
    

    // Calculos de tiempo
    
    public int tiempoDeEspera(Proceso proceso){
        int espera = 0;
        int tiempoActual = 0;
        boolean estaEsperando = true;
        int ultimaEjecucion = proceso.getLlegada();
        for (String p : planificadorLista){
            if (tiempoActual >= proceso.getLlegada()){
                if (p.equals(proceso.getNombre())){
                    if (estaEsperando){
                        espera += tiempoActual - ultimaEjecucion;
                        estaEsperando = false;
                    }
                }else if (!estaEsperando){
                    estaEsperando = true;
                    ultimaEjecucion = tiempoActual;
                }
            }
            tiempoActual++;
        }
        return espera;
    }

    public int tiempoDeRetorno(Proceso proceso){
        return (tiempoDeEspera(proceso) + proceso.getRafagaUltima());
    }

    public int tiempoDeRespuesta(Proceso proceso){
        for (String p : planificadorLista){
            if (proceso.getNombre().equals(p)){
                return planificadorLista.indexOf(p) - proceso.getLlegada();
            }
        }
        return -1;
    }

    
    // Bubble sort para ordenar por prioridad o llegada la cola de procesos
    
    
    public void bubbleSortPorPrioridad(List<Proceso> listaProcesos) {
        int n = listaProcesos.size();
        boolean huboIntercambio;

        for (int i = 0; i < n - 1; i++) {
            huboIntercambio = false;

            for (int j = 0; j < n - 1 - i; j++) {
                Proceso p1 = listaProcesos.get(j);
                Proceso p2 = listaProcesos.get(j + 1);

                if (p1.getPrioridad() > p2.getPrioridad()) {
                    // Intercambiar
                    listaProcesos.set(j, p2);
                    listaProcesos.set(j + 1, p1);
                    huboIntercambio = true;
                }
            }

            if (!huboIntercambio) break;
        }
    }
    public void bubbleSortPorLlegada(List<Proceso> listaProcesos) {
        int n = listaProcesos.size();
        boolean huboIntercambio;

        for (int i = 0; i < n - 1; i++) {
            huboIntercambio = false;

            for (int j = 0; j < n - 1 - i; j++) {
                Proceso p1 = listaProcesos.get(j);
                Proceso p2 = listaProcesos.get(j + 1);

                if (p1.getLlegada() > p2.getLlegada()) {
                    // Intercambiar
                    listaProcesos.set(j, p2);
                    listaProcesos.set(j + 1, p1);
                    huboIntercambio = true;
                }
            }

            if (!huboIntercambio) break;
        }
    }
}
