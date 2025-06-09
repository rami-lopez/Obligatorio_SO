import java.util.*;

public class Planificador {
    private Queue<Proceso> colaProcesos = new PriorityQueue<>(Comparator.comparingInt(Proceso::getLlegada));
    private List<String> planificadorLista = new ArrayList<>(setSumaDeProcesos());
    private int contador;

    public void agregarAColaProcesos(Proceso proceso){
        colaProcesos.add(proceso);
    }

    public void verProcesos(){
        for (Proceso proceso : colaProcesos){
            System.out.println(proceso.toString());
        }
    }

    public int setSumaDeProcesos() {
        // Para establecer la capacidad de la lista
        int suma = 0;
        for (Proceso proceso : colaProcesos){
            suma += proceso.getRafaga();
        }
        return suma;
    }

    public List<List<String>> asignacionPridoridades(){
        planificadorLista.clear();
        Stack<Proceso> stackPrioridad = new Stack<>();

        Proceso mayorPrioridad = null;
        for (Proceso proceso : colaProcesos) {
            if (mayorPrioridad == null || proceso.getPrioridad() < mayorPrioridad.getPrioridad()) {
                mayorPrioridad = proceso;
                stackPrioridad.add(mayorPrioridad);
            }
        }
        return null; // por ahora
    }

    public List<List<String>> FIFO(){
        planificadorLista.clear();
        List<List<String>> listaARetornar = new ArrayList<>();
        for(Proceso proceso : colaProcesos){
            for (int i = 0; i < proceso.getRafaga(); i++){
                planificadorLista.add(proceso.getNombre());
            }
            proceso.ejecutar(proceso.getRafaga());
        }
        for (Proceso proceso : colaProcesos){
            //agregar todas a la lista de listas
            List<String> lista = new ArrayList<>();
            lista.add("Tiempo de espera: " + tiempoDeEspera(proceso));
            lista.add("Tiempo de retorno: " + tiempoDeRetorno(proceso));
            lista.add("Tiempo de respuesta: " + tiempoDeRespuesta(proceso));

            listaARetornar.add(lista);
        }

        return listaARetornar;
    }

    // public List<List<String>>

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
        return tiempoDeEspera(proceso) + proceso.getRafaga();
    }

    public int tiempoDeRespuesta(Proceso proceso){
        for (String p : planificadorLista){
            if (proceso.getNombre().equals(p)){
                return planificadorLista.indexOf(p) - proceso.getLlegada();
            }
        }
        return -1;
    }
}
