public class Proceso {
    private String nombre;
    private Integer prioridad;
    private int rafaga;
    private int llegada;

    public Proceso(String nombre, Integer prioridad, int rafaga, int llegada) {
        this.nombre = nombre;
        this.prioridad = prioridad;
        this.rafaga = rafaga;
        this.llegada = llegada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Integer prioridad) {
        this.prioridad = prioridad;
    }

    public int getRafaga() {
        return rafaga;
    }

    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    }

    public int getLlegada() {
        return llegada;
    }

    @Override
    public String toString() {
        return "Proceso{" +
                "nombre='" + nombre + '\'' +
                ", prioridad=" + prioridad +
                ", rafaga=" + rafaga +
                '}';
    }

    public void ejecutar(int unidadesDeTiempo) {
        this.rafaga -= unidadesDeTiempo;
    }
}
