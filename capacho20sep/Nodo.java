package capacho20sep;

public class Nodo {
    int codigo;
    Nodo siguiente;
    Nodo anterior;

    public Nodo(int codigo) {
        this.codigo = codigo;
        this.siguiente = null;
        this.anterior = null;
    }
}