import java.util.*;

public class Planificador {
    private List<Proceso> colaProcesos = new ArrayList<>();
    private List<String> planificadorLista = new ArrayList<>(setSumaDeProcesos());

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

    public List<Proceso> getColaProcesos() {
        return colaProcesos;
    }

    public void setColaProcesosNull() {
        this.colaProcesos.clear();
    }

    public int setSumaDeProcesos() {
        // Para establecer la capacidad de la lista
        int suma = 0;
        for (Proceso proceso : colaProcesos){
            suma += proceso.getRafaga();
        }
        return suma;
    }

    public List<List<String>> asignacionPridoridades() {
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


    public List<List<String>> FIFO(){
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
