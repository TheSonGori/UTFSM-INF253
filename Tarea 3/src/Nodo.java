public abstract class Nodo {

    private Integer id;

    // Contructor
    public Nodo(Integer id) {
        this.id = id;
    }

    // Id
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    // Funciones
    abstract void interactuar(Jugador jugador);

    public void agregarNodo(Nodo Nodo) {
    }
}
