package ejercicios.nallig;

// Lo utilizamos en Cola2 y en LlamadasCola2
public class Nodo {
    public int dato;
    public Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}