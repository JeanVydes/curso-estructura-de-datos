package capacho.sistemas_sid;

public class NodoJugador {
    int codigo;
    String nombre;
    NodoJugador siguiente;
    NodoJugador anterior;

    public NodoJugador(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.siguiente = null;
        this.anterior = null;
    }
}